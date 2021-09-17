package algorithmbasic.class_21;

import static algorithmzuo.zuo_class20.Code03_Coffee.printArray;
import static algorithmzuo.zuo_class20.Code03_Coffee.randomArray;
import static algorithmzuo.zuo_class21.Code02_CoinsWayEveryPaperDifferent.coinWays;

/**
 * @Author sunzhongyi
 * @Date 2021/9/16
 *
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class Code02_CoinsWayEveryPaperDifferent {


    public static int getWay(int[] arr, int aim) {
        return process(arr, aim, 0);

    }

    private static int process(int[] arr, int aim, int i) {
        if (aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        if (i == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        int p1 = process(arr, aim, i + 1);
        int p2 = process(arr, aim - arr[i], i + 1);
        return p1 + p2;
    }

    public static int dp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 1; rest < aim + 1; rest++) {

                int p1 = dp[i + 1][rest];
                int p2 = 0;
                if (rest - arr[i] >= 0) {
                    p2 = dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = p1 + p2;
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = algorithmzuo.zuo_class21.Code02_CoinsWayEveryPaperDifferent.dp(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
