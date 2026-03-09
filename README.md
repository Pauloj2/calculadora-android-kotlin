# 📱 Calculadora Android em Kotlin

---

## 📌 Sobre o projeto

Este projeto consiste no desenvolvimento de uma calculadora funcional para Android, criada no Android Studio, utilizando:

- **XML** para construção da interface
- **Kotlin** para implementação da lógica

A aplicação permite realizar operações matemáticas básicas e também inclui funções científicas, além de tratamento de erros para garantir maior robustez do sistema.
<p align="center">
  <img src="https://github.com/user-attachments/assets/df1e5c0f-1dba-4d98-a83f-705882d054c5" width="300">
</p>

## 🎯 Objetivo

O objetivo do projeto é desenvolver uma calculadora com:

- Interface organizada e intuitiva
- Operações matemáticas básicas
- Funções científicas
- Tratamento de erros
- Boa experiência de usuário (UX/UI)

---

## 🧮 Funcionalidades

### Operações básicas

A calculadora realiza as quatro operações fundamentais:

- ➕ Soma
- ➖ Subtração
- ✖️ Multiplicação
- ➗ Divisão

**Exemplo:**

```
7 + 3 = 10
9 - 4 = 5
6 * 5 = 30
8 / 2 = 4
```

---

### 🔬 Funções científicas

A aplicação também conta com funções matemáticas adicionais:

| Função | Descrição   |
|--------|-------------|
| `sin`  | Seno        |
| `cos`  | Cosseno     |
| `tan`  | Tangente    |
| `log`  | Logaritmo   |
| `^`    | Potência    |

**Exemplo:**

```
sin(30) = 0.5
cos(60) = 0.5
tan(45) ≈ 1
2 ^ 3 = 8
```

---

### 🧠 Precedência matemática

O sistema respeita a ordem correta das operações matemáticas.

**Exemplo:**

```
2 + 3 * 4 = 14
(2 + 3) * 4 = 20
```

---

## ⚠️ Tratamento de erros

Para garantir maior robustez, o aplicativo trata situações inválidas como:

**Divisão por zero:**

```
8 / 0 → Erro
```

> O aplicativo não fecha e informa o erro ao usuário.

**Operadores duplicados:**

O sistema impede que o usuário digite expressões como:

```
5 + * 2
```

Evitando erros de cálculo.

---

## 🎨 Interface (UX/UI)

A interface foi desenvolvida com foco em simplicidade e organização, utilizando **GridLayout** para organizar os botões.

### Estrutura da tela

A interface possui duas áreas principais:

**Área superior**
- Exibição da expressão digitada
- Exibição do resultado

**Área inferior**
- Teclado da calculadora organizado em grade

### Personalização visual

- Fundo escuro moderno
- Cores diferentes para operadores
- Resultado com fonte maior
- Botões organizados em grade

---

## 🧩 Estrutura do projeto

```
CalculadoraAndroid
│
├── app
│   ├── java
│   │   └── MainActivity.kt
│   │
│   ├── res
│   │   ├── layout
│   │   │   └── activity_main.xml
│   │   │
│   │   └── values
│   │       └── strings.xml
│
└── build.gradle
```

---

## ⚙️ Tecnologias utilizadas

- ![Android](https://img.shields.io/badge/Android_Studio-3DDC84?style=flat&logo=android-studio&logoColor=white) Android Studio
- ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white) Kotlin
- XML Layout
- GridLayout
- Material Design

---

## ▶️ Como executar o projeto

1. **Clone este repositório:**

```bash
git clone https://github.com/SEU_USUARIO/calculadora-android-kotlin.git
```

2. Abra o projeto no **Android Studio**
3. Aguarde a sincronização do **Gradle**
4. Execute o aplicativo em um emulador ou dispositivo Android

---

## 👨‍💻 Autor

**Paulo Junior Rodrigues**  
Estudante de Análise e Desenvolvimento de Sistemas

---

## 📄 Licença

Este projeto foi desenvolvido para **fins educacionais**.
