package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/9/24
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public int getVal() {
        return val;
    }

    public TreeNode setVal(int val) {
        this.val = val;
        return this;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode setLeft(TreeNode left) {
        this.left = left;
        return this;
    }

    public TreeNode getRight() {
        return right;
    }

    public TreeNode setRight(TreeNode right) {
        this.right = right;
        return this;
    }
}
