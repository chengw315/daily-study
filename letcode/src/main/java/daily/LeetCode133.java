package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/8/12
 **/
public class LeetCode133 {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node2.neighbors.add(node1);
        node2.neighbors.add(node4);
        node3.neighbors.add(node1);
        node3.neighbors.add(node4);
        node4.neighbors.add(node2);
        node4.neighbors.add(node3);
        Node node = new LeetCode133().cloneGraph(node1);
    }

    Node[] nodes = new Node[101];

    public Node cloneGraph(Node node) {
        if(node == null) return null;
        if (nodes[node.val] != null) return nodes[node.val];
        Node result = new Node(node.val);
        nodes[node.val] = result;
        for (int i = 0; i < node.neighbors.size(); i++) {
            result.neighbors.add(cloneGraph(node.neighbors.get(i)));
        }
        return result;
    }
}
