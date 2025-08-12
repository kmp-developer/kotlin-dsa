# How to Calculate Complexity (ELI15)

Imagine you have a list of things to do. 

*   If you have to do something for every single item on the list, the time it takes is related to the number of items. If you have `n` items, we say the complexity is **O(n)** (linear).

*   If you have a list of `n` items, and for each item, you have to do something with every other item, that's like `n * n` or `n^2`. We say the complexity is **O(n^2)** (polynomial).

*   If you can cut the list in half each time you look for something (like in a dictionary), that's really fast! We call this **O(log n)** (logarithmic).

Let's look at a simple example in Kotlin:

```kotlin
fun findNumber(numbers: List<Int>, target: Int): Boolean {
    for (number in numbers) { // This loop runs 'n' times, where n is the size of the list
        if (number == target) {
            return true
        }
    }
    return false
}
```

This function has a time complexity of **O(n)** because in the worst case, we have to look at every single number in the list.
