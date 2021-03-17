package szy.algorithmbasic.class_09;


import java.util.Stack;

/**
 * 判断是否回文
 */
public class IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 利用栈实现 额外空间复杂度o（N）
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome1(Node head) {
        Stack<Node> nodes = new Stack<>();
        Node pre = head;
        while (pre != null) {
            nodes.push(pre);
            pre = pre.next;
        }
        Node cur = head;
        while (cur != null) {
            if (nodes.pop().value != cur.value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 一半的数据进入栈，n/2
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        //right表示右侧第一个需要进入辅助栈对应的索引位置
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    public static boolean isPalindrome3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        Node n2 = slow.next;// n2 first of right
        slow.next = null;
        Node next = null;
        while (n2 != null) {//reverse n2 is the right of rightNode
            next = n2.next;
            n2.next = slow;
            slow = n2;
            n2 = next;
        }

        next = slow;//right line
        n2 = head;//left line
        boolean ans = true;
        while (n2 != null && slow != null) {
            if (n2.value != slow.value) {//不相等，直接返回
                ans = false;
                break;
            }
            slow = slow.next;
            n2 = n2.next;
        }
        // huanyuan listNode
        Node pre = next.next;
        next.next = null;
        Node next2 = null;
        while (pre!=null){
            next2 = pre.next;
            pre.next = next;
            next = pre;
            pre = next2;
        }
        return ans;
    }

    public static Node reverseNode(Node head){
        Node pre = null;
        Node next = null;
        while (head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    public static void printNode(Node head){
        while (head!=null){
            System.out.print(head.value+"--->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(1);
        Node node4 = new Node(0);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        Node reverseNode = reverseNode(head);
        printNode(reverseNode);
        System.out.println(isPalindrome3(reverseNode));


    }
}
