package algorithmbasic.class_08;

/**
 * 计数排序
 */
public class Code01_CountSort {

    //only for 0~200 age
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] help = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < help.length; j++) {
            while (help[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

}
