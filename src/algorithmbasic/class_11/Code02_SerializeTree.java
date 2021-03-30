package algorithmbasic.class_11;


import java.util.*;

/**
 * @Author:sunzhongyi
 * @Date:2021/3/24 序列化、反序列化二叉树
 */
public class Code02_SerializeTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //前序遍历序列化
    public static Queue<String> preSerializeTree(Node head) {
        Queue<String> queue = new LinkedList<>();
        pres(head, queue);
        return queue;
    }


    private static void pres(Node node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            queue.add(node.value + "");
            pres(node.left, queue);
            pres(node.right, queue);
        }
    }

    //前序遍历反序列化
    private static Node reSerializeTree(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        return rePres(queue);
    }

    private static Node rePres(Queue<String> queue) {
        String val = queue.poll();
        if (val == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(val));
        head.left = rePres(queue);
        head.right = rePres(queue);
        return head;
    }

    //中序遍历序列化
    public static Queue<String> inSerializeTree(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        ins(head, queue);
        return queue;
    }

    private static void ins(Node node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            ins(node.left, queue);
            queue.add(node.value + "");
            ins(node.right, queue);
        }
    }

    //后序遍历序列化
    public static Queue<String> posSerializeTree(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        pos(head, queue);
        return queue;
    }

    private static void pos(Node node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            ins(node.left, queue);
            ins(node.right, queue);
            queue.add(node.value + "");
        }

    }

    //后序遍历反序列化
    public static Node rePosSeralizeTree(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        //队列顺序为左--》右--》头,输出到栈中后变为头-->右--》左
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return rePos(stack);
    }

    private static Node rePos(Stack<String> stack) {
        Node head = new Node(Integer.getInteger(stack.pop()));
        head.right = rePos(stack);
        head.left = rePos(stack);
        return head;

    }

    //层序遍历序列化
    public static Queue<String> levelSerialTree(Node head) {
        if (head == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        levelSerial(head, queue);
        return queue;
    }

    private static void levelSerial(Node node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            queue.add(node.value + "");
            Queue<Node> nodes = new LinkedList<>();
            nodes.add(node);
            while (!nodes.isEmpty()) {
                if (node.left != null) {
                    queue.add(node.left.value + "");
                    nodes.add(node.left);
                } else {
                    queue.add(null);
                }
                if (node.right != null) {
                    queue.add(node.right.value + "");
                    nodes.add(node.right);
                } else {
                    queue.add(null);
                }
            }
        }
    }

    //层序遍历反序列化
    public static Node reLevelSerialTree(Queue<String> queue) {
        if (queue.isEmpty() || queue.size() == 0) {
            return null;
        }
        Node head = generateNode(queue.poll());
        Queue<Node> nodes = new LinkedList<>();
        if (head != null) {
            nodes.add(head);
            Node cur = null;
            while (!nodes.isEmpty()) {
                cur = nodes.poll();
                cur.left = generateNode(queue.poll());
                if (cur.left != null) {
                    nodes.add(cur.left);
                }
                cur.right = generateNode(queue.poll());
                if (cur.right != null) {
                    nodes.add(cur.right);
                }
            }
        }
        return head;

    }

    //创建节点
    private static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.getInteger(val));

    }


}
