package szy.algorithmbasic.class_12;

import szy.algorithmbasic.util.MainUtil;

/**
 * @Author sunzhongyi
 * @Date 2021/3/27
 * <p>
 * 求两个节点的最大距离
 */
public class Code06_MaxDIstance {


    public static class Info {
        int distance;
        int height;

        public Info(int dis, int hi) {
            distance = dis;
            height = hi;
        }
    }

    public static int getMaxDistance(MainUtil.Node node){
        return process(node).distance;
    }

    public static Info process(MainUtil.Node head) {
        if (head == null) return new Info(0, 0);
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int distance = 0;
        int height = 0;
        height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int p1 = leftInfo.distance;//左树的最大距离
        int p2 = rightInfo.distance;//右树的最大距离
        int p3 = leftInfo.height + rightInfo.height + 1;//经过x点的最大距离
        distance = Math.max(p1, Math.max(p2, p3));
        return new Info(distance, height);

    }
}
