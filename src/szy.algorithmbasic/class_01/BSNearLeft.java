package szy.algorithmbasic.class_01;

import java.util.Arrays;

import static szy.algorithmbasic.util.MainUtil.copyArray;
import static szy.algorithmbasic.util.MainUtil.generatorRandomArray;


/**
 * 在有序数组中查询大于num的最左索引值
 */
public class BSNearLeft {

    /**
     * 获取数组中大于num的最左索引数
     * @param arr
     * @param num
     * @return
     */
    public static int getIndexLeft(int[] arr, int num) {
        if (arr == null) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int index = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > num) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static int getIndexLeft2(int[] arr, int num) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > num) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLength = 100;
        int maxValue = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generatorRandomArray(maxValue, maxLength);
            Arrays.sort(arr);
            int[] arr1 = copyArray(arr);
//        printArray(arr);
//        printArray(arr1);
            int l1 = getIndexLeft(arr, 50);
            int l2 = getIndexLeft2(arr1, 50);
//        System.out.println( "l1: "+l1+"l2:"+l2+"is true?"+(l1 == l2));
            if (l1 != l2) {
                System.out.println("fuck!");
            }
        }


    }


}
