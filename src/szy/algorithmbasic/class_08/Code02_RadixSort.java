package szy.algorithmbasic.class_08;

import szy.algorithmbasic.util.MainUtil;

/**
 * 基数排序
 */
public class Code02_RadixSort {


    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        redixSort(arr, 0, arr.length - 1, getDigit(arr));
    }

    public static int getDigit(int[] arr) {
        int digit = 0;
        int max = 0;
        for (int a : arr) {
            max = Math.max(max, a);
        }
        while (max != 0 && max / 10 >= 0) {
            digit++;
            max = max / 10;
        }
        return digit;
    }

    /**
     * 求num对应的每个位置的值是多少
     *
     * @param d
     * @param num
     * @return
     */
    public static int getDigitNum(int d, int num) {
        return (num / ((int) Math.pow(10, d - 1))) % 10;
    }

    private static void redixSort(int[] arr, int l, int r, int digit) {
        int radix = 10;
        int i = 0;
        int j = 0;
        int[] help = new int[r - l + 1];
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            //将号码相同的值放在一个桶中，并记录相应的次数
            for (i = l; i <= r; i++) {
                count[getDigitNum(d, arr[i])]++;
            }
            //每个桶中记录比当前值<=的个数
            //1 1 3 4 5 3 2 原始count
            //0 1 2 3 4 5 6 索引位置
            //1 2 5 9 14 17 19  重新记录后
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            //丛数组右侧依次遍历
            for (i = r; i >= l; i--) {
                j = getDigitNum(d, arr[i]);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            //将辅助数组中的值传回arr数组
            for (i = 0; i < r - l + 1; i++) {
                arr[l + i] = help[i];
            }

        }
    }
}
