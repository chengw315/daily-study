import java.util.Iterator;
import java.util.LinkedList;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/23
 **/
public class AnyTest {
    class Node {
        Integer val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public void insert(int i) {
            Node cur = this;
            while (cur.next != null && cur.next.val < i) {
                cur = cur.next;
            }
            Node newNode = new Node(i);
            newNode.next = cur.next;
            cur.next = newNode;
        }
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
    public void bucketSort(int[] a) {
        int maxValue = a[0];
        for(int i = 1; i < a.length; i++) {
            maxValue = Math.max(maxValue,a[i]);
        }
        Node []nodes = new Node[maxValue/100+1];
        for(int i = 0; i < a.length; i++) {
            if(nodes[a[i]/100] == null) {
                nodes[a[i]/100] = new Node(a[i]);
                continue;
            }
            nodes[a[i]/100].insert(a[i]);
        }
        int offset = 0;
        for(int i = 0; i < nodes.length; i++) {
            Node cur = nodes[i];
            while (cur != null) {
                a[offset++] = cur.val;
                cur = cur.next;
            }
        }
        return;
    }
    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<>();
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
        }
        int[] a = {862345, 356, 4512, 956, 56464};
        new AnyTest().bucketSort(a);

    }
}
