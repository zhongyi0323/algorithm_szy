package mianshi;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author sunzhongyi
 * @Date 2021/8/13
 */
public class MergeArr {

    public int[] intersect(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<>();
        for (int i : nums1){
            set.add(i);
        }
        for (int j : nums2){
            if (!set.add(j)){

            }
        }
        return null;
    }
}
