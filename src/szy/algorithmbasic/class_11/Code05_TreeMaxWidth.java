package szy.algorithmbasic.class_11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author sunzhongyi
 * @Date 2021/3/26
 * <p>
 * 获取一个树的最大宽度
 */
public class Code05_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static int getMaxWidth(Node head) {
        if (head == null) return 0;
        int max = 0;
        int curNums = 0;
        Queue<Node> queue = new LinkedList<>();
        Node cur = head;
        queue.add(head);
        Node curNode = null;//当前层最后一个节点
        Node nextNode = head;//下一层最后一个节点；
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.left != null) {
                nextNode = cur.left;
                queue.add(cur.left);
            }
            if (cur.right != null) {
                nextNode = cur.right;
                queue.add(cur.right);
            }
            curNums++;
            if (curNode == cur) {
                max = Math.max(max, curNums);
                curNums = 0;
                curNode = nextNode;
            }
        }
        return max;
    }



}
