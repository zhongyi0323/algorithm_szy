package szy.algorithmbasic.class_06;

import szy.algorithmbasic.util.MainUtil;

import java.math.BigDecimal;

public class HeapSort {

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[index / 2 - 1]) {
            MainUtil.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largeI = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largeI = arr[largeI] > arr[index] ? largeI : index;
            if (largeI == index) break;
            MainUtil.swap(arr, index, largeI);
            index = largeI;
            left = largeI * 2 + 1;
        }
    }

    public static void heapSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            MainUtil.swap(arr, 0, --heapSize);
        }
    }

    public static void main(String[] args) {
//        for (int i = 0;i<100000;i++){
//            int[] arr = MainUtil.generatorRandomArray(100,10);
//            int[] arr1 = MainUtil.copyArray(arr);
//            int[] arr2 = heapSort(arr1);
//            MainUtil.compareArray(arr);
//            if (!MainUtil.compareTo(arr,arr2)){
//                MainUtil.printArray(arr);
//                MainUtil.printArray(arr2);
//                System.out.println("fuck");
//            }
//        }

    }

}
