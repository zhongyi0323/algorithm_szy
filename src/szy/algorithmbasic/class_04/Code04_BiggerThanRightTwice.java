package szy.algorithmbasic.class_04;

import szy.algorithmbasic.util.MainUtil;

/**
 * 查询无序数组中前面的数比后面数2倍还大的个数
 */
public class Code04_BiggerThanRightTwice {

    public static int getBiggerNum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int l = 0;
        int r = arr.length - 1;
        return process(arr, l, r);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        int leftNum = process(arr, l, mid);
        int rightNum = process(arr, mid + 1, r);
        return leftNum + rightNum + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] helps = new int[r - l + 1];
        int i = helps.length - 1;
        int p1 = mid;
        int p2 = r;
        int res = 0;
        //因为倍数原因，不能正常的进行排序操作，所以单独抽出方法做这部运算
        while (p1 >= l && p2 > mid) {
            if (arr[p1] > (arr[p2] << 1)) {
                res += (p2 - mid);
                p1--;
            } else {
                p2--;
            }
        }
        //重新进行归并排序
        p1 = mid;
        p2 = r;
        while (p1 >= l && p2 > mid) {
            helps[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            helps[i--] = arr[p1--];
        }
        while (p2 > mid) {
            helps[i--] = arr[p2--];
        }
        for (i = 0; i < helps.length; i++) {
            arr[l + i] = helps[i];
        }
        return res;
    }


    public static int compareTo(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int testTimes = 10;
        int maxVal = 100;
        int maxLen = 10;
        for (int i = 0; i < 1000000; i++) {
            int[] arr = MainUtil.generatorRandomArray(maxVal, maxLen);
//            int[] arr = {-26, 0, 78, -17, 57, -37, 45, -24, -9};
//        int[] arr = {-23 ,54, 80, -42, -51};
            int[] arr1 = MainUtil.copyArray(arr);
            int sum1 = compareTo(arr);
            int sum2 = getBiggerNum(arr1);
            if (sum1 != sum2) {
                System.out.println("fail==========");
                System.out.println(sum1);
                System.out.println(sum2);
                MainUtil.printArray(arr);
            }
        }
    }
}
