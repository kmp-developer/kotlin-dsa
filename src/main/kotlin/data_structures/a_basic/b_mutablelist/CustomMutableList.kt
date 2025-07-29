package naser09.github.io.dsa.data_structures.a_basic.b_mutablelist

// CUSTOM MUTABLE LIST IMPLEMENTATION
// This implements the same interface as Kotlin's MutableList

class CustomMutableList<T> : MutableList<T> {
    private var data = arrayOfNulls<Any?>(4)
    private var _size = 0

    // === SIZE AND CAPACITY ===
    override val size: Int get() = _size

    fun capacity(): Int = data.size

    override fun isEmpty(): Boolean = _size == 0

    // === ACCESS OPERATIONS ===
    override fun get(index: Int): T {
        checkElementIndex(index)
        @Suppress("UNCHECKED_CAST")
        return data[index] as T
    }

    override fun set(index: Int, element: T): T {
        checkElementIndex(index)
        @Suppress("UNCHECKED_CAST")
        val oldValue = data[index] as T
        data[index] = element
        return oldValue
    }

    // === ADD OPERATIONS ===
    override fun add(element: T): Boolean {
        ensureCapacity()
        data[_size] = element
        _size++
        return true
    }

    override fun add(index: Int, element: T) {
        checkPositionIndex(index)
        ensureCapacity()

        // Shift elements to the right
        for (i in _size downTo index + 1) {
            data[i] = data[i - 1]
        }

        data[index] = element
        _size++
    }

    override fun addAll(elements: Collection<T>): Boolean {
        if (elements.isEmpty()) return false

        ensureCapacity(_size + elements.size)
        for (element in elements) {
            data[_size] = element
            _size++
        }
        return true
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        checkPositionIndex(index)
        if (elements.isEmpty()) return false

        val elementsArray = elements.toList()
        ensureCapacity(_size + elementsArray.size)

        // Shift existing elements to the right
        for (i in _size - 1 downTo index) {
            data[i + elementsArray.size] = data[i]
        }

        // Insert new elements
        for ((i, element) in elementsArray.withIndex()) {
            data[index + i] = element
        }

        _size += elementsArray.size
        return true
    }

    // === REMOVE OPERATIONS ===
    override fun remove(element: T): Boolean {
        val index = indexOf(element)
        if (index >= 0) {
            removeAt(index)
            return true
        }
        return false
    }

    override fun removeAt(index: Int): T {
        checkElementIndex(index)

        @Suppress("UNCHECKED_CAST")
        val removedElement = data[index] as T

        // Shift elements to the left
        for (i in index until _size - 1) {
            data[i] = data[i + 1]
        }

        _size--
        data[_size] = null

        // Shrink if necessary
        if (_size <= data.size / 4 && data.size > 4) {
            shrink()
        }

        return removedElement
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var modified = false
        val elementsSet = elements.toSet() // For O(1) lookup

        var writeIndex = 0
        for (readIndex in 0 until _size) {
            @Suppress("UNCHECKED_CAST")
            val element = data[readIndex] as T

            if (element !in elementsSet) {
                data[writeIndex] = element
                writeIndex++
            } else {
                modified = true
            }
        }

        // Clear remaining elements
        for (i in writeIndex until _size) {
            data[i] = null
        }

        _size = writeIndex
        return modified
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var modified = false
        val elementsSet = elements.toSet()

        var writeIndex = 0
        for (readIndex in 0 until _size) {
            @Suppress("UNCHECKED_CAST")
            val element = data[readIndex] as T

            if (element in elementsSet) {
                data[writeIndex] = element
                writeIndex++
            } else {
                modified = true
            }
        }

        // Clear remaining elements
        for (i in writeIndex until _size) {
            data[i] = null
        }

        _size = writeIndex
        return modified
    }

    override fun clear() {
        for (i in 0 until _size) {
            data[i] = null
        }
        _size = 0
    }

    // === SEARCH OPERATIONS ===
    override fun indexOf(element: T): Int {
        for (i in 0 until _size) {
            if (data[i] == element) return i
        }
        return -1
    }

    override fun lastIndexOf(element: T): Int {
        for (i in _size - 1 downTo 0) {
            if (data[i] == element) return i
        }
        return -1
    }

    override fun contains(element: T): Boolean = indexOf(element) >= 0

    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    // === SUBLIST OPERATIONS ===
    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        checkRangeIndexes(fromIndex, toIndex)

        val sublist = CustomMutableList<T>()
        for (i in fromIndex until toIndex) {
            @Suppress("UNCHECKED_CAST")
            sublist.add(data[i] as T)
        }
        return sublist
    }

    // === ITERATOR OPERATIONS ===
    override fun iterator(): MutableIterator<T> = CustomMutableIterator(0)

    override fun listIterator(): MutableListIterator<T> = CustomMutableListIterator(0)

    override fun listIterator(index: Int): MutableListIterator<T> {
        checkPositionIndex(index)
        return CustomMutableListIterator(index)
    }

    // === CUSTOM ITERATOR IMPLEMENTATIONS ===
    private inner class CustomMutableIterator(private var index: Int) : MutableIterator<T> {
        private var lastReturnedIndex = -1

        override fun hasNext(): Boolean = index < _size

        override fun next(): T {
            if (!hasNext()) throw NoSuchElementException()
            lastReturnedIndex = index
            return get(index++)
        }

        override fun remove() {
            if (lastReturnedIndex < 0) throw IllegalStateException()
            removeAt(lastReturnedIndex)
            index = lastReturnedIndex
            lastReturnedIndex = -1
        }
    }

    private inner class CustomMutableListIterator(private var index: Int) : MutableListIterator<T> {
        private var lastReturnedIndex = -1

        override fun hasNext(): Boolean = index < _size
        override fun hasPrevious(): Boolean = index > 0
        override fun nextIndex(): Int = index
        override fun previousIndex(): Int = index - 1

        override fun next(): T {
            if (!hasNext()) throw NoSuchElementException()
            lastReturnedIndex = index
            return get(index++)
        }

        override fun previous(): T {
            if (!hasPrevious()) throw NoSuchElementException()
            lastReturnedIndex = --index
            return get(index)
        }

        override fun add(element: T) {
            add(index++, element)
            lastReturnedIndex = -1
        }

        override fun set(element: T) {
            if (lastReturnedIndex < 0) throw IllegalStateException()
            set(lastReturnedIndex, element)
        }

        override fun remove() {
            if (lastReturnedIndex < 0) throw IllegalStateException()
            removeAt(lastReturnedIndex)
            if (lastReturnedIndex < index) index--
            lastReturnedIndex = -1
        }
    }

    // === HELPER METHODS ===
    private fun ensureCapacity(minCapacity: Int = _size + 1) {
        if (minCapacity > data.size) {
            val newCapacity = maxOf(data.size * 2, minCapacity)
            resize(newCapacity)
        }
    }

    private fun shrink() {
        resize(data.size / 2)
    }

    private fun resize(newCapacity: Int) {
        val newData = arrayOfNulls<Any?>(newCapacity)
        for (i in 0 until _size) {
            newData[i] = data[i]
        }
        data = newData
    }

    private fun checkElementIndex(index: Int) {
        if (index < 0 || index >= _size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $_size")
        }
    }

    private fun checkPositionIndex(index: Int) {
        if (index < 0 || index > _size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $_size")
        }
    }

    private fun checkRangeIndexes(fromIndex: Int, toIndex: Int) {
        if (fromIndex < 0 || toIndex > _size || fromIndex > toIndex) {
            throw IndexOutOfBoundsException("fromIndex: $fromIndex, toIndex: $toIndex, size: $_size")
        }
    }

    // === UTILITY METHODS ===
    override fun toString(): String {
        if (isEmpty()) return "[]"

        val sb = StringBuilder("[")
        for (i in 0 until _size) {
            if (i > 0) sb.append(", ")
            sb.append(data[i])
        }
        sb.append("]")
        return sb.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is List<*>) return false
        if (size != other.size) return false

        for (i in 0 until size) {
            if (get(i) != other[i]) return false
        }
        return true
    }

    override fun hashCode(): Int {
        var hashCode = 1
        for (i in 0 until _size) {
            hashCode = 31 * hashCode + (data[i]?.hashCode() ?: 0)
        }
        return hashCode
    }
}

// === EXTENSION FUNCTIONS (bonus features) ===
fun <T> CustomMutableList<T>.addFirst(element: T) = add(0, element)
fun <T> CustomMutableList<T>.addLast(element: T) = add(element)
fun <T> CustomMutableList<T>.removeFirst(): T = removeAt(0)
fun <T> CustomMutableList<T>.removeLast(): T = removeAt(size - 1)
fun <T> CustomMutableList<T>.first(): T = get(0)
fun <T> CustomMutableList<T>.last(): T = get(size - 1)
fun <T> CustomMutableList<T>.firstOrNull(): T? = if (isEmpty()) null else get(0)
fun <T> CustomMutableList<T>.lastOrNull(): T? = if (isEmpty()) null else get(size - 1)

// === FACTORY FUNCTIONS ===
fun <T> customMutableListOf(vararg elements: T): CustomMutableList<T> {
    val list = CustomMutableList<T>()
    for (element in elements) {
        list.add(element)
    }
    return list
}

fun <T> customMutableListOf(): CustomMutableList<T> = CustomMutableList()

// === COMPREHENSIVE DEMONSTRATION ===
fun demonstrateCustomMutableList() {
    println("=== CUSTOM MUTABLE LIST DEMONSTRATION ===\n")

    // 1. Basic operations
    println("1. BASIC OPERATIONS")
    val list = customMutableListOf("Apple", "Banana", "Cherry")
    println("Created list: $list")
    println("Size: ${list.size}, Capacity: ${list.capacity()}")
    println("Get(1): ${list[1]}")
    println("Set(1, 'Blueberry'): ${list.set(1, "Blueberry")}")
    println("After set: $list")

    // 2. Add operations
    println("\n2. ADD OPERATIONS")
    list.add("Date")
    println("After add('Date'): $list")
    list.add(1, "Avocado")
    println("After add(1, 'Avocado'): $list")
    list.addAll(listOf("Elderberry", "Fig"))
    println("After addAll: $list")
    list.addAll(2, listOf("Grape", "Honeydew"))
    println("After addAll(2, ...): $list")

    // 3. Remove operations
    println("\n3. REMOVE OPERATIONS")
    println("Remove 'Grape': ${list.remove("Grape")}")
    println("After remove: $list")
    println("RemoveAt(0): ${list.removeAt(0)}")
    println("After removeAt: $list")
    list.removeAll(listOf("Blueberry", "Date"))
    println("After removeAll: $list")

    // 4. Search operations
    println("\n4. SEARCH OPERATIONS")
    println("Contains 'Cherry': ${list.contains("Cherry")}")
    println("IndexOf 'Fig': ${list.indexOf("Fig")}")
    println("LastIndexOf 'Cherry': ${list.lastIndexOf("Cherry")}")
    list.add("Cherry") // Add duplicate
    println("After adding duplicate Cherry: $list")
    println("LastIndexOf 'Cherry': ${list.lastIndexOf("Cherry")}")

    // 5. Iterator operations
    println("\n5. ITERATOR OPERATIONS")
    print("For-each loop: ")
    for (item in list) {
        print("$item ")
    }
    println()

    print("Iterator with removal: ")
    val iterator = list.iterator()
    while (iterator.hasNext()) {
        val item = iterator.next()
        if (item.startsWith("C")) {
            iterator.remove()
            print("[removed $item] ")
        } else {
            print("$item ")
        }
    }
    println("\nAfter iterator removal: $list")

    // 6. ListIterator operations
    println("\n6. LIST ITERATOR OPERATIONS")
    val listIterator = list.listIterator(1)
    print("Bidirectional iteration from index 1: ")

    // Go forward
    while (listIterator.hasNext()) {
        print("→${listIterator.next()} ")
    }

    // Go backward
    while (listIterator.hasPrevious()) {
        print("←${listIterator.previous()} ")
    }
    println()

    // 7. Bulk operations
    println("\n7. BULK OPERATIONS")
    val list2 = customMutableListOf("Avocado", "Banana", "Kiwi")
    println("List2: $list2")
    println("ContainsAll from list2: ${list.containsAll(listOf("Avocado", "Banana"))}")

    val originalList = customMutableListOf("A", "B", "C", "B", "D", "B")
    println("Original for retain test: $originalList")
    originalList.retainAll(listOf("B", "D"))
    println("After retainAll(['B', 'D']): $originalList")

    // 8. SubList operations
    println("\n8. SUBLIST OPERATIONS")
    val fruits = customMutableListOf("Apple", "Banana", "Cherry", "Date", "Elderberry")
    println("Original: $fruits")
    val sublist = fruits.subList(1, 4)
    println("SubList(1, 4): $sublist")

    // 9. Extension functions
    println("\n9. EXTENSION FUNCTIONS")
    val extList = customMutableListOf("Middle")
    extList.addFirst("First")
    extList.addLast("Last")
    println("After addFirst/addLast: $extList")
    println("First: ${extList.first()}, Last: ${extList.last()}")
    println("RemoveFirst: ${extList.removeFirst()}")
    println("RemoveLast: ${extList.removeLast()}")
    println("Final: $extList")

    // 10. Comparison with built-in MutableList
    println("\n10. COMPARISON WITH BUILT-IN MUTABLELIST")
    val builtInList = mutableListOf("A", "B", "C")
    val customList = customMutableListOf("A", "B", "C")

    println("Built-in: $builtInList")
    println("Custom:   $customList")
    println("Equal: ${customList == builtInList}")
    println("HashCode - Built-in: ${builtInList.hashCode()}, Custom: ${customList.hashCode()}")

    // Demonstrate they work the same way
    builtInList.add(1, "X")
    customList.add(1, "X")
    println("After add(1, 'X'):")
    println("Built-in: $builtInList")
    println("Custom:   $customList")
    println("Still equal: ${customList == builtInList}")
}