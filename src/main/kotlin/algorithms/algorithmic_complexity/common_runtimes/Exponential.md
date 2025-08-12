# O(2^n) - Exponential Time (ELI15)

Imagine you have a bunch of friends, and you want to find the perfect group to invite to a party. You have to try every single combination of friends to see which group is the best. 

As you add more friends, the number of combinations explodes! This is **O(2^n)** or **exponential time**. These algorithms are very slow and only work for a small number of items.

Here's a Kotlin example (a recursive way to calculate Fibonacci numbers):

```kotlin
fun fibonacci(n: Int): Int {
    if (n <= 1) return n
    return fibonacci(n - 1) + fibonacci(n - 2) // This calls itself twice for each number
}
```
