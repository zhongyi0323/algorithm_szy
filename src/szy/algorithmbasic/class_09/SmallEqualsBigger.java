package szy.algorithmbasic.class_09;


import szy.algorithmbasic.util.MainUtil;

/**
 * 给定一个链表，输出小于->等于-》大于 输入值后的链表顺序
 */
public class SmallEqualsBigger {

    public static class Node {
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 链表头部，比较值
     * 借助辅助数组进行排序（参考荷兰国旗）
     *
     * @param head
     * @param pivot
     * @return
     */
    public static Node partation1(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        int num = 0;
        Node pre = head;
        while (pre != null) {
            num++;
            pre = pre.next;
        }
        //辅助数组
        Node[] nodes = new Node[num];
        pre = head;
        int index = 0;
        while (pre != null) {
            nodes[index++] = pre;
            pre = pre.next;
        }
        printNode1(nodes);
        arrPartation(nodes, pivot);
        for (int i = 1; i < nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }
        nodes[nodes.length - 1].next = null;
        return nodes[0];
    }


    /**
     * 不借助辅助空间，只借助额外变量
     *
     * @param head
     * @param pivot
     * @return
     */
    public static Node partation2(Node head, int pivot) {
        if (head == null || head.next == null) return head;
        Node smallStart = null;
        Node smalltail = null;
        Node equalsStart = null;
        Node equalstail = null;
        Node bigStart = null;
        Node bigtail = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < pivot) {
                if (smallStart == null) {
                    smallStart = head;
                    smalltail = head;
                } else {
                    smalltail.next = head;
                    smalltail = head;
                }
            } else if (head.val == pivot) {
                if (equalsStart == null) {
                    equalsStart = head;
                    equalstail = head;
                } else {
                    equalstail.next = head;
                    equalstail = head;
                }
            } else {
                if (bigStart == null) {
                    bigStart = head;
                    bigtail = head;
                } else {
                    bigtail.next = head;
                    bigtail = head;
                }
            }
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (smalltail != null) { // 如果有小于区域
            smalltail.next = equalsStart;
            equalstail = equalstail == null ? smalltail : equalstail; // 下一步，谁去连大于区域的头，谁就变成eT
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (equalstail != null) { // 如果小于区域和等于区域，不是都没有
            equalstail.next = bigStart;
        }
        return smallStart != null ? smallStart : (equalsStart != null ? equalsStart : bigStart);

    }


    public static void swap(Node[] nodes, int i, int j) {
        Node node = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = node;
    }

    public static void printNode(Node head) {
        while (head != null) {
            System.out.print(head.val + "--->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void arrPartation(Node[] nodes, int pivot) {
        int leftIndex = -1;
        int rightIndex = nodes.length;
        for (int i = 0; i < rightIndex; ) {
            if (nodes[i].val < pivot) {
                swap(nodes, ++leftIndex, i++);
            } else if (nodes[i].val > pivot) {
                swap(nodes, --rightIndex, i);
            } else {
                i++;
            }
        }
    }

    public static void printNode1(Node[] nodes) {
        for (Node node : nodes) {
            System.out.print(node.val + "--->");
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        Node node1 = new Node(1);
        Node node2 = new Node(5);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        printNode(head);
        Node newNode = partation2(head, 4);
        printNode(newNode);
    }


}
