package algorithmbasic.sort;

import algorithmbasic.util.MainUtil;

/**
 * @Author sunzhongyi
 * @Date 2021/4/28
 */
public class Code02_insertSort {

    //插入排序
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i - 1; j >= 0 && arr[j+1] < arr[j]; j--) {
                MainUtil.swap(arr, j + 1, j);
            }
        }
    }

    public static void insertSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int index = i;
            while (index > 0 && arr[index] < arr[index - 1]) {
                MainUtil.swap(arr, index, index - 1);
                index--;
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = MainUtil.generatorRandomArray(100, 10);
            int[] arr1 = MainUtil.copyArray(arr);
            insertSort2(arr1);
            MainUtil.compareArray(arr);
            if (!MainUtil.compareTo(arr, arr1)) {
                MainUtil.printArray(arr);
                MainUtil.printArray(arr1);
                System.out.println("fuck");
            }
        }
    }

}
