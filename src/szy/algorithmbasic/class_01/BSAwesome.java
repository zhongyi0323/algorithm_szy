package szy.algorithmbasic.class_01;

/**
 * 在相邻不想等的数组中找出，比左右节点数值都小的索引值
 */
public class BSAwesome {

    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int mid = 0;
        int l = 1;
        int r = arr.length - 2;
        //如果不满足上述条件，那么在l到r范围内必有局部最小
        while (l < r) {
            mid = l + ((r - l) << 1);
            if (arr[mid] > arr[mid - 1]) {
                r = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return l;
    }
}
