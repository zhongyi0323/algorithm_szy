package szy.algorithmbasic.class_02;

import szy.algorithmbasic.util.MainUtil;

public class Code03_getMore {

    /**
     * 一个数组中，有两个数出现了奇数次，输出这两个值
     *
     * @param arr
     * @return
     */
    public static int[] getMore(int[] arr) {
        int[] vals = new int[2];
        //结果为a^b
        int eor = Code02_GetOne.getOne(arr);
        //找到最右侧1
        int rightOne = eor & (-eor);
        int eor1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                eor1 ^= arr[i];
            }
        }
        vals[0] = eor1;
        vals[1] = eor ^ eor1;
        return vals;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 3, 4, 4};
        int[] vals = getMore(arr);
        MainUtil.printArray(vals);
    }

}
