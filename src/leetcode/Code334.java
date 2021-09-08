package leetcode;

/**
 * @Author sunzhongyi
 * @Date 2021/8/15
 */
public class Code334 {

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < small) {
                small = nums[i];
            } else if (nums[i] < mid) {
                mid = nums[i];
            } else if (nums[i] > mid) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 0, 4, 1, 3};
        System.out.println(increasingTriplet(nums));
        ;

    }
}
