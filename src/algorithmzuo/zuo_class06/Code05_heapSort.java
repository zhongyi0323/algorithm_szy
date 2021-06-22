package algorithmzuo.zuo_class06;

import algorithmbasic.util.MainUtil;

import static algorithmzuo.zuo_class06.Code03_HeapSort.swap;

/**
 * @Author sunzhongyi
 * @Date 2021/4/28
 */
public class Code05_heapSort {


    public static void heapInsert(int index, int[] arr) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int mergeIndex = index * 2 + 1;
        while (mergeIndex < heapSize) {
            if (mergeIndex + 1 < heapSize && arr[mergeIndex] < arr[mergeIndex + 1]) {
                mergeIndex = mergeIndex + 1;
            }
            mergeIndex = arr[index] >= arr[mergeIndex] ? index : mergeIndex;
            if (mergeIndex == index) break;
            swap(arr, index, mergeIndex);
            index = mergeIndex;
            mergeIndex = mergeIndex * 2 + 1;
        }
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        //大根堆
        int length = arr.length;
//        for (int i = length - 1; i >= 0; i--) {
//            heapify(arr, i, length);
//        }
        for (int i = 0;i<length;i++){
            heapInsert(i,arr);
        }
        swap(arr, 0, --length);
        while (length > 0) {
            heapify(arr, 0, length--);
            swap(arr, 0, length);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = MainUtil.generatorRandomArray(100, 10);
            int[] arr1 = MainUtil.copyArray(arr);
            heapSort(arr1);
            MainUtil.compareArray(arr);
            if (!MainUtil.compareTo(arr, arr1)) {
                MainUtil.printArray(arr);
                MainUtil.printArray(arr1);
                System.out.println("fuck");
            }
        }
    }
}
