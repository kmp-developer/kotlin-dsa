# O(n!) - Factorial Time (ELI15)

Imagine you're a traveling salesperson, and you want to find the shortest possible route that visits every city exactly once. You have to try every single possible order of cities.

This is **O(n!)** or **factorial time**. It's the slowest of the slow! Even for a small number of cities, the number of possible routes is enormous.

Here's a Kotlin example (a simple way to find all permutations of a list):

```kotlin
fun <T> permutations(list: List<T>): List<List<T>> {
    if (list.isEmpty()) return listOf(emptyList())

    val result: MutableList<List<T>> = mutableListOf()
    for (i in list.indices) {
        permutations(list - list[i]).forEach {
            result.add(it + list[i])
        }
    }
    return result
}
```
