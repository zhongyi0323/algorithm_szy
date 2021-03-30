package szy.algorithmbasic.class_14;

import java.util.PriorityQueue;

/**
 * @Author sunzhongyi
 * @Date 2021/3/28
 *
 */
public class Code02_LessMoneySplitGold {

    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int a : arr){
            queue.add(a);
        }
        int ans = 0;
        int cur = 0;
        if (queue.size()>1){
            cur = queue.poll()+queue.poll();
            ans += cur;
            queue.add(cur);
        }
        return ans;
    }
}
