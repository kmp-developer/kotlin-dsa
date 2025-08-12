package naser09.github.io.dsa.data_structures.a_basic.a_array

/**
 * An array is a data structure consisting of a collection of elements (values or variables),
 * each identified by at least one array index or key.
 */
fun arrayBasic(){
    demonstrateArrays()
}

// 1. BASIC FIXED-SIZE ARRAY
// This is the simplest array implementation with fixed size
class BasicArray<T>(private val size: Int) {
    private val data = arrayOfNulls<Any>(size)

    fun get(index: Int): T? {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index $index out of bounds for size $size")
        }
        @Suppress("UNCHECKED_CAST")
        return data[index] as T?
    }

    fun set(index: Int, value: T) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index $index out of bounds for size $size")
        }
        data[index] = value
    }

    fun size(): Int = size

    // Iterator operator for for-each loops
    operator fun iterator(): Iterator<T?> {
        return object : Iterator<T?> {
            private var index = 0

            override fun hasNext(): Boolean = index < size

            override fun next(): T? {
                if (!hasNext()) throw NoSuchElementException()
                @Suppress("UNCHECKED_CAST")
                return data[index++] as T?
            }
        }
    }

    override fun toString(): String {
        return data.joinToString(prefix = "[", postfix = "]")
    }
}

// 2. IMPROVED ARRAY WITH UTILITY METHODS
// Adds common array operations like fill, contains, etc.
class ImprovedArray<T>(private val size: Int) {
    private val data = arrayOfNulls<Any>(size)

    fun get(index: Int): T? {
        checkBounds(index)
        @Suppress("UNCHECKED_CAST")
        return data[index] as T?
    }

    fun set(index: Int, value: T) {
        checkBounds(index)
        data[index] = value
    }

    fun fill(value: T) {
        for (i in 0 until size) {
            data[i] = value
        }
    }

    fun contains(value: T): Boolean {
        for (i in 0 until size) {
            if (data[i] == value) return true
        }
        return false
    }

    fun indexOf(value: T): Int {
        for (i in 0 until size) {
            if (data[i] == value) return i
        }
        return -1
    }

    fun isEmpty(): Boolean {
        for (i in 0 until size) {
            if (data[i] != null) return false
        }
        return true
    }

    private fun checkBounds(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("Index $index out of bounds for size $size")
        }
    }

    fun size(): Int = size

    // Iterator operator for for-each loops
    operator fun iterator(): Iterator<T?> {
        return object : Iterator<T?> {
            private var index = 0

            override fun hasNext(): Boolean = index < size

            override fun next(): T? {
                if (!hasNext()) throw NoSuchElementException()
                @Suppress("UNCHECKED_CAST")
                return data[index++] as T?
            }
        }
    }

    override fun toString(): String {
        return data.joinToString(prefix = "[", postfix = "]")
    }
}

// 3. SIMPLE DYNAMIC ARRAY (RESIZABLE)
// This array can grow in size when needed
class SimpleDynamicArray<T> {
    private var data = arrayOfNulls<Any>(2) // Start with small capacity
    private var currentSize = 0

    // Add element at the end (alias for add)
    fun addLast(value: T) = add(value)

    fun add(value: T) {
        if (currentSize >= data.size) {
            resize()
        }
        data[currentSize] = value
        currentSize++
    }

    fun get(index: Int): T? {
        checkBounds(index)
        @Suppress("UNCHECKED_CAST")
        return data[index] as T?
    }

    fun set(index: Int, value: T) {
        checkBounds(index)
        data[index] = value
    }

    private fun resize() {
        val newCapacity = data.size * 2
        val newData = arrayOfNulls<Any>(newCapacity)
        for (i in 0 until currentSize) {
            newData[i] = data[i]
        }
        data = newData
        println("Resized array to capacity: $newCapacity")
    }

    private fun checkBounds(index: Int) {
        if (index < 0 || index >= currentSize) {
            throw IndexOutOfBoundsException("Index $index out of bounds for size $currentSize")
        }
    }

    fun size(): Int = currentSize
    fun capacity(): Int = data.size

    // Iterator operator for for-each loops
    operator fun iterator(): Iterator<T?> {
        return object : Iterator<T?> {
            private var index = 0

            override fun hasNext(): Boolean = index < currentSize

            override fun next(): T? {
                if (!hasNext()) throw NoSuchElementException()
                @Suppress("UNCHECKED_CAST")
                return data[index++] as T?
            }
        }
    }

    override fun toString(): String {
        return data.sliceArray(0 until currentSize).joinToString(prefix = "[", postfix = "]")
    }
}

// 4. ADVANCED DYNAMIC ARRAY
// Full-featured dynamic array with insert, remove, and more operations
class AdvancedDynamicArray<T> {
    private var data = arrayOfNulls<Any>(4)
    private var currentSize = 0

    // Add element at the end (alias for add)
    fun addLast(value: T) = add(value)

    // Add element at the beginning
    fun addFirst(value: T) {
        insert(0, value)
    }

    fun add(value: T) {
        ensureCapacity()
        data[currentSize] = value
        currentSize++
    }

    fun insert(index: Int, value: T) {
        if (index < 0 || index > currentSize) {
            throw IndexOutOfBoundsException("Index $index out of bounds for size $currentSize")
        }

        ensureCapacity()

        // Shift elements to the right
        for (i in currentSize downTo index + 1) {
            data[i] = data[i - 1]
        }

        data[index] = value
        currentSize++
    }

    fun removeAt(index: Int): T? {
        checkBounds(index)

        @Suppress("UNCHECKED_CAST")
        val removedValue = data[index] as T?

        // Shift elements to the left
        for (i in index until currentSize - 1) {
            data[i] = data[i + 1]
        }

        currentSize--
        data[currentSize] = null // Clear the last element

        // Shrink if array is too large
        if (currentSize <= data.size / 4 && data.size > 4) {
            shrink()
        }

        return removedValue
    }

    // Remove last element
    fun removeLast(): T? {
        if (isEmpty()) throw NoSuchElementException("Array is empty")
        return removeAt(currentSize - 1)
    }

    // Remove first element
    fun removeFirst(): T? {
        if (isEmpty()) throw NoSuchElementException("Array is empty")
        return removeAt(0)
    }

    fun remove(value: T): Boolean {
        val index = indexOf(value)
        if (index != -1) {
            removeAt(index)
            return true
        }
        return false
    }

    fun get(index: Int): T? {
        checkBounds(index)
        @Suppress("UNCHECKED_CAST")
        return data[index] as T?
    }

    fun set(index: Int, value: T) {
        checkBounds(index)
        data[index] = value
    }

    // Get first element
    fun first(): T? {
        if (isEmpty()) return null
        return get(0)
    }

    // Get last element
    fun last(): T? {
        if (isEmpty()) return null
        return get(currentSize - 1)
    }

    fun indexOf(value: T): Int {
        for (i in 0 until currentSize) {
            if (data[i] == value) return i
        }
        return -1
    }

    fun contains(value: T): Boolean = indexOf(value) != -1

    fun clear() {
        for (i in 0 until currentSize) {
            data[i] = null
        }
        currentSize = 0
    }

    fun isEmpty(): Boolean = currentSize == 0

    private fun ensureCapacity() {
        if (currentSize >= data.size) {
            resize(data.size * 2)
        }
    }

    private fun shrink() {
        resize(data.size / 2)
    }

    private fun resize(newCapacity: Int) {
        val newData = arrayOfNulls<Any>(newCapacity)
        for (i in 0 until currentSize) {
            newData[i] = data[i]
        }
        data = newData
        println("Resized array to capacity: $newCapacity")
    }

    private fun checkBounds(index: Int) {
        if (index < 0 || index >= currentSize) {
            throw IndexOutOfBoundsException("Index $index out of bounds for size $currentSize")
        }
    }

    fun size(): Int = currentSize
    fun capacity(): Int = data.size

    // Iterator support for for-each loops
    operator fun iterator(): Iterator<T?> {
        return object : Iterator<T?> {
            private var index = 0

            override fun hasNext(): Boolean = index < currentSize

            override fun next(): T? {
                if (!hasNext()) throw NoSuchElementException()
                @Suppress("UNCHECKED_CAST")
                return data[index++] as T?
            }
        }
    }

    override fun toString(): String {
        return data.sliceArray(0 until currentSize).joinToString(prefix = "[", postfix = "]")
    }
}

// DEMONSTRATION FUNCTION
fun demonstrateArrays() {
    println("=== BASIC ARRAY DEMO ===")
    val basicArray = BasicArray<String>(3)
    basicArray.set(0, "Hello")
    basicArray.set(1, "World")
    basicArray.set(2, "!")
    println("Basic Array: $basicArray")
    println("Element at index 1: ${basicArray.get(1)}")

    // Demonstrate iterator
    print("Iterating with for-each: ")
    for (element in basicArray) {
        print("$element ")
    }
    println()

    println("\n=== IMPROVED ARRAY DEMO ===")
    val improvedArray = ImprovedArray<Int>(5)
    improvedArray.fill(42)
    println("Filled Array: $improvedArray")
    improvedArray.set(2, 100)
    println("After setting index 2 to 100: $improvedArray")
    println("Contains 42: ${improvedArray.contains(42)}")
    println("Index of 100: ${improvedArray.indexOf(100)}")

    // Demonstrate iterator
    print("Iterating with for-each: ")
    for (element in improvedArray) {
        print("$element ")
    }
    println()

    println("\n=== SIMPLE DYNAMIC ARRAY DEMO ===")
    val dynamicArray = SimpleDynamicArray<String>()
    println("Initial capacity: ${dynamicArray.capacity()}")

    dynamicArray.add("First")
    dynamicArray.addLast("Second")  // Same as add()
    println("After adding 2 elements: $dynamicArray")
    println("Capacity: ${dynamicArray.capacity()}")

    dynamicArray.add("Third")
    println("After adding 3rd element: $dynamicArray")
    println("Capacity: ${dynamicArray.capacity()}")

    // Demonstrate iterator
    print("Iterating with for-each: ")
    for (element in dynamicArray) {
        print("$element ")
    }
    println()

    println("\n=== ADVANCED DYNAMIC ARRAY DEMO ===")
    val advancedArray = AdvancedDynamicArray<Int>()

    // Add elements using different methods
    advancedArray.add(10)
    advancedArray.addLast(20)  // Same as add()
    advancedArray.addFirst(5)  // Add at beginning
    advancedArray.add(30)
    advancedArray.addLast(40)
    advancedArray.addFirst(1)
    println("After adding elements: $advancedArray")
    println("Size: ${advancedArray.size()}, Capacity: ${advancedArray.capacity()}")

    // Access first and last elements
    println("First element: ${advancedArray.first()}")
    println("Last element: ${advancedArray.last()}")

    // Insert element
    advancedArray.insert(2, 999)
    println("After inserting 999 at index 2: $advancedArray")

    // Remove elements using different methods
    val removedLast = advancedArray.removeLast()
    println("Removed last element: $removedLast")
    println("Array after removing last: $advancedArray")

    val removedFirst = advancedArray.removeFirst()
    println("Removed first element: $removedFirst")
    println("Array after removing first: $advancedArray")

    // Remove by value
    advancedArray.remove(999)
    println("After removing 999: $advancedArray")

    // Demonstrate iterator
    println("Iterating through array:")
    for (element in advancedArray) {
        print("$element ")
    }
    println("\n")

    // Remove many elements to trigger shrinking
    repeat(3) {
        if (!advancedArray.isEmpty()) {
            advancedArray.removeAt(0)
        }
    }
    println("After removing 3 elements: $advancedArray")
    println("Size: ${advancedArray.size()}, Capacity: ${advancedArray.capacity()}")
}