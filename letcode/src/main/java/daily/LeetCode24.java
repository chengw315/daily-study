package daily;

/**
 * description 两两交换链表的节点 1->2->3->4  =>  2->1->4->3
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/13
 **/
public class LeetCode24 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(4);
        ListNode listNode2 = new ListNode(3,listNode3);
        ListNode listNode1 = new ListNode(2,listNode2);
        ListNode listNode = new ListNode(1,listNode1);
        ListNode listNode4 = new LeetCode24().swapPairs(listNode);

    }

    public ListNode swapPairs(ListNode head) {
        //无需互换
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        //上一个节点
        ListNode last = null;
        //互换的两个节点
        ListNode prev = head;
        ListNode post = head.next;

        while (true) {
            //互换完成
            if (prev == null || post == null) {
                return newHead;
            }
            //互换
            if (last != null) {
                last.next = post;
            }
            ListNode temp = post.next;
            post.next = prev;
            prev.next = temp;
            last = prev;
            prev = last.next;
            if (prev != null) {
                post = prev.next;
            }
        }
    }
}
