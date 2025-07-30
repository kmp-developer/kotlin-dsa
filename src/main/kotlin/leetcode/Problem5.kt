package naser09.github.io.dsa.leetcode

import java.util.Stack

class SolutionToProblem5 {
    /**
     * "babad"
     * "cbbd" = c ??
     */
    fun longestPalindrome(s: String): String {
        var longest = ""
        var max = 0
        for (index in s.indices){
            var left = index
            var right = index
            //odd match
            while (left>=0 && right<=s.lastIndex && s[left]==s[right]){
                if (((right+1)-left) > max){
                    max = (right+1)-left
                    longest  = s.substring(left,right+1)
                }
                left--
                right++
            }
            //even match
            right = index+1
            left=index
            while (left>=0 && right<=s.lastIndex && s[left]==s[right]){
                if (((right+1)-left) > max){
                    max = (right+1)-left
                    longest  = s.substring(left,right+1)
                }
                left--
                right++
            }
        }
        return longest
    }
}