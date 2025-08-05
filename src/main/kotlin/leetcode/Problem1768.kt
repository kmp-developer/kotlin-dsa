package naser09.github.io.dsa.leetcode

class Problem1768 {
    fun mergeAlternately107MS(word1: String, word2: String): String {
        var merged = ""
        var index = 0
        while (index < maxOf(word1.length,word2.length)){
            val first = word1.getOrNull(index)
            val second = word2.getOrNull(index)
            merged = "$merged${first?:""}${second?:""}"
            index++
        }
        return merged
    }
    fun mergeAlternately(word1: String, word2: String): String {
        var merged = ""
        var index = 0
        while (index < maxOf(word1.length,word2.length)){
            merged = "$merged${word1.getOrNull(index)?:""}${word2.getOrNull(index)?:""}"
            index++
        }
        return merged
    }
    fun mergeAlternatelyFailed(word1: String, word2: String): String {
        val max = maxOf(word1.lastIndex,word2.lastIndex)
        val array = arrayOfNulls<Char>(word1.length+word2.length)
        for (index in 0..max){
            array[index*2-1] = word2[index]
        }
        return array.joinToString("")
    }
    fun printResult(){
        println(mergeAlternately("abs","dersd"))
    }
}