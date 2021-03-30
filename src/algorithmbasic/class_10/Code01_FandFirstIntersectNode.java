package algorithmbasic.class_10;

/**
 * 找出两个链表第一个相交节点
 */
public class Code01_FandFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


    public static Node getIntersectNode(Node head1, Node head2) {

        Node node1 = getLoopNode(head1);
        Node node2 = getLoopNode(head2);
        //两个无环链表可能存在交集
        if (node1 == null && node2 == null) {
            return noLoop(head1, head2);

        }
        //两个有环链表可能存在交集
        if (node1 != null && node2 != null) {
            return bothLoop(head1, head2, node1, node2);
        }
        //一个有环，一个无环，一定没有交集

        return null;
    }

    private static Node bothLoop(Node head1, Node head2, Node node1, Node node2) {
        //如果入环节点相同，必相交
        if (node1 == node2) {
            Node cur = head1;
            int n = 0;
            while (cur != node1) {
                n++;
                cur = cur.next;
            }
            cur = head2;
            while (cur != node1) {
                n--;
                cur = cur.next;
            }
            Node cur1 = n > 0 ? head1 : head2;
            Node cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                cur1 = cur1.next;
                n--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{//如果两个链表有交集，焦点必在环上，遍历一圈，如果有相等，则返回当前节点，否则返回null;
            Node cur1 = node1.next;
            while (cur1!=node1){
                if (cur1 == node2){
                    return node2;
                }
                cur1 = cur1.next;
            }
            return null;
        }

    }

    //求无环链表的第一个交点
    private static Node noLoop(Node head1, Node head2) {
        Node cur = head1;
        int n = 0;
        while (head1 != null) {
            n++;
            cur = cur.next;
        }
        cur = head2;
        while (head2 != null) {
            n--;
            cur = cur.next;
        }
        Node cur1 = n > 0 ? head1 : head2;//cur1指向长度较长的链表
        Node cur2 = cur1 == head1 ? head2 : head1;//cur2指向另外一个
        for (int i = 0; i < Math.abs(n); i++) {
            cur1 = cur1.next;
        }
        while (cur1 != null && cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }


    //找到链表入环节点，如果无还，返回null
    public static Node getLoopNode(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        head.next = new Node(1);
        head.next.next = new Node(2);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(5);
        head.next.next.next.next.next.next = head.next.next;
        Node node1 = getLoopNode(head);
        System.out.println(node1 == null ? -1 : node1.value);
    }

}
