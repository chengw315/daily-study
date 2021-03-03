package daily;

import java.util.LinkedList;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/28
 **/
public class LeetCode104 {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(20);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(7);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(7);
        treeNode1.left = treeNode5;
        treeNode5.left = treeNode6;
        treeNode5.right = treeNode7;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;

        treeNode3.right = treeNode8;
        treeNode4.right = treeNode9;
        int i = new LeetCode104().maxDepth(treeNode);
        int i2 = new LeetCode104().maxDepthDivide(treeNode);
    }

    public int maxDepth(TreeNode root) {
        int result = 0;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        if(root != null) nodes.offer(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = nodes.poll();
                if(poll.left != null) nodes.offer(poll.left);
                if(poll.right != null) nodes.offer(poll.right);
            }
            result++;
        }
        return result;
    }

    //分治法
    public int maxDepthDivide(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepthDivide(root.left),maxDepthDivide(root.right));
    }
}
