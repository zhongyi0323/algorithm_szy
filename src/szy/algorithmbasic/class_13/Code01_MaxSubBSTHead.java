package szy.algorithmbasic.class_13;

import szy.algorithmbasic.util.MainUtil;

import java.util.ArrayList;

/**
 * @Author sunzhongyi
 * @Date 2021/3/28的头节点
 * <p>
 * 获取最大二叉搜索子树
 */
public class Code01_MaxSubBSTHead {


    public static class Info {
        //        private boolean isBST;//是否为二叉搜索树
        private int max;//最大值
        private int min;//最小值
        private MainUtil.Node head;//二叉搜索子树的头节点
        private int size;//当前节点数大小
        private int maxSubBSTSize;//最大搜索子树大小

        public Info(int max, int min, MainUtil.Node head, int size, int maxSubBSTSize) {
//            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.head = head;
            this.size = size;
            this.maxSubBSTSize = maxSubBSTSize;
        }
    }

    public static MainUtil.Node getHead(MainUtil.Node head) {
        if (head == null) return null;
        return process(head).head;
    }

    public static Info process(MainUtil.Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.value, min = node.value;
        MainUtil.Node head = null;
        int size = 1;
        int p1 = -1;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            size += leftInfo.size;
            p1 = leftInfo.maxSubBSTSize;
            head  = leftInfo.head;
        }
        int p2 = -1;
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            size += rightInfo.size;
            p2 = rightInfo.maxSubBSTSize;
            if (head==null || rightInfo.maxSubBSTSize > leftInfo.maxSubBSTSize){
                head  = rightInfo.head;
            }
        }
        boolean leftBST = leftInfo == null ? true : leftInfo.maxSubBSTSize == leftInfo.size;
        boolean rightBST = rightInfo == null ? true : rightInfo.maxSubBSTSize == rightInfo.size;
        int p3 = -1;
        if (leftBST && rightBST) {
            boolean leftMaxLessX = leftInfo == null ? true : node.value > leftInfo.max;
            boolean rightMinMoreX = rightInfo == null ? true : node.value < rightInfo.min;
            if (leftMaxLessX && rightMinMoreX) {
                int leftNum = leftInfo == null ? 0 : leftInfo.maxSubBSTSize;
                int rightNum = rightInfo == null ? 0 : rightInfo.maxSubBSTSize;
                p3 = leftNum + rightNum + 1;
            }
        }
        if (p3 > Math.max(p1, p2)) {
            head = node;
        }
        int maxSubBSTSize = Math.max(Math.max(p1, p2), p3);
        return new Info(max, min, head, size, maxSubBSTSize);
    }


    // for test
    public static MainUtil.Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static MainUtil.Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        MainUtil.Node head = new MainUtil.Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            MainUtil.Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != getHead(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static MainUtil.Node maxSubBSTHead1(MainUtil.Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        MainUtil.Node leftAns = maxSubBSTHead1(head.left);
        MainUtil.Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getBSTSize(MainUtil.Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<MainUtil.Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(MainUtil.Node head, ArrayList<MainUtil.Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }
}
