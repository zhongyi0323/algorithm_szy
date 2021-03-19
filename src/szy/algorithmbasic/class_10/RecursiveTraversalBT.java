package szy.algorithmbasic.class_10;

public class RecursiveTraversalBT {
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
    public static void head(Node head){
        if (head == null) return;
        System.out.println(head.value);
        head(head.left);
        head(head.right);
    }

}
