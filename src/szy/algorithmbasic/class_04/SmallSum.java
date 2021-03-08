package szy.algorithmbasic.class_04;

import sun.applet.Main;
import szy.algorithmbasic.util.MainUtil;

/**
 * 求小和
 */
public class SmallSum {


    public static int getSmallSum(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        return process(arr, l, r);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        int leftSum = process(arr, l, mid);
        int rightSum = process(arr, mid + 1, r);
        int mergeSum = merge(arr, l, r, mid);
        return leftSum + rightSum + mergeSum;
    }

    public static int merge(int[] arr, int l, int r, int mid) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int sum = 0;
        while (p1 <= mid && p2 <= r) {
            //如果左边的数比右边的数小，那么功过p1所得到的小和应该是r-p2+1 * arr[p1]；
            if (arr[p1] < arr[p2]) {
                sum += arr[p1] * (r - p2 + 1);
                help[i++] = arr[p1++];
            } else {
                help[i++] = arr[p2++];
            }
        }

        while (p2 <= r) {
            help[i++] = arr[p2++];

        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return sum;
    }

    //test 方法
    public static int compareTo(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j] > arr[i] ? arr[i] : 0;
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        int testTimes = 10000;
        int maxVal = 100;
        int maxLen = 10;
        for (int i = 0;i<testTimes;i++){
            int[] arr = MainUtil.generatorRandomArray(maxVal, maxLen);
//        int[] arr = {-23 ,54, 80, -42, -51};
            int[] arr1 = MainUtil.copyArray(arr);
            int sum1 = compareTo(arr);
            int sum2 = getSmallSum(arr1);
            if(sum1!=sum2){
                System.out.println( sum1);
                System.out.println( sum2);
                MainUtil.printArray(arr);
            }
        }
    }

}
