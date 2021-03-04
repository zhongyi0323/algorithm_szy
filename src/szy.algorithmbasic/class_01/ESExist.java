package szy.algorithmbasic.class_01;

/**
 * 二分查找
 */
public class ESExist {

    public static boolean query(int[] arr, int num) {
        if (arr == null) return false;
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] == num) {
                return true;
            }
            if (arr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return arr[L] == num;
    }
}
