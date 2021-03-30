package algorithmbasic.class_03;

/**
 * 递归实现查找最大值
 */
public class Code08_GetMax {

    public static Integer getMax(int[] arr) {
        return process(arr, 0, arr.length);
    }

    public static Integer process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);

    }
}
