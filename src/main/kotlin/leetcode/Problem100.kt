package naser09.github.io.dsa.leetcode

class Problem100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        val stackP = ArrayDeque<TreeNode?>()
        val stackQ = ArrayDeque<TreeNode?>()
        stackP.add(p)
        stackQ.add(q)
        while (stackQ.isNotEmpty() && stackP.isNotEmpty()){
            val currentP = stackP.removeLast()
            val currentQ = stackQ.removeLast()
            if (currentP == null && currentQ == null) continue
            if (currentQ == null || currentP == null) return false
            if (currentP.`val` != currentQ.`val`) return false
            currentP.right.let { stackP.add(it) }
            currentQ.right.let { stackQ.add(it) }
            currentP.left.let { stackP.add(it) }
            currentQ.left.let { stackQ.add(it) }
        }
        return stackQ.isEmpty() && stackP.isEmpty()
    }
    fun buildTree(values: List<Int?>): TreeNode? {
        if (values.isEmpty() || values[0] == null) return null
        val root = TreeNode(values[0]!!)
        val queue = ArrayDeque<TreeNode>()
        queue.add(root)
        var index = 1

        while (queue.isNotEmpty() && index < values.size) {
            val current = queue.removeFirst()
            if (index < values.size && values[index] != null) {
                current.left = TreeNode(values[index]!!)
                queue.add(current.left!!)
            }
            index++
            if (index < values.size && values[index] != null) {
                current.right = TreeNode(values[index]!!)
                queue.add(current.right!!)
            }
            index++
        }

        return root
    }
    fun printResult(){
        val tree1 = buildTree(listOf(1, null, 2))
        val tree2 = buildTree(listOf(1, 2))
        println(isSameTree(tree1, tree2))  // should print false

        val tree3 = buildTree(listOf(1, 2, 3))
        val tree4 = buildTree(listOf(1, 2, 3))
        println(isSameTree(tree3, tree4))
    }
}