package szy.algorithmbasic.class_05;

public class CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //求前缀和
        long[] sums = getBeforeSum(nums);
        int l = 0;
        int r = sums.length - 1;
        return process(sums, l, r, lower, upper);
    }

    private static int process(long[] sums, int l, int r, int lower, int upper) {
        if (l == r) {
            return lower <= sums[l] && sums[l] <= upper ? 1 : 0;
        }
        int mid = l + ((r - l) >> 1);
        int leftNum = process(sums, l, mid, lower, upper);
        int rightNum = process(sums, mid + 1, r, lower, upper);
        return leftNum + rightNum + merge(sums, l, mid, r, lower, upper);
    }

    private static int merge(long[] sums, int l, int mid, int r, int lower, int upper) {

        int p1 = l;
        int p2 = mid + 1;
        int winl = l;
        int winr = l;
        int ans = 0;
        //先进行数据计算
        while (p2 <= r) {
            long min = sums[p2] - upper;
            long max = sums[p2] - lower;
            //如果左边的值小于允许的最小值 winl++，最终返回的是第一个符合条件的值
            while (winl <= mid && sums[winl] < min) {
                winl++;
            }
            //如果左边的值小于等于允许的最大值 winr++，最终返回的是最后一个符合条件的值+1
            while (winr <= mid && sums[winr] <= max) {
                winr++;
            }
            p2++;
            ans += winr - winl;
        }

        long[] helps = new long[r - l + 1];
        p1 = l;
        p2 = mid + 1;
        int i = 0;
        //再进行排序
        while (p1 <= mid && p2 <= r) {
            helps[i++] = sums[p1] <= sums[p2] ? sums[p1++] : sums[p2++];
        }
        while (p1 <= mid) {
            helps[i++] = sums[p1++];
        }
        while (p2 <= r) {
            helps[i++] = sums[p2++];
        }
        for (i = 0; i < helps.length; ) {
            sums[l + i] = helps[i++];
        }

        return ans;

    }

    private static long[] getBeforeSum(int[] nums) {
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        return sums;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 3, 2};
        System.out.println(countRangeSum(arr, 2, 5));
    }
}
