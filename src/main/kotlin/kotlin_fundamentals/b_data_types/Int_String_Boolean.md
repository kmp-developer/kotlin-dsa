# 📚 Kotlin Basic Types

In Kotlin, **everything is an object** – even basic types. That means you can call functions and access properties on them just like regular classes.

Kotlin's basic types cover:

- [Numbers](#1-numbers)
- [Booleans](#2-booleans)
- [Characters](#3-characters)
- [Strings](#4-strings)
- [Arrays](#5-arrays)

There are also some **special types**:  
👉 [`Any`](#special-types-any) | [`Nothing`](#special-types-nothing) | [`Unit`](#special-types-unit)

---

## 1. 🔢 Numbers

Kotlin supports several numeric types:

| Type     | Size (bits) | Example        |
|----------|-------------|----------------|
| `Byte`   | 8           | `val b: Byte = 1` |
| `Short`  | 16          | `val s: Short = 10` |
| `Int`    | 32          | `val i: Int = 100` |
| `Long`   | 64          | `val l: Long = 1000L` |
| `Float`  | 32          | `val f: Float = 3.14f` |
| `Double` | 64          | `val d: Double = 3.14159` |

🔹 Kotlin also supports **unsigned** versions like `UByte`, `UInt`, `ULong`.

> Numbers behave like objects: `i.toString()`, `d.compareTo(other)`, etc.

---

## 2. ✅ Booleans

```kotlin
val isActive: Boolean = true


Booleans support:

* `&&` (and)
* `||` (or)
* `!` (not)

---

## 3. 🔤 Characters

```kotlin
val letter: Char = 'A'
```

Characters are single quoted and support:

* `isLetter()`
* `isDigit()`
* Comparisons like `a > b`

---

## 4. 🧵 Strings

Strings are immutable text containers.

```kotlin
val name = "Kotlin"
```

Supports:

* `.length`
* `.uppercase()`, `.lowercase()`
* String templates: `"Hello, $name"`
* Multiline: `"""Triple-quoted text"""`

---

## 5. 📦 Arrays

Fixed-size container of elements.

```kotlin
val numbers = arrayOf(1, 2, 3)
```

Other types:

```kotlin
val intArray = intArrayOf(1, 2, 3)
val charArray = charArrayOf('a', 'b', 'c')
```

Use:

* `array[i]` to access
* `.size`, `.first()`, `.last()`

---

## ✨ Special Types

### `Any`

* The **superclass** of all Kotlin types.
* Similar to `Object` in Java.

```kotlin
val value: Any = "Hello"
```

---

### `Nothing`

* Represents a value that **never exists**.
* Used in functions that **always throw**.

```kotlin
fun fail(): Nothing = throw Exception("Error")
```

---

### `Unit`

* The return type of functions with **no meaningful value**.
* Equivalent to `void` in Java, but it's a real type.

```kotlin
fun printMessage(): Unit {
    println("Done!")
}
```

---

## 🧠 Type Checking and Casting

Kotlin has smart casting:

```kotlin
fun printLength(obj: Any) {
    if (obj is String) {
        println(obj.length) // Smart cast to String
    }
}
```

Use `as?` for safe cast:

```kotlin
val str = obj as? String
```

---

## ✅ Summary

| Type       | Description                           |
| ---------- | ------------------------------------- |
| Numbers    | Byte, Short, Int, Long, Float, Double |
| Booleans   | true or false logic values            |
| Characters | Single characters like `'A'`          |
| Strings    | Immutable text values                 |
| Arrays     | Fixed-size collections                |
| Any        | Supertype of all Kotlin types         |
| Nothing    | Used for "no return" scenarios        |
| Unit       | Void-like return type                 |

---

Let me know if you'd like this turned into a PDF, HTML, or Kobweb-friendly page!

```
```
