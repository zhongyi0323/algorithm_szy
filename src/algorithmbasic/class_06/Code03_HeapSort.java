package algorithmbasic.class_06;

import algorithmbasic.util.MainUtil;

/**
 * 堆排序
 * heapify 移除元素后，重新构建最大堆
 * heapInsert 插入元素后，重新构建最大堆
 */
public class Code03_HeapSort {

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[index / 2 - 1]) {
            MainUtil.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largeI = (left + 1 < heapSize && arr[left + 1] > arr[left]) ? left + 1 : left;
            largeI = arr[largeI] > arr[index] ? largeI : index;
            if (largeI == index) break;
            MainUtil.swap(arr, index, largeI);
            index = largeI;
            left = index * 2 + 1;
        }
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        //组建最大堆
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
//        MainUtil.printArray(arr);
        int heapSize = arr.length;
        //现将最大值放到length-1位置
        MainUtil.swap(arr, 0, --heapSize);

        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            MainUtil.swap2(arr, 0, --heapSize);
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


    public static void heapInsert111(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            MainUtil.swap2(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify111(int[] arr, int i, int heapSize) {
        int leftIndex = 2 * i + 1;
        while (leftIndex < heapSize) {

            int maxIndex = leftIndex+1<heapSize&& arr[leftIndex+1]>arr[leftIndex]?leftIndex+1:leftIndex;

            maxIndex = arr[i]>arr[maxIndex]?i:maxIndex;
            if (maxIndex == i) {
                break;
            }
            MainUtil.swap2(arr,maxIndex,i);
            leftIndex = maxIndex *2 +1;
            i = maxIndex;
        }
    }
}
