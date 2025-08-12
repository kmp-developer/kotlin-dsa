# O(1) - Constant Time (ELI15)

Imagine you have a box of toys, and you know exactly where your favorite toy is. You can just reach in and grab it. It doesn't matter if you have 10 toys or 100 toys, it always takes the same amount of time.

That's **O(1)** or **constant time**. The time it takes to do something doesn't change, even if the amount of stuff you're working with gets bigger.

Here's a Kotlin example:

```kotlin
fun getFirstItem(items: List<String>): String? {
    return items.firstOrNull() // This always takes the same amount of time
}
```
