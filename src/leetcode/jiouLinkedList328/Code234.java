package leetcode.jiouLinkedList328;

import java.util.List;

/**
 * @Author sunzhongyi
 * @Date 2021/8/7
 */
public class Code234 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(0);
        Code234 code = new Code234();
        code.isPalindrome(head);

    }

    public boolean isPalindrome(ListNode head) {
        if(head == null|| head.next == null) return true;
        ListNode midNode = midNode(head);
        ListNode reverseNode = reverse(midNode);
        while (reverseNode!=null && head!=null){
            if (reverseNode.val!=head.val){
                return false;
            }
            reverseNode = reverseNode.next;
            head = head.next;
        }
        return true;
    }


    public ListNode midNode(ListNode node){
        ListNode fast  = node;
        ListNode slow = node;

        while (fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public ListNode reverse(ListNode node){
        ListNode pre = null;

        while (node!=null){
            ListNode newNode = new ListNode(node.val);
            newNode.next = pre;
            pre = newNode;
            node = node.next;
        }
        return pre;
    }
}
