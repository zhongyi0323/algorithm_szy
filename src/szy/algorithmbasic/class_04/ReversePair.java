package szy.algorithmbasic.class_04;


import sun.applet.Main;
import szy.algorithmbasic.util.MainUtil;

/**
 * 求一个无序数组中逆序对的对数
 */
public class ReversePair {


    public static int getReversePariNum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;

        int l = 0;
        int r = arr.length - 1;
        return process(arr, l, r);

    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        int leftNum = process(arr, l, mid);
        int rightNum = process(arr, mid + 1, r);
        return leftNum + rightNum + merge(arr, l, mid, r);
    }

    /**
     * merge过程中，从最右侧的索引进行比较
     * @param arr
     * @param l
     * @param mid
     * @param r
     * @return
     */
    public static int merge(int[] arr, int l, int mid, int r) {
        int sum = 0;
        int i = r - l;
        int p1 = mid;
        int p2 = r;
        int[] nums = new int[r - l + 1];
        while (p1 >= l && p2 >= mid + 1) {
            if (arr[p1] <= arr[p2]) {
                nums[i--] = arr[p2--];
            } else {
                sum += (p2 - mid);
                nums[i--] = arr[p1--];
            }
        }
        while (p1 >= l) {
            nums[i--] = arr[p1--];
        }
        while (p2 >= mid + 1) {
            nums[i--] = arr[p2--];
        }
        for (i = 0; i < nums.length; i++) {
            arr[l + i] = nums[i];
        }
        return sum;
    }

    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            int[] arr = MainUtil.generatorRandomArray(100, 10);
            int[] arr2 = MainUtil.copyArray(arr);
            int num1 = getReversePariNum(arr);
            int num2 = comparator(arr2);
            if (num1 != num2) {
                System.out.println("fuck");
                MainUtil.printArray(arr);
            }
        }
    }


}
