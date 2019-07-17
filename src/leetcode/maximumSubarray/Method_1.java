package leetcode.maximumSubarray;

/**
 * @Author: sunzhongyi
 * @Date: 2019/7/16 20:27
 * @description：${description}
 */
public class Method_1 {

    public int  maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num : nums){
            if(sum > 0){
                sum += num;
            }else{
                sum = num;
            }
            ans = Math.max(ans,sum);
        }
        return ans;
    }
}
