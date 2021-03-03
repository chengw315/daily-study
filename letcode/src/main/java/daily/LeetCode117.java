package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/9/28
 **/
public class LeetCode117 {

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(7);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        Node connect = new LeetCode117().connect(node);
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node parent = root;
        Node firstSon = null;
        Node before = null;
        while (parent != null) {
            if(parent.left != null) {
                if(firstSon == null) {
                    firstSon = parent.left;
                }
                if (before != null) {
                    before.next = parent.left;
                }
                before = parent.left;
            }
            if(parent.right != null) {
                if(firstSon == null) {
                    firstSon = parent.right;
                }
                if (before != null) {
                    before.next = parent.right;
                }
                before = parent.right;
            }
            parent = parent.next;
            if (parent == null) {
                parent = firstSon;
                firstSon = null;
                before = null;
            }
        }
        return root;
    }
}
