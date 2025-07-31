package naser09.github.io.dsa.leetcode

import kotlin.math.max

class Problem11 {
    //[1,8,6,2,5,4,8,3,7]
    /**
     * best answer .
     */
    fun maxArea(height: IntArray): Int{
        var result = 0
        var left = 0
        var right = height.lastIndex
        var lowerHeight = 0
        while (left < right){
            lowerHeight = minOf(height[left],height[right])
            result = maxOf(result , lowerHeight * (right-left))
            while (left < right && height[left] <= lowerHeight){
                left++
            }
            while (right > left && lowerHeight >= height[right]){
                right--
            }
        }
        return result
    }
    fun maxArea16MS(height: IntArray): Int {
        var left = 0
        var right = height.lastIndex
        var maxArea = 0
        var maxMin = 0
        while (left != right && right>0 && left < height.lastIndex){
            if (maxMin > minOf(height[left],height[right]) ){
                if (height[left] > height[right]){
                    right--
                }else left++
                continue
            }else {
                maxMin = minOf(height[left],height[right])
            }
            maxArea = maxOf(
                maxArea,
                minOf(height[left],height[right]) * (right-left)
            )
            println("left = $left , right = $right and maxArea = ${maxArea} , minHeght = ${minOf(height[left],height[right])}")
            if (height[left] > height[right]){
                right--
            }else left++
        }
        return maxArea
    }
    fun printResult(){
        val array1 = intArrayOf(1,8,6,2,5,4,8,3,7)
        println(maxArea(array1))
        println(maxArea(intArrayOf(1,1)))
    }
}