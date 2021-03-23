package szy.algorithmbasic.class_09;

import java.util.HashMap;

public class Code03_CopyWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 通过容器的方式进行赋值操作
     *
     * @param head
     * @return
     */
    public static Node copyWithRand(Node head) {
        if (head == null) return null;
        Node cur = head;
        HashMap<Node, Node> nodeHashMap = new HashMap<>();
        while (cur != null) {
            nodeHashMap.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            nodeHashMap.get(cur).next = nodeHashMap.get(cur.next);
            nodeHashMap.get(cur).rand = nodeHashMap.get(cur.rand);
            cur = cur.next;
        }
        return nodeHashMap.get(head);
    }

    public static Node copyWithRand2(Node head) {
        if (head == null) return null;
        Node cur = head;
        Node next = null;
        while (cur != null) {//将copy 的节点放在当前节点的后面
            Node newNode = new Node(cur.value);
            next = cur.next;
            cur.next = newNode;
            newNode.next = next;
            cur = next;
        }
        cur = head;
        Node copyNode = null;
        while (cur != null) {//将copy对应的rand节点与对应的copy节点连接
            next = cur.next.next;
            copyNode = cur.next;
            copyNode.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        cur = head;
        Node res = head.next;
        while (cur != null) {//还原原数组、并且返回新的节点res
            next = cur.next.next;
            copyNode = cur.next;
            cur.next = next;
            copyNode.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

}
