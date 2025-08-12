Here’s a **beautiful and simplified Markdown guide** to help you understand **Kotlin Variables** in an easy and beginner-friendly way:

---

# 🌱 Kotlin Variables Made Simple

In Kotlin, **variables** are containers for storing data. You can define a variable using two main keywords:

* `val` – for **read-only** (immutable) variables
* `var` – for **changeable** (mutable) variables

---

## 🔒 `val` – Immutable Variable

Use `val` when the value **won’t change** after it's set.

```kotlin
val age: Int = 25
```

* ✅ Can be **read**
* ❌ Cannot be **reassigned**

> Think of it like a **final value** or a **constant** in other languages.

---

## 🔄 `var` – Mutable Variable

Use `var` when the value **can change** later.

```kotlin
var score: Int = 10
score += 5
```

* ✅ Can be **read**
* ✅ Can be **updated**

> Use it when the data is **dynamic** or **temporary**.

---

## 🧠 Type Inference

Kotlin is smart enough to guess the type!

```kotlin
val name = "Ali"    // Kotlin knows it's a String
val number = 10     // Kotlin knows it's an Int
```

You **can omit** the type unless you're delaying initialization.

---

## ⏳ Delayed Initialization

You can declare first and assign later, but must **mention the type**.

```kotlin
val count: Int
count = 3
```

* `val` still can’t be reassigned once set.
* `var` can be reassigned anytime.

---

## 🌍 Top-Level Variables

Variables can also be declared **outside functions** – at the top level.

```kotlin
val PI = 3.14
var clicks = 0

fun increment() {
    clicks += 1
}
```

* These act like **global variables**.

---

## ✅ Summary

| Keyword | Mutable? | Type Required? | Reassignable? |
| ------- | -------- | -------------- | ------------- |
| `val`   | ❌        | Optional       | ❌             |
| `var`   | ✅        | Optional       | ✅             |

---

### 💡 Pro Tip:

Prefer `val` by default. Use `var` only when necessary. This makes your code **safer and easier to maintain**.

---