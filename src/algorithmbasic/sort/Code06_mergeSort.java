package algorithmbasic.sort;

import algorithmbasic.util.MainUtil;

/**
 * @Author sunzhongyi
 * @Date 2021/4/28
 */
public class Code06_mergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int length = arr.length;
        //构造最大堆
        for (int i = length - 1; i >=0; i--) {
            heapify(arr, i, length);
        }
        while (length > 0) {
            MainUtil.swap2(arr, 0, --length);
            heapify(arr, 0, length);
        }


    }

    private static void heapify(int[] arr, int index, int length) {
        int left = index * 2 + 1;
        while (left < length) {
            left = left + 1 < length && arr[left] < arr[left + 1] ? left + 1 : left;
            left = arr[left] > arr[index] ? left : index;
            if (left == index) break;
            MainUtil.swap2(arr, left, index);
            index = left;
            left = index * 2 + 1;
        }

    }

    private static void heapinsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            MainUtil.swap2(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = MainUtil.generatorRandomArray(100, 10);
            int[] arr1 = MainUtil.copyArray(arr);
            mergeSort(arr1);
            MainUtil.compareArray(arr);
            if (!MainUtil.compareTo(arr, arr1)) {
                MainUtil.printArray(arr);
                MainUtil.printArray(arr1);
                System.out.println("fuck");
            }
        }
    }

}
