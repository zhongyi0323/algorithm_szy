package algorithmbasic.sort;

import algorithmbasic.util.MainUtil;

/**
 * @Author sunzhongyi
 * @Date 2021/8/25
 */
public class Code07_quickSort {



    public static void quickSort(int[] arr){
        int l = 0;
        int r = arr.length-1;
        process(arr,l,r);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) return;
        final int[] partation = partation(l, r, arr);
        process(arr,l,partation[0]-1);
        process(arr,partation[1]+1,r);
    }

    static int[] partation(int l, int r, int[] arr) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int less = l - 1;
        int more = r - 1;
        int cur = arr[r];
        int index = l;
        while (index < r) {
            if (arr[index] < cur) {
                arr[less] = arr[index++];
            } else if (arr[index] == cur) {
                index++;
            } else {
                swap(arr, index, more--);
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];

    }
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = MainUtil.generatorRandomArray(100, 10);
            int[] arr1 = MainUtil.copyArray(arr);
            quickSort(arr1);
            MainUtil.compareArray(arr);
            if (!MainUtil.compareTo(arr, arr1)) {
                MainUtil.printArray(arr);
                MainUtil.printArray(arr1);
                System.out.println("fuck");
            }
        }
    }
}
