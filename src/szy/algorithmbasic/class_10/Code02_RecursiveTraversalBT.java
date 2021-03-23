package szy.algorithmbasic.class_10;

/**
 * 二叉树遍历
 *
 *
 *
 */
public class Code02_RecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    /**
     * 先序遍历
     * @param head
     */
    public static void pre(Node head){
        if (head == null) return;
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序遍历
     * @param head
     */
    public static void mid(Node head){
        if (head == null) return;
        mid(head.left);
        System.out.println(head.value);
        mid(head.right);
    }

    /**
     * 后续遍历
     * @param head
     */
    public static void pos(Node head){
        if (head == null) return ;
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        mid(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");

    }

}
