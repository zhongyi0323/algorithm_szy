package szy.algorithmbasic.class_01;

import szy.algorithmbasic.util.MainUtil;

/**
 * 冒泡排序
 * 时间复杂度 O(n^2)
 * 额外空间复杂度 O(1)
 * 稳定
 */
public class Code03_BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        for (int i = arr.length - 1; i < 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    MainUtil.swap(arr, j, j + 1);
                }
            }
        }
    }
}
