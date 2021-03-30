package algorithmbasic.class_01;

import algorithmbasic.util.MainUtil;

/**
 * 插入排序
 * 时间复杂度O(n^2)
 * 额外空间复杂度O(1)
 * 稳定
 * 基于相对有序的数组，时间复杂度会变低
 */
public class Code02_InsertSort {


    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int minIndex = i;
            while (minIndex > 1 && arr[minIndex] < arr[minIndex - 1]) {
                MainUtil.swap(arr, minIndex, minIndex - 1);
                minIndex--;
            }
        }
    }

    /**
     * 通用写法
     * @param arr
     */
    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i - 1; j >= 0 && arr[j] < arr[j + 1]; j--) {
                MainUtil.swap(arr, j, j + 1);
            }
        }
    }


}
