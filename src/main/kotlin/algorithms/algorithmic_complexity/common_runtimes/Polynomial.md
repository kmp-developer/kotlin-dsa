# O(n^k) - Polynomial Time (ELI15)

Imagine you have a list of people, and you want to find out if any two of them have the same birthday. You have to compare each person with every other person.

If you have `n` people, you have to do `n * n` comparisons. This is **O(n^2)**, which is a type of **polynomial time**. It gets slow pretty quickly as the number of people grows.

Here's a Kotlin example:

```kotlin
fun hasDuplicate(items: List<Any>): Boolean {
    for (i in items.indices) {
        for (j in i + 1 until items.size) { // For each item, we compare it to every other item
            if (items[i] == items[j]) {
                return true
            }
        }
    }
    return false
}
```
