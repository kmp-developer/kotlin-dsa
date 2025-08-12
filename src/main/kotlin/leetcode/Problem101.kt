package naser09.github.io.dsa.leetcode

class Problem101 {
    fun isSymmetric(root: TreeNode?): Boolean {
        val queue = ArrayDeque<TreeNode?>()
        queue.add(root)
        while (queue.isNotEmpty()){
            val stack = arrayListOf<TreeNode?>()
            val levelCount = queue.size
            repeat(levelCount){
                val c = queue.removeFirst()
                stack.add(c)
                queue.add(c?.left)
                queue.add(c?.right)
            }
            repeat(levelCount/2){i->
                println("i =  $i")
                println(stack[i].toString()+" : "+ stack[levelCount-1-i])
                println("asdf ${stack[i] != stack[levelCount-1-i]}")
                if (stack[i]==null && stack[levelCount-1-i] == null){
                    println("both null ?")
                }else if (stack[i]==null || stack[levelCount-1-i] == null) return false
                if (stack[i]?.`val` != stack[levelCount-1-i]?.`val`) return false
            }
        }
        return true
    }

    fun printResult(){
        val m = TreeNode(0)
        m.left = TreeNode(1)
        m.right = TreeNode(1)
        m.left?.left = TreeNode(2)
        m.right?.right = TreeNode(2)
        m.left?.right = TreeNode(3)
        m.right?.left = TreeNode(3)
//        m.right?.left?.left = null
        println(isSymmetric(m))
    }
}