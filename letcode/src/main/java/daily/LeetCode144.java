package daily;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * description 迭代法前序遍历二叉树
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/27
 **/
public class LeetCode144 {
    public static void main(String[] args) {
        new LeetCode144().preorderTraversal(new TreeNode(5));
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> nodes = new Stack<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            TreeNode pop = nodes.pop();
            if (pop != null) {
                result.add(pop.val);
                nodes.add(pop.right);
                nodes.add(pop.left);
            }
        }
        return result;
    }

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
