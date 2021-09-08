package mianshi;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author sunzhongyi
 * @Date 2021/8/12
 * <p>
 * 给订两个长度为N的有序数据，输出排序后的第N、N+1位置的数
 * <p>
 * 1、3、5、7
 * 2、4、6、8
 * 输出：4，5
 */
public class MedalNum {





    public static int[] getMedalNum(int[] a1, int[] a2, int n) {

        Set set = new HashSet();



        int[] nums = new int[2];
        //长度为1时直接排序
        if (n == 1) {
            nums[0] = Math.max(a1[0], a2[0]);
            nums[1] = Math.min(a1[0], a2[0]);
            return nums;
        }
        //长度为2时，N位置的数一定是两个有序数组第0位中相对大的元素
        if (n == 2) {
            nums[0] = Math.max(a1[0], a2[0]);
            nums[1] = Math.min(a1[1], a2[1]);
        }

        int a1Mid = (n - 1) / 2;
        int a2Mid = (n - 1) / 2;
        if (a1[a1Mid] == a2[a2Mid]) {
            nums[0] = nums[1] = a1[a1Mid];
            return nums;
        }

        if (a1[a1Mid] < a2[a2Mid]) {

        }
        return null;

    }


}
