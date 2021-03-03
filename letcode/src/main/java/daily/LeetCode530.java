package daily;

/**
 * description 二叉搜索树两个节点间的最小差
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/12
 **/
public class LeetCode530 {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node.right = node3;
        node3.left = node2;
        int minimumDifference = new LeetCode530().getMinimumDifference(node);
    }

    private Integer last = null;
    private int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if(root == null) {
            return 0;
        }
        midTraverse(root);
        return min;
    }
    private void midTraverse(TreeNode node) {
        if(node == null) {
            return;
        }
        midTraverse(node.left);
        if(last != null) {
            min = Math.min(Math.abs(node.val - last), min);
        }
        last = node.val;
        midTraverse(node.right);
    }
}
