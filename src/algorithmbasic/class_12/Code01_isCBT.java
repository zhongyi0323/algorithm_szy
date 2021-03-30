package algorithmbasic.class_12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author sunzhongyi
 * @Date 2021/3/26
 * <p>
 * 完全二叉树
 */
public class Code01_isCBT {


    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static boolean isCBT(Node head) {
        if (head == null) return true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node l = null;
        Node r = null;
        queue.add(head);
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            l = cur.left;
            r = cur.right;
            //如果右节点不为空，左节点为空 或者 （遇到过左右节点不双全 && 当前节点有左或右节点）
            if ((l == null && r != null) || (leaf && (l != null || r != null))) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            leaf = (l == null || r == null);
        }
        return true;
    }

    public static class Info {
        boolean isCBT;
        boolean isFull;
        int high;

        public Info(int hi, boolean isCBT, boolean isFull) {
            high = hi;
            this.isFull = isFull;
            this.isCBT = isCBT;
        }
    }

    public static boolean isCBT2(Node head) {
        return process(head).isCBT;
    }

    public static Info process(Node head) {
        if (head == null) return new Info(0, true, true);
        Info left = process(head.left);
        Info right = process(head.right);
        boolean isCBT = true;
        boolean isFull = true;
        int high = 0;
        high = Math.max(left.high, right.high) + 1;
        isFull = left.isFull && right.isFull && left.high == right.high;
        isCBT = isFull;
        if (!isCBT) {
            //左树为完全二叉树、右树为满树、高度差为1 left = right+1
            if (left.isCBT && right.isFull && left.high == right.high + 1) {
                isCBT = true;
            }
            //左树为满树、右树为满树、高度相差为1left = right+1
            if (left.isFull && right.isFull && left.high == right.high + 1) {
                isCBT = true;
            }
            //左树为满树、右树为完全二叉树、高度相同
            if (left.isFull && right.isCBT && left.high == right.high) {
                isCBT = true;
            }
        }
        return new Info(high, isCBT, isFull);
    }

}
