package szy.algorithmbasic.class_12;

/**
 * @Author sunzhongyi
 * @Date 2021/3/27
 * <p>
 * 平衡二叉树
 */
public class Code02_isBBT {
    //平衡二叉树特点
    //任意节点子树的高度差都小于等于1

    public static class Node {
        private int value;
        Node left;
        Node right;
    }
    public static boolean isBBT(Node head) {
        return process(head).isbbt;
    }
    public static class Info {
        private boolean isbbt;//是否为平衡二叉树
        private int height;//树的高度

        public Info(boolean bbt, int height) {
            this.isbbt = bbt;
            this.height = height;
        }
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        boolean isbst = true;
        int height = 1;
        isbst = leftInfo.isbbt && rightInfo.isbbt && Math.abs(leftInfo.height - rightInfo.height) < 2;
        height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(isbst, height);

    }
}
