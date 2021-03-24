package szy.algorithmbasic.class_11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author:sunzhongyi
 * @Date:2021/3/24 查询二叉树层数数据最多的那层的值
 */
public class Code04_TreeMaxWidth {


    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 使用额外变量处理
     * @param head
     * @return
     */
    public static int maxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curNode = head;//当前层最后一个节点
        Node nextNode = null;//下一层的最后一个节点
        int curNums = 0;//当前层数量
        int max = 0;//最大数，返回值
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextNode = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextNode = cur.right;
            }
            curNums++;
            //如果弹出的时候刚好为当前层最后一个节点，计算最大值，当前层值=0，当前层最后一个节点替换成下一层最后一个节点，进行下一层遍历
            if (cur == curNode) {
                max = Math.max(max, curNums);
                curNums = 0;
                curNode = nextNode;
            }
        }
        return max;

    }


    /**
     * 使用map辅助计算
     * @param head
     * @return
     */
    public static int maxWidthUseMap(Node head) {
        if (head == null) return 0;
        //map存储当前节点在第几层
        Map<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        int max = 0;//最大数，返回值
        int curNum = 0;//当前层元素个数
        int curLever = 1;//当前在第几层
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int level = map.get(cur);
            if (cur.left != null) {
                queue.add(cur.left);
                map.put(cur, level + 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                map.put(cur, level + 1);
            }
            //如果当前节点层数与正在统计的层数相同，当前层元素个数+1，否则计算max，当前层元素个数变成1，层数+1
            if (curLever == level) {
                curNum++;
            } else {
                max = Math.max(max, curNum);
                curNum = 1;
                curLever++;
            }
        }
        return max;

    }

}
