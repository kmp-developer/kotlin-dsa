In Kotlin, arrays are represented by the `Array` class, and they are built on top of Java's native arrays under the hood (`T[]`). Here's a detailed breakdown with examples and explanation.

---

## ✅ Creating Arrays in Kotlin

### 1. **Using `arrayOf()`**

```kotlin
val numbers = arrayOf(1, 2, 3, 4, 5)
val names = arrayOf("Alice", "Bob", "Charlie")
```

### 2. **Using Array constructor**

```kotlin
val squares = Array(5) { index -> index * index }
// Output: [0, 1, 4, 9, 16]
```

### 3. **Primitive arrays (efficient, avoids boxing)**

Kotlin has special classes for primitive arrays to avoid boxing overhead:

```kotlin
val intArray = intArrayOf(1, 2, 3)      // type: IntArray
val doubleArray = doubleArrayOf(1.0, 2.0) // type: DoubleArray
```

---

## 🔍 What Is Underneath?

Under the hood, Kotlin arrays use **Java arrays**:

* `Array<T>` → wraps Java array `T[]`
* `IntArray`, `DoubleArray`, etc. → use primitive arrays: `int[]`, `double[]`, etc.

### Example: Kotlin → Java Interop

```kotlin
fun main() {
    val arr = arrayOf("A", "B", "C")
    println(arr::class) // class kotlin.Array
    println(arr.javaClass) // class [Ljava.lang.String; ← Java native array
}
```

---

## 📦 How `Array<T>` Works Internally

Here’s a **simplified version** of how Kotlin’s `Array<T>` might be implemented internally:

```kotlin
class SimpleArray<T>(size: Int, init: (Int) -> T) {
    private val data: Array<Any?> = arrayOfNulls(size)

    init {
        for (i in 0 until size) {
            data[i] = init(i)
        }
    }

    @Suppress("UNCHECKED_CAST")
    operator fun get(index: Int): T = data[index] as T

    operator fun set(index: Int, value: T) {
        data[index] = value
    }

    val size: Int get() = data.size
}
```

This is **not** how Kotlin actually implements it in the standard library (which uses platform types for performance), but it gives you a conceptual understanding.

---

## 💡 Notes

| Kotlin Class  | Backed by Java | Notes                                         |
| ------------- | -------------- | --------------------------------------------- |
| `Array<T>`    | `T[]`          | Works with any type, but boxed for primitives |
| `IntArray`    | `int[]`        | Specialized, avoids boxing                    |
| `DoubleArray` | `double[]`     | Same as above                                 |
| etc.          |                |                                               |

---

## 🔄 Why Use `IntArray`, `DoubleArray`, etc.?

Because `Array<Int>` stores **boxed** `Integer` objects, which is slower and takes more memory than using `IntArray`, which uses **primitive** `int` values.

```kotlin
val boxed = Array(1000) { it }    // Boxed: Array<Int>
val primitive = IntArray(1000) { it } // Primitive: int[]
```
## Custom kotlin dynamic array
```kotlin
class DynamicArray<T> {
    private var data: Array<Any?> = arrayOfNulls(10)
    private var _size = 0

    val size: Int get() = _size
    val capacity: Int get() = data.size
    val isEmpty: Boolean get() = _size == 0

    fun add(element: T) {
        ensureCapacity()
        data[_size] = element
        _size++
    }

    fun add(index: Int, element: T) {
        checkIndexForAdd(index)
        ensureCapacity()
        for (i in _size downTo index + 1) {
            data[i] = data[i - 1]
        }
        data[index] = element
        _size++
    }

    @Suppress("UNCHECKED_CAST")
    operator fun get(index: Int): T {
        checkIndex(index)
        return data[index] as T
    }

    operator fun set(index: Int, element: T) {
        checkIndex(index)
        data[index] = element
    }

    fun removeAt(index: Int): T {
        checkIndex(index)
        val removed = data[index]
        for (i in index until _size - 1) {
            data[i] = data[i + 1]
        }
        data[_size - 1] = null
        _size--
        return removed as T
    }

    fun clear() {
        for (i in 0 until _size) {
            data[i] = null
        }
        _size = 0
    }

    private fun ensureCapacity() {
        if (_size >= data.size) {
            val newCapacity = data.size * 2
            data = data.copyOf(newCapacity)
        }
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= _size) throw IndexOutOfBoundsException("Index: $index, Size: $_size")
    }

    private fun checkIndexForAdd(index: Int) {
        if (index < 0 || index > _size) throw IndexOutOfBoundsException("Index: $index, Size: $_size")
    }

    override fun toString(): String {
        val elements = (0 until _size).joinToString(", ") { data[it].toString() }
        return "[$elements]"
    }
}

```

---