package algorithmbasic.class_05;

/**
 * 快速排序1.0，2.0；3.0
 */
public class Code02_PartitionAndQuickSort {
    //数据交换
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 快速排序1.0 返回 r位置对应值最终所在位置
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int partition1(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;//左边界
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    /**
     * 返回一个数组，表示等于r位置值对应位置的起终点
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int[] partition2(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int lessIndex = l - 1;
        int upperIndex = r;
        int index = l;
        while (index < upperIndex) {
            if (arr[index] < arr[r]) {
                swap(arr, ++lessIndex, index);
            } else if (arr[index] == arr[r]) {
                index++;
            } else {
                swap(arr, index, --upperIndex);
            }
        }
        //将r位置的数和比r位置大的最左边的数进行交换
        swap(arr, upperIndex, r);
        return new int[]{lessIndex + 1, upperIndex};
    }

    public static void process1(int[] arr, int l, int r) {
        if (l >= r) return;
        int m = partition1(arr, l, r);
        process1(arr, l, m - 1);
        process1(arr, m + 1, r);
    }

    public static void process2(int[] arr, int l, int r) {
        if (l >= r) return;
        int[] range = partition2(arr, l, r);
        process2(arr, l, range[0] - 1);
        process2(arr, range[1] + 1, r);
    }

    private static void process3(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] range = partition2(arr, l, r);
        process3(arr, l, range[0] - 1);
        process3(arr, range[1] + 1, r);
    }


    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int l = 0;
        int r = arr.length - 1;
        process1(arr, l, r);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int l = 0;
        int r = arr.length - 1;
        process2(arr, l, r);
    }


    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int l = 0;
        int r = arr.length - 1;
        process3(arr, l, r);
    }


}
