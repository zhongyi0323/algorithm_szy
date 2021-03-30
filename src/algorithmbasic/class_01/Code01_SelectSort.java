package algorithmbasic.class_01;

import algorithmbasic.util.MainUtil;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度O(n^2)
 * 额外空间复杂度O(1)
 * 不稳定
 */
public class Code01_SelectSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int minIndex = 0;
        //0~N-1位置选个最小值的位置
        //1~N-1位置选个最小值的位置
        //2~N-1位置选个最小值的位置
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            if (i != minIndex) {
                swap(arr, i, minIndex);
            }
        }
    }

    /**
     * 两个位置的数进行交换，值相等的情况下禁止调用
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxVal = 1000;
        int maxLength = 100;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = MainUtil.generatorRandomArray(maxVal, maxLength);
//            arr = new int[]{0, -35, 357, 0, 23, 683 };
            MainUtil.compareArray(arr);
            int[] arr1 = MainUtil.copyArray(arr);
            selectionSort(arr);
            Arrays.sort(arr1);
//            MainUtil.printArray(arr);
//            MainUtil.printArray(arr1);
            if (!MainUtil.compareTo(arr, arr1)) {

                System.out.println("fuck");
                MainUtil.printArray(arr);
            }
        }
    }
}
