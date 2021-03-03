package _useful;

import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

/**
 * description 环状链表,不修改原链表找到环节点（入度为2的节点），无环返回null
 *
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/10
 **/
public class LeetCode142 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 获取环状链表交点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        //return getListNodeBySet(head);
        return getListNodeByFastSlowPonit(head);
    }

    /**
     * 快慢指针法
     * @param head
     * @return
     */
    private ListNode getListNodeByFastSlowPonit(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != slow) {
            //无环，快指针走到尽头
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        /**
         *                     ||||||||||||||||||||||||||
         *                     |                        |
         *                     |        环长n           |
         *                     |                        |
         *                     |                        |
         *   ||||||||||||||||||||||||||||||||||||||||||||
         *                     |               |
         *                   交点位置m      快慢指针y点相遇
         */
        //相遇之时，快指针已走一圈，慢指针未走完一圈，有：  2*t = m+n+y ； t = m + y （t为时间，v*t=s）
        // 可得m = n - y，令一个指针从起点开始走至m点，另一指针从y点沿环走至m点，路程相等
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * Hash统计法
     *
     * @param head
     * @return
     */
    @Nullable
    private ListNode getListNodeBySet(ListNode head) {
        HashSet<ListNode> set = new HashSet<ListNode>();
        ListNode node = head;
        while (node != null) {
            if (set.add(node)) {
                node = node.next;
            } else {
                return node;
            }
        }
        return null;
    }
}
