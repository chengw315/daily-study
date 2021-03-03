package dataStruct;

import java.util.Objects;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/13
 **/
public class BinarySearchTree<T extends Comparable> {

    private TreeNode root;

    class TreeNode {
        TreeNode left;
        TreeNode right;
        T val;
        TreeNode(T t) {
            this.val = t;
        }
    }

    void add(T t) {
        if(root == null) { root = new TreeNode(t); return;}
        TreeNode parent = root;
        TreeNode cur = root;
        boolean left = true;
        while (cur != null) {
            parent = cur;
            if(t.compareTo(cur.val) < 0) {cur = cur.left;left = true;}
            else {cur = cur.right;left = false;}
        }
        if(left) parent.left = new TreeNode(t);
        else parent.right = new TreeNode(t);
    }

    boolean remove(T t) {
        if(root == null) return false;
        if (Objects.equals(root.val, t)) {root = removeNode(root); return true;}
        TreeNode parent = root;
        TreeNode cur = root;
        boolean left = true;
        while (cur != null) {
            if (Objects.equals(cur.val, t)) {
                if(left) {
                    parent.left = removeNode(cur);
                } else parent.right = removeNode(cur);
                return true;
            }
            parent = cur;
            if(t.compareTo(cur.val) < 0) {cur = cur.left;left = true;}
            else {cur = cur.right;left = false;}
        }
        return false;
    }

    /**
     * 移除一个节点,返回移除后取代此节点位置的子节点
     * 优先左节点上移，右节点归至左节点最右侧
     * @param node
     * @return
     */
    private TreeNode removeNode(TreeNode node) {
        if (node.left == null) return node.right;
        TreeNode parent = node.left;
        TreeNode cur = node.left;
        while (cur != null) {
            parent = cur;
            cur = cur.right;
        }
        parent.right = node.right;
        return node.left;
    }

    boolean contains(T t) {
        TreeNode cur = root;
        while (cur != null) {
            if (Objects.equals(cur.val, t)) { return true;}
            if(t.compareTo(cur.val) < 0) {cur = cur.left;}
            else {cur = cur.right;}
        }
        return false;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(1);
        binarySearchTree.add(10);
        binarySearchTree.add(18);
        binarySearchTree.add(95);
        binarySearchTree.add(5);
        binarySearchTree.add(9);
        binarySearchTree.add(2);
        binarySearchTree.add(3);
        binarySearchTree.add(33);
        binarySearchTree.add(38);
        boolean contains = binarySearchTree.contains(38);
        boolean contains2 = binarySearchTree.contains(99);
        binarySearchTree.add(99);
        boolean contains3 = binarySearchTree.contains(99);
        boolean remove = binarySearchTree.remove(38);
        boolean contains4 = binarySearchTree.contains(38);
    }
}
