package algorithmbasic.class_22;

import static algorithmzuo.zuo_class22.Code03_SplitNumber.dp1;

/**
 * @Author sunzhongyi
 * @Date 2021/9/22
 */
public class Code03_SplitNumber {

    public static int ways(int aim) {
        if (aim < 0) {
            return 0;
        }
        if (aim == 1) {
            return 1;
        }
        return process(1, aim - 1);

    }

    private static int process(int i, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (i > rest) {
            return 0;
        }
        if (i == rest) {
            return 1;
        }
        int ways = 0;
        for (int j = i; j <= rest; j++) {
            ways += process(j, rest - j);
        }
        return ways;
    }

    public static int dp(int n) {

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = i + 1; rest <= n; rest++) {

                dp[i][rest] = dp[i][rest - i] + dp[i + 1][rest];
//                //循环遍历
//                int ways = 0;
//                for (int j = i; j <= rest; j++) {
//                    ways += dp[j][rest - j];
//                }
//                dp[i][rest] = ways;

            }
        }
        return dp[1][n - 1];
    }


    public static void main(String[] args) {
        int test = 39;
        System.out.println(ways(test));
        System.out.println(dp(test));
    }

}
