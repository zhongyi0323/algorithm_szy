package algorithmbasic.class_02;

import algorithmbasic.util.MainUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Code04_KMcode {

    public static int test(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key) == k) {
                return key;
            }
        }
        return -1;
    }


    // 只有一种数出现了K次，其他数都出现了M次
    public static int getKval(int[] arr, int k, int m) {
        int sums = 0;
        int[] ans = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                ans[i] += ((num >> i) & 1);
            }
        }
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] % m != 0) {
                sums |= (1 << i);
            }
        }
        return sums;
    }

    /**
     * 生成随机数组
     *
     * @param kinds 一共多少种数
     * @param range 取值范围
     * @param k
     * @param m
     * @return
     */
    public static int[] randomArray(int kinds, int range, int k, int m) {
        //设置出现k次的数的值
        int kval = radomRange(range);
        int length = (kinds - 1) * m + k;
        int[] arr = new int[length];
        int index = 0;
        //将出现k次的值添加到数组中
        for (; index < k; index++) {
            arr[index] = kval;
        }
        kinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(kval);
        //将其他出现m次的值一次添加到数组中
        while (kinds > 0) {
            int curVal = 0;
            do {
                curVal = radomRange(range);
            } while (set.contains(curVal));
            kinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curVal;
            }
        }
//        //随机打乱顺序
//        for (int i = 0; i < arr.length; i++) {
//            int j = (int) (Math.random() * arr.length);
//            int tmp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = tmp;
//        }
        return arr;
    }

    /**
     * 随机生成 [-range~range] 之间的数
     *
     * @param range
     */
    private static int radomRange(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

    public static void main(String[] args) {

//        int[] arr = {-1, 2, 2, 10, 10, 12, 12};
//        System.out.println(test(arr, 1, 2));
//        System.out.println(getKval(arr, 1, 2));
        int kinds = 5;
        int range = 10;
        int testTimes = 100000;
        int max = 9;
        int a = (int)(Math.random()*max)+1;//a : 1~9
        int b = (int)(Math.random()*max)+1;//b : 1~9
        int k = Math.min(a,b);
        int m = Math.max(a,b);
        if (k == m){
            m++;
        }
        for (int i = 0;i<testTimes;i++){
            int[] arr = randomArray(kinds,range,k,m);
            int ans1 = getKval(arr,k,m);
            int ans2 = test(arr,k,m);
            if(ans1 != ans2){
                System.out.println(ans1);
                System.out.println(ans2);
                MainUtil.printArray(arr);
                System.out.println("出错了！");
            }
        }

    }


}
