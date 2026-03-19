# 📱 Android Calculator in Kotlin

---

## 📌 About the Project

This project consists of developing a functional calculator for Android, built in Android Studio using:

- **XML** for interface construction
- **Kotlin** for logic implementation

The application allows basic mathematical operations and also includes scientific functions, along with error handling to ensure greater system robustness.

<p align="center">
  <img src="https://github.com/user-attachments/assets/df1e5c0f-1dba-4d98-a83f-705882d054c5" width="300">
</p>

---

## 🎯 Objective

The goal of the project is to develop a calculator with:

- Organized and intuitive interface
- Basic mathematical operations
- Scientific functions
- Error handling
- Good user experience (UX/UI)

---

## 🧮 Features

### Basic Operations

The calculator performs the four fundamental operations:

- ➕ Addition
- ➖ Subtraction
- ✖️ Multiplication
- ➗ Division

**Example:**
```
7 + 3 = 10
9 - 4 = 5
6 * 5 = 30
8 / 2 = 4
```

---

### 🔬 Scientific Functions

The application also includes additional mathematical functions:

| Function | Description    |
|----------|----------------|
| `sin`    | Sine           |
| `cos`    | Cosine         |
| `tan`    | Tangent        |
| `log`    | Logarithm      |
| `^`      | Exponentiation |

**Example:**
```
sin(30) = 0.5
cos(60) = 0.5
tan(45) ≈ 1
2 ^ 3 = 8
```

---

### 🧠 Mathematical Precedence

The system respects the correct order of mathematical operations.

**Example:**
```
2 + 3 * 4 = 14
(2 + 3) * 4 = 20
```

---

## ⚠️ Error Handling

To ensure greater robustness, the app handles invalid situations such as:

**Division by zero:**
```
8 / 0 → Error
```

> The application does not crash and informs the user of the error.

**Duplicate operators:**

The system prevents the user from typing expressions such as:
```
5 + * 2
```

Avoiding calculation errors.

---

## 🎨 Interface (UX/UI)

The interface was developed with a focus on simplicity and organization, using **GridLayout** to arrange the buttons.

### Screen Structure

The interface has two main areas:

**Upper Area**

- Display of the typed expression
- Display of the result

**Lower Area**

- Calculator keyboard organized in a grid

### Visual Customization

- Modern dark background
- Different colors for operators
- Result with larger font
- Buttons organized in a grid

---

## 🧩 Project Structure
```
CalculatorAndroid
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

## ⚙️ Technologies Used

- ![Android](https://img.shields.io/badge/Android_Studio-3DDC84?style=flat&logo=android-studio&logoColor=white) Android Studio
- ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white) Kotlin
- XML Layout
- GridLayout
- Material Design

---

## ▶️ How to Run the Project

1. **Clone this repository:**
```bash
git clone https://github.com/YOUR_USERNAME/calculator-android-kotlin.git
```

2. Open the project in **Android Studio**
3. Wait for **Gradle** synchronization
4. Run the application on an emulator or Android device

---

## 👨‍💻 Author

**Paulo Junior Rodrigues**  
Systems Analysis and Development Student

---

## 📄 License

This project was developed for **educational purposes**.
