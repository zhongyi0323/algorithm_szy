package szy.algorithmbasic.class_06;

import java.util.PriorityQueue;

/**
 * @Author sunzhongyi
 * @Date 2021/3/22
 * <p>
 * 已知数组中元素距离最终位置移动小于k，将数组排序
 */
public class Code04_MergeSortDisLessK {

    public static void mergeSortLessK(int[] arr, int k) {
        if (arr == null || arr.length < 2) return;
        PriorityQueue<Integer> queue = new PriorityQueue();
        //先构建最小堆结合，大小为k
        int i = 0;
        for (; i < Math.min(arr.length, k); i++) {
            queue.add(arr[i]);
        }
        int j = 0;
        for (; i < arr.length; i++) {
            arr[j++] = queue.poll();
            queue.add(arr[i]);
        }
        while (!queue.isEmpty()) {
            arr[j++] = queue.poll();
        }


    }
}
