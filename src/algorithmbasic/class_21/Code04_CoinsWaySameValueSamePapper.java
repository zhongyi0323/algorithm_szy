package algorithmbasic.class_21;

import java.util.HashMap;
import java.util.Map;

import static algorithmzuo.zuo_class21.Code04_CoinsWaySameValueSamePapper.coinsWay;
import static algorithmzuo.zuo_class21.Code04_CoinsWaySameValueSamePapper.dp1;
import static algorithmzuo.zuo_class21.Code04_CoinsWaySameValueSamePapper.printArray;
import static algorithmzuo.zuo_class21.Code04_CoinsWaySameValueSamePapper.randomArray;

/**
 * @Author sunzhongyi
 * @Date 2021/9/16
 * <p>
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code04_CoinsWaySameValueSamePapper {


    public static class Info {
        private int[] coins;//面值
        private int[] zhangs; //每张面值对应的张数

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }
    }

    private static Info getInfo(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        int[] coins = new int[map.size()];
        int[] zhangs = new int[map.size()];
        int index = 0;
        for (Integer key : map.keySet()) {
            coins[index] = key;
            zhangs[index++] = map.get(key);
        }
        return new Info(coins, zhangs);
    }

    public static int getWay1(int[] arr, int aim) {
        Info info = getInfo(arr);
        return dp(info.coins, info.zhangs, 0, aim);
    }

    public static int getWay2(int[] arr, int aim) {
        Info info = getInfo(arr);
        return dp2(info.coins, info.zhangs, 0, aim);
    }

    //coins
    private static int dp2(int[] coins, int[] zhangs, int index, int aim) {
        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - coins[i]  >= 0){
                    dp[i][rest] += dp[i][rest - coins[i]];
                }
                if (rest - coins[i]* (zhangs[i]+1)>=0){
                    dp[i][rest]-= dp[i+1][rest - coins[i]* (zhangs[i]+1)];
                }
            }
        }
        return dp[0][aim];
    }

    private static int dp(int[] coins, int[] zhangs, int index, int aim) {
        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                for (int zhang = 0; zhang <= zhangs[i] && rest - coins[i] * zhang >= 0; zhang++) {
                    dp[i][rest] += dp[i + 1][rest - zhang * coins[i]];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = getWay1(arr, aim);
            int ans3 = getWay2(arr, aim);
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
