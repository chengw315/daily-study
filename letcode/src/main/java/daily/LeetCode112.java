package daily;

/**
 * description 树是否存在一条和为sum的路径
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/7
 **/
public class LeetCode112 {

    public static void main(String[] args) {

        TreeNode root2 = new TreeNode(-2);
        TreeNode node22 = new TreeNode(-3);
        root2.right = node22;
        //true
        boolean b2 = new LeetCode112().hasPathSum(root2, -5);

        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(11);
        node1.left = node3;
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        node2.left = node4;
        node2.right = node5;
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        node3.left = node6;
        node3.right = node7;
        TreeNode node8 = new TreeNode(1);
        node5.right = node8;
        //true
        boolean b = new LeetCode112().hasPathSum(root, 22);

        TreeNode root1 = new TreeNode(1);
        TreeNode node12 = new TreeNode(2);
        root1.left = node12;
        //false
        boolean b1 = new LeetCode112().hasPathSum(root1, 1);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        if(sum - root.val == 0 && root.left == null && root.right == null) {
            return true;
        }
        if(hasPathSum(root.left,sum -root.val)) {
            return true;
        }
        if(hasPathSum(root.right,sum -root.val)) {
            return true;
        }
        return false;
    }
}
