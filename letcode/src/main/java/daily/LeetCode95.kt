package daily

fun main() {
    val solution = Solution95()
    solution.generateTrees(10)
}

/**
 * 节点1~n，生成所有可能的二叉搜索树
 */
class Solution95 {
    fun generateTrees(n: Int): List<TreeNode?> {

        return List<TreeNode>(1){ TreeNode(1) }
    }
}