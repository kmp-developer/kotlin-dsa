# O(log n) - Logarithmic Time (ELI15)

Imagine you're looking for a word in a dictionary. You don't start at the first page and read every word. You open it to the middle, see if your word is before or after, and then you know which half of the dictionary to look in. You keep doing this, cutting the problem in half each time.

This is **O(log n)** or **logarithmic time**. It's super fast because you get to ignore a huge chunk of the problem with each step.

Here's a Kotlin example (binary search):

```kotlin
fun binarySearch(sortedItems: List<Int>, target: Int): Boolean {
    var low = 0
    var high = sortedItems.size - 1

    while (low <= high) {
        val mid = (low + high) / 2
        when {
            sortedItems[mid] == target -> return true
            sortedItems[mid] < target -> low = mid + 1
            else -> high = mid - 1
        }
    }
    return false
}
```
