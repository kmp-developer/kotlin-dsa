# O(n) - Linear Time (ELI15)

Imagine you have a stack of papers, and you're looking for one with your name on it. You have to look at each paper, one by one, until you find it. If you have twice as many papers, it will take you twice as long.

This is **O(n)** or **linear time**. The time it takes to do something grows in a straight line with the amount of stuff you have.

Here's a Kotlin example:

```kotlin
fun findItem(items: List<String>, target: String): Boolean {
    for (item in items) { // We have to look at every item in the worst case
        if (item == target) {
            return true
        }
    }
    return false
}
```
