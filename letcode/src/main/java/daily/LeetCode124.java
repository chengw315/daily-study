package daily;

/**
 * description 寻找树的最大路径
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/18
 **/
public class LeetCode124 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        int sum1 = new LeetCode124().maxPathSum(node1);

        TreeNode root = new TreeNode(-10);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.left = left;
        root.right = right;
        TreeNode rightLeft = new TreeNode(15);
        TreeNode rightRight = new TreeNode(7);
        right.left = rightLeft;
        right.right = rightRight;
        int sum2 = new LeetCode124().maxPathSum(root);

        TreeNode root2 = new TreeNode(-3);
        int sum3 = new LeetCode124().maxPathSum(root2);
    }

    public int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSingleThrough(root);
        return max;
    }


    public int maxPathSingleThrough(TreeNode treeNode) {
        if(treeNode == null) {
            return Integer.MIN_VALUE;
        }
        int result = treeNode.val;
        int left = 0;
        int right = 0;
        if(treeNode.left != null) {
            left = Math.max(maxPathSingleThrough(treeNode.left),0);
        }
        if(treeNode.right != null) {
            right = Math.max(maxPathSingleThrough(treeNode.right),0);
        }
        max = Math.max(max,result + left +right);
        result += Math.max(left,right);
        return result;
    }


}
