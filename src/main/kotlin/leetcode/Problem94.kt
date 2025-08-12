package naser09.github.io.dsa.leetcode

//  Definition for a binary tree node.
  class TreeNode(var `val`: Int) {
      var left: TreeNode? = null
      var right: TreeNode? = null
    override fun toString(): String {
        return "TreeNode($`val`)"
    }
  }

class Problem94 {
     fun inorderTraversal(root: TreeNode?): List<Int> {
         val result = mutableListOf<Int>()
         inOrderTree(root,result)
         return result
    }
    tailrec fun inOrderTree(treeNode: TreeNode?,mutableList: MutableList<Int>){
        if (treeNode == null) return
        inOrderTree(treeNode.left,mutableList)
        mutableList.add(treeNode.`val`)
        inOrderTree(treeNode.right,mutableList)
    }
    fun inOrderIterative(treeNode: TreeNode?): List<Int>{
        if (treeNode == null) return emptyList()
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode>()
        var current = treeNode
        while (stack.isNotEmpty() || current != null){
            while (current!=null){
                stack.add(current)
                current = current.left
            }
            result.add(stack.last().`val`)
            stack.last().right?.let { current = it }
            stack.removeLast()
        }
        return result
    }
    fun printResult(){
        var ti = TreeNode(5)
         ti.left = TreeNode(10)
        ti.right = TreeNode(9)
        ti.left?.left = TreeNode(12)
        ti.left?.right = TreeNode(120)
        println(inorderTraversal(ti))
        println("iterative .........")
        println(inOrderIterative(ti))
    }
}