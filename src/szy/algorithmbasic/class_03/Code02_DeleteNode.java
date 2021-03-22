package szy.algorithmbasic.class_03;

/**
 * @Author sunzhongyi
 * @Date 2021/3/19
 */
public class Code02_DeleteNode {

    public static class Node {
        private int value;
        private Node next;

        public Node(int val) {
            value = val;
        }
    }

    /**
     * 将满足条件的节点从链表中移除
     *
     * @param head
     * @param num
     * @return
     */
    public static Node removeNode(Node head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
