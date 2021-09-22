package algorithmbasic.class_22;

import static algorithmzuo.zuo_class22.Code01_KillMonster.dp2;
import static algorithmzuo.zuo_class22.Code01_KillMonster.right;

/**
 * @Author sunzhongyi
 * @Date 2021/9/17
 * <p>
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Code01_KillMonster {


    public static double ways(int N, int M, int k) {
        return process(N, M, k) / Math.pow(M + 1, k);
    }

    private static int process(int n, int m, int k) {
        if (k == 0) {
            return n <= 0 ? 1 : 0;
        }
        int ways = 0;
        for (int i = 0; i < m; i++) {
            ways(n - i, m, k - 1);
        }
        return ways;
    }


    public static double dp1(int n, int m, int k) {
        if (n < 1 || m < 1 || k < 1) {
            return 0;
        }
        long[][] dp = new long[k + 1][n + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= k; times++) {
            //如果剩下的血量为0时，还有几刀，那砍死怪兽的可能次数就为 (int) Math.pow(m + 1, times)
            dp[times][0] = (int) Math.pow(m + 1, times);
            for (int hp = 1; hp <= n; hp++) {
                long ways = 0;
                for (int i = 0; i <= m; i++) {
                    //如果砍完之后血量>=0 直接加
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {//小于零的话，剩下的所有可能性
                        ways += (long) Math.pow(m + 1, times - 1);
                    }
                    dp[times][hp] = ways;
                }
            }
        }
        return (double) dp[k][n] / Math.pow(m + 1, k);
    }

    public static double dp2(int n, int m, int k) {
        if (n < 1 || m < 1 || k < 1) {
            return 0;
        }
        long[][] dp = new long[k + 1][n + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= k; times++) {
            //如果剩下的血量为0时，还有几刀，那砍死怪兽的可能次数就为 (int) Math.pow(m + 1, times)
            dp[times][0] = (long) Math.pow(m + 1, times);
            for (int hp = 1; hp <= n; hp++) {

                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - m >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - m];
                } else {
                    dp[times][hp] -= (long) Math.pow(m + 1, times - 1);
                }
            }
        }
        return (double) dp[k][n] / Math.pow(m + 1, k);
    }


    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }


}
