package algorithmbasic.sort;

import algorithmbasic.util.MainUtil;

/**
 * @Author sunzhongyi
 * @Date 2021/4/28
 */
public class Code04_mergeSort {

    //递归
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int l = 0;
        int r = arr.length - 1;
        process(arr, l, r);
    }

    //非递归
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int length = arr.length;
        int step = 1;//步长
        while (step < length) {
            int l = 0;//左边届
            while (l < length) {
                if (step >= length - l) break;
                int m = l + step - 1;//左边届最右边的值
                int r = m + Math.min(step, length - m - 1);//右边届最右侧的值
                merge(arr, l, m, r);
                l = r + 1;
            }
            if (length / 2 < step) {
                break;
            }
            step <<= 1;
        }
    }

    private static void process(int[] arr, int l, int r) {
        if (l == r) return;
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= r) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }
        for (int val : help) {
            arr[l++] = val;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = MainUtil.generatorRandomArray(100, 10);
            int[] arr1 = MainUtil.copyArray(arr);
            mergeSort2(arr1);
            MainUtil.compareArray(arr);
            if (!MainUtil.compareTo(arr, arr1)) {
                MainUtil.printArray(arr);
                MainUtil.printArray(arr1);
                System.out.println("fuck");
            }
        }
    }

}
