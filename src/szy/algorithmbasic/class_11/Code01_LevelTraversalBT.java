package szy.algorithmbasic.class_11;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author:sunzhongyi
 * @Date:2021/3/24
 * 宽度优先遍历（按层便利）
 */
public class Code01_LevelTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }


    public static void levelTraversal(Node head){
        if(head == null){
            return ;
        }
        Queue<Node> queue = new LinkedList<>();
        Node cur = head;
        queue.add(cur);
        while (!queue.isEmpty()){
            cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left!=null){
                queue.add(cur.left);
            }
            if (cur.right!=null){
                queue.add(cur.right);
            }
        }
    }
}
