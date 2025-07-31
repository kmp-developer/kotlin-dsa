package naser09.github.io.dsa.leetcode

class Probelm35 {
    fun searchInsert(nums: IntArray, target: Int): Int{
        var left = 0
        var right = nums.lastIndex
        var mid = left + (right-left) / 2
        while (left <= right){
            mid = left+(right-left)/2
            if (nums[mid] == target) return  mid
            if (target > nums[mid]){
                left = mid+1
            }else{
                right = mid-1
            }
        }
        return mid
    }
    fun searchInsertNONE(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size-1
        var mid = (right+left)/2
        //1,2,3,4,5,8,9,10
        //0 1 2 3 4 5 6 7
        while (left <= right){
            mid = left + (right - left) / 2
            println("start = $left end : $right mid = $mid = target $target")
            // Check if the target is present at mid
            if (nums[mid] == target) {
                return mid // Target found
            }

            // If target is greater, ignore left half
            if (nums[mid] < target) {
                left = mid + 1
            } else {
                // If target is smaller, ignore right half
                right = mid - 1
            }
        }
        return mid
    }
    fun printResult(){
        val data = Probelm35()
        println(data.searchInsert(intArrayOf(1,4,5,9,10),11))
    }
}