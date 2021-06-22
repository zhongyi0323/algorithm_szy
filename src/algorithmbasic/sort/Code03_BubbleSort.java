package algorithmbasic.sort;

import algorithmbasic.util.MainUtil;

/**
 * @Author sunzhongyi
 * @Date 2021/4/28
 */
public class Code03_BubbleSort {

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    MainUtil.swap(arr, j, j + 1);
                }
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = MainUtil.generatorRandomArray(100, 10);
            int[] arr1 = MainUtil.copyArray(arr);
            bubbleSort(arr1);
            MainUtil.compareArray(arr);
            if (!MainUtil.compareTo(arr, arr1)) {
                MainUtil.printArray(arr);
                MainUtil.printArray(arr1);
                System.out.println("fuck");
            }
        }
    }
}
