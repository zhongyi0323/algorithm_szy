package szy.algorithmbasic.class_13;

/**
 * @Author sunzhongyi
 * @Date 2021/3/28
 * <p>
 * 二叉树最低公共祖先
 */
public class Code02_lowestAncestor {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    public static class Info {
        boolean findA;
        boolean findB;
        Node ans;

        public Info(boolean findA, boolean findB, Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    public static Node getLowestAncestor(Node head, Node a, Node b) {
        return process(head, a, b).ans;
    }

    //x不是答案
    public static Info process(Node node, Node a, Node b) {
        if (node == null) return new Info(false, false, null);
        Info leftInfo = process(node.left, a, b);
        Info rightInfo = process(node.right, a, b);
        Node ans = null;
        boolean findA = false;
        boolean findB = false;
        findA = leftInfo.findA || rightInfo.findA || node == a;
        findB = rightInfo.findB || leftInfo.findB || node == b;

        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else if (findA && findB) {
            ans = node;
        }
        return new Info(findA, findB, ans);
    }
}
