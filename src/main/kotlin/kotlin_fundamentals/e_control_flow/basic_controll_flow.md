# 🔁 Kotlin Control Flow

Control flow lets you run different blocks of code based on conditions or loops.

---

## ✅ `if` Expression

```kotlin
val max = if (a > b) a else b
````

Supports blocks too:

```kotlin
val max = if (a > b) {
    println("a is larger")
    a
} else {
    b
}
```

---

## 🧮 `when` Expression

Used instead of `switch`.

```kotlin
val result = when (x) {
    1 -> "One"
    2 -> "Two"
    in 3..5 -> "Three to Five"
    else -> "Other"
}
```

---

## 🔁 `for` Loop

```kotlin
for (i in 1..5) {
    println(i)
}
```

Loop through collections:

```kotlin
val names = listOf("Ali", "Sara", "Omar")
for (name in names) {
    println(name)
}
```

---

## 🔁 `while` and `do..while`

```kotlin
var x = 5
while (x > 0) {
    println(x)
    x--
}

do {
    println("Runs at least once")
} while (x > 0)
```

---

## ⏩ `break` and `continue`

```kotlin
for (i in 1..10) {
    if (i == 5) break     // Exit loop
    if (i % 2 == 0) continue // Skip even numbers
    println(i)
}
```

---

## 🔁 Summary

| Keyword     | Purpose                             |
| ----------- | ----------------------------------- |
| `if`        | Simple branching                    |
| `when`      | Multi-branch matching (like switch) |
| `for`       | Iterate over ranges/collections     |
| `while`     | Loop while condition is true        |
| `do..while` | Loop at least once                  |
| `break`     | Exit the loop                       |
| `continue`  | Skip to next iteration              |

---

🧠 **Tips:**

* `if` and `when` can be **expressions** (return values).
* Prefer range-based `for` loops for simplicity.

```