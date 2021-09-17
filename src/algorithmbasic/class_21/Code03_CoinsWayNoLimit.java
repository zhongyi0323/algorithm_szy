package algorithmbasic.class_21;

import static algorithmzuo.zuo_class20.Code03_Coffee.printArray;
import static algorithmzuo.zuo_class20.Code03_Coffee.randomArray;
import static algorithmzuo.zuo_class21.Code03_CoinsWayNoLimit.coinsWay;
import static algorithmzuo.zuo_class21.Code03_CoinsWayNoLimit.dp1;

/**
 * @Author sunzhongyi
 * @Date 2021/9/16
 * <p>
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code03_CoinsWayNoLimit {

    public static int getWay(int[] arr, int aim) {
        return process(arr, 0, aim);

    }

    private static int process(int[] arr, int index, int aim) {
        if (aim == 0) {
            return 1;
        }
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= aim; zhang++) {
            ways += process(arr, index + 1, aim - zhang * arr[index]);
        }
        return ways;
    }

    public static int dp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[i] <= rest; zhang++) {
                    ways += dp[i + 1][rest - zhang * arr[i]];
                }
                dp[i][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i][rest - arr[i]];
                }
            }
        }
        return dp[0][aim];
    }


    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
