package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    private lateinit var txtExpression: TextView
    private lateinit var txtResult: TextView

    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtExpression = findViewById(R.id.txtExpression)
        txtResult = findViewById(R.id.txtResult)

        // Números
        setNumberClick(R.id.btn0, "0")
        setNumberClick(R.id.btn1, "1")
        setNumberClick(R.id.btn2, "2")
        setNumberClick(R.id.btn3, "3")
        setNumberClick(R.id.btn4, "4")
        setNumberClick(R.id.btn5, "5")
        setNumberClick(R.id.btn6, "6")
        setNumberClick(R.id.btn7, "7")
        setNumberClick(R.id.btn8, "8")
        setNumberClick(R.id.btn9, "9")

        // Operadores básicos
        setOperatorClick(R.id.btnPlus, "+")
        setOperatorClick(R.id.btnMinus, "-")
        setOperatorClick(R.id.btnMultiply, "*")
        setOperatorClick(R.id.btnDivide, "/")
        setOperatorClick(R.id.btnPower, "^")

        // Outros
        findViewById<Button>(R.id.btnDot).setOnClickListener { appendDot() }
        findViewById<Button>(R.id.btnOpen).setOnClickListener { appendText("(") }
        findViewById<Button>(R.id.btnClose).setOnClickListener { appendText(")") }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            expression = ""
            updateDisplay("0")
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            calculateResult()
        }

        // Científica
        findViewById<Button>(R.id.btnSin).setOnClickListener { appendFunction("sin(") }
        findViewById<Button>(R.id.btnCos).setOnClickListener { appendFunction("cos(") }
        findViewById<Button>(R.id.btnTan).setOnClickListener { appendFunction("tan(") }
        findViewById<Button>(R.id.btnLog).setOnClickListener { appendFunction("log(") }
    }

    private fun setNumberClick(buttonId: Int, value: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            appendText(value)
        }
    }

    private fun setOperatorClick(buttonId: Int, operator: String) {
        findViewById<Button>(buttonId).setOnClickListener {
            appendOperator(operator)
        }
    }

    private fun appendText(value: String) {
        expression += value
        updateDisplay()
    }

    private fun appendFunction(func: String) {
        if (expression.isNotEmpty()) {
            val last = expression.last()
            if (last.isDigit() || last == ')') {
                Toast.makeText(this, "Operador necessário antes da função", Toast.LENGTH_SHORT).show()
                return
            }
        }
        expression += func
        updateDisplay()
    }

    private fun appendOperator(operator: String) {
        if (expression.isEmpty()) {
            if (operator == "-") {
                expression += operator
                updateDisplay()
            }
            return
        }

        val last = expression.last()

        if (isOperator(last) || last == '.') {
            Toast.makeText(this, "Não é permitido usar dois operadores seguidos", Toast.LENGTH_SHORT).show()
            return
        }

        expression += operator
        updateDisplay()
    }

    private fun appendDot() {
        if (expression.isEmpty()) {
            expression = "0."
            updateDisplay()
            return
        }

        val lastNumber = getLastNumberPart()

        if (lastNumber.contains(".")) {
            Toast.makeText(this, "Número já possui ponto decimal", Toast.LENGTH_SHORT).show()
            return
        }

        val last = expression.last()
        if (isOperator(last) || last == '(') {
            expression += "0."
        } else {
            expression += "."
        }
        updateDisplay()
    }

    private fun getLastNumberPart(): String {
        val operators = listOf('+', '-', '*', '/', '^', '(', ')')
        var i = expression.length - 1

        while (i >= 0 && !operators.contains(expression[i])) {
            i--
        }

        return expression.substring(i + 1)
    }

    private fun calculateResult() {
        if (expression.isBlank()) return

        try {
            val result = ExpressionEvaluator(expression).parse()

            if (result.isInfinite() || result.isNaN()) {
                txtResult.text = "Erro"
                Toast.makeText(this, "Operação inválida", Toast.LENGTH_SHORT).show()
                return
            }

            val formatted = formatResult(result)
            txtExpression.text = expression
            txtResult.text = formatted
            expression = formatted
        } catch (e: ArithmeticException) {
            txtResult.text = "Erro"
            Toast.makeText(this, e.message ?: "Erro matemático", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            txtResult.text = "Erro"
            Toast.makeText(this, "Expressão inválida", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateDisplay(defaultValue: String? = null) {
        txtExpression.text = expression
        txtResult.text = if (expression.isEmpty()) defaultValue ?: "0" else expression
    }

    private fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^'
    }

    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toLong().toString()
        } else {
            value.toString()
        }
    }
}

/**
 * Avaliador de expressões com precedência matemática.
 * Suporta:
 * +, -, *, /, ^, parênteses, sin, cos, tan, log
 *
 * sin, cos, tan usam graus.
 * log é log natural.
 */
class ExpressionEvaluator(private val input: String) {

    private var pos = -1
    private var ch = 0

    fun parse(): Double {
        nextChar()
        val x = parseExpression()
        if (pos < input.length) throw RuntimeException("Caractere inesperado: ${ch.toChar()}")
        return x
    }

    private fun nextChar() {
        pos++
        ch = if (pos < input.length) input[pos].code else -1
    }

    private fun eat(charToEat: Int): Boolean {
        while (ch == ' '.code) nextChar()
        if (ch == charToEat) {
            nextChar()
            return true
        }
        return false
    }

    private fun parseExpression(): Double {
        var x = parseTerm()
        while (true) {
            x = when {
                eat('+'.code) -> x + parseTerm()
                eat('-'.code) -> x - parseTerm()
                else -> return x
            }
        }
    }

    private fun parseTerm(): Double {
        var x = parseFactor()
        while (true) {
            x = when {
                eat('*'.code) -> x * parseFactor()
                eat('/'.code) -> {
                    val divisor = parseFactor()
                    if (divisor == 0.0) throw ArithmeticException("Divisão por zero")
                    x / divisor
                }
                else -> return x
            }
        }
    }

    private fun parseFactor(): Double {
        if (eat('+'.code)) return parseFactor()
        if (eat('-'.code)) return -parseFactor()

        var x: Double
        val startPos = pos

        if (eat('('.code)) {
            x = parseExpression()
            if (!eat(')'.code)) throw RuntimeException("Faltou fechar parêntese")
        } else if ((ch in '0'.code..'9'.code) || ch == '.'.code) {
            while ((ch in '0'.code..'9'.code) || ch == '.'.code) nextChar()
            x = input.substring(startPos, pos).toDouble()
        } else if (ch in 'a'.code..'z'.code || ch in 'A'.code..'Z'.code) {
            while (ch in 'a'.code..'z'.code || ch in 'A'.code..'Z'.code) nextChar()
            val func = input.substring(startPos, pos)

            x = parseFactor()

            x = when (func) {
                "sin" -> sin(Math.toRadians(x))
                "cos" -> cos(Math.toRadians(x))
                "tan" -> tan(Math.toRadians(x))
                "log" -> {
                    if (x <= 0) throw ArithmeticException("Logaritmo inválido")
                    ln(x)
                }
                else -> throw RuntimeException("Função desconhecida: $func")
            }
        } else {
            throw RuntimeException("Expressão inválida")
        }

        if (eat('^'.code)) {
            x = x.pow(parseFactor())
        }

        return x
    }
}