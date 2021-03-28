package szy.algorithmbasic.class_12;

/**
 * @Author sunzhongyi
 * @Date 2021/3/27
 * 搜索二叉树
 *
 * 思想2：中序遍历后是递增序列即为二叉搜索树
 */
public class Code03_isBST {


    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBst(Node head) {
        if (head == null) return true;
        return process(head).isBst;
    }

    public static class Info {
        boolean isBst;
        int max;
        int min;

        public Info(boolean isBst, int max, int min) {
            this.isBst = isBst;//左右子树是否为二叉搜索树
            this.max = max;//当前子树最大值
            this.min = min;//当前子树最小值
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean isBst = true;
        int max = head.value;
        int min = head.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        if (leftInfo!=null && !leftInfo.isBst){
            isBst = false;
        }
        if (rightInfo!=null && !rightInfo.isBst){
            isBst = false;
        }
        if (leftInfo != null && leftInfo.max > head.value) {
            isBst = false;
        }
        if (rightInfo != null && rightInfo.min < head.value) {
            isBst = false;
        }

        return new Info(isBst, max, min);
    }

}
