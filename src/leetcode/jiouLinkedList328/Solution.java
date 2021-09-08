package leetcode.jiouLinkedList328;

/**
 * @Author sunzhongyi
 * @Date 2021/8/7
 */
public class Solution {

    public ListNode oddEvenList(ListNode head) {

        if (head == null) return null;
        if (head.next == null || head.next.next == null) return head;

        ListNode p1 = head;
        ListNode next = head.next;

        ListNode p2 = next;

        while (p2!=null && p2.next!=null){
            p1.next = p2.next;
            p1 = p1.next;
            p2 = p1.next;
        }
        p1.next = next;

        return head;


    }

}

class ListNode {
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

