package szy.algorithmbasic.class_12;

import szy.algorithmbasic.util.MainUtil;

/**
 * @Author sunzhongyi
 * @Date 2021/3/27
 */
public class Code05_MaxSubBSTSize {


    public static class Info {
        int maxBSTSubSize;//当前节点最大二叉搜索子树的大小
        int max;//最大值
        int min;//最小值
        int size;//节点大小

        public Info(int maxBSTSubSize, int max, int min, int size) {
            this.maxBSTSubSize = maxBSTSubSize;
            this.max = max;
            this.min = min;
            this.size = size;
        }
    }

    public static int maxSubBSTSize(MainUtil.Node head) {
        if (head == null) return 0;
        return process(head).maxBSTSubSize;

    }

    public static Info process(MainUtil.Node node) {

        if (node == null) return null;
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int maxBSTSubSize = -1;
        int max = node.value;
        int min = node.value;
        int size = 1;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            size += leftInfo.size;
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            size += rightInfo.size;
        }
        boolean isleftBST = leftInfo == null || leftInfo.maxBSTSubSize == leftInfo.size;
        boolean isrightBST = rightInfo == null || rightInfo.maxBSTSubSize == rightInfo.size;
        int p1 = 0;
        if (leftInfo != null) {
            p1 = leftInfo.maxBSTSubSize;
        }
        int p2 = 0;
        if (rightInfo != null) {
            p2 = rightInfo.maxBSTSubSize;
        }
        int p3 = 0;
        //如果左右子树都满足二叉搜索树
        if (isleftBST && isrightBST) {

            if (leftInfo == null && rightInfo == null) {
                p3 = 1;
            } else if (leftInfo == null) {
                if (rightInfo.min > node.value) {
                    p3 = rightInfo.maxBSTSubSize + 1;
                }
            } else if (rightInfo == null) {
                if (leftInfo.max < node.value) {
                    p3 = leftInfo.maxBSTSubSize + 1;
                }
            } else {
                if (leftInfo.max < node.value && rightInfo.min > node.value) {
                    p3 = rightInfo.maxBSTSubSize + leftInfo.maxBSTSubSize + 1;
                }
            }
        }
        maxBSTSubSize = Math.max(Math.max(p1, p2), p3);
        return new Info(maxBSTSubSize, max, min, size);
    }
}
