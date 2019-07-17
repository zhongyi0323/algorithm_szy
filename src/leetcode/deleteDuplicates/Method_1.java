package leetcode.deleteDuplicates;

/**
 * @Author: sunzhongyi
 * @Date: 2019/7/17 17:50
 * @description：${description}
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Method_1 {

    public ListNode deleteDuplicates(ListNode head) {


        ListNode newNode = head;
        while (newNode != null && newNode.next != null) {
            if (newNode.val == newNode.next.val) {
                newNode.next = newNode.next.next;
            } else {
                newNode = newNode.next;
            }
        }
        return head;
    }
}
