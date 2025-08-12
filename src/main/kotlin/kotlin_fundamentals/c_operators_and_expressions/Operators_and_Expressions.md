# 🧮 Kotlin Operations and Expressions

In Kotlin, **expressions return values** and can be assigned directly. Almost everything in Kotlin is an expression, not just a statement.

---

## ✏️ Basic Arithmetic

```kotlin
val a = 10
val b = 3

val sum = a + b       // 13
val diff = a - b      // 7
val product = a * b   // 30
val quotient = a / b  // 3
val remainder = a % b // 1
````

---

## 💡 Assignment and Compound Operators

```kotlin
var x = 5
x += 3  // Same as x = x + 3
x -= 2
x *= 4
x /= 2
```

---

## 🔍 Comparison Operators

Used in conditions, return a Boolean:

| Operator | Description      | Example  |
| -------- | ---------------- | -------- |
| `==`     | Equal to         | `a == b` |
| `!=`     | Not equal to     | `a != b` |
| `<`      | Less than        | `a < b`  |
| `>`      | Greater than     | `a > b`  |
| `<=`     | Less or equal    | `a <= b` |
| `>=`     | Greater or equal | `a >= b` |

---

## ⚙️ Logical Operators

```kotlin
val a = true
val b = false

a && b // false
a || b // true
!a     // false
```

---

## 👀 Range and Membership

```kotlin
val x = 5
x in 1..10     // true
x !in 6..10    // true
```

---

## 🧠 Expression Example

```kotlin
val max = if (a > b) a else b
```

The `if` expression returns a value!

---

## 🎯 Summary

* ✅ Most things in Kotlin are **expressions**
* ✅ Use concise syntax for logic
* ✅ Prefer expressions over traditional statements when possible

````