package algorithmbasic.class_18;

/**
 * @Author sunzhongyi
 * @Date 2021/9/8
 * <p>
 * 暴力递归与动态规划
 */
public class Code01_RobotWalk {

    /**
     * @param start 机器人起始位置
     * @param n     一共有的位置
     * @param k     机器人需要走的步数
     * @param ami   目标位置
     * @return
     */
    public static int ways1(int start, int n, int k, int ami) {
        return process1(start, n, k, ami);
    }

    private static int process1(int cur, int n, int rest, int ami) {
        //base case
        if (rest == 0) {//如果当前没有步数可走了
            return ami == cur ? 1 : 0;
        }
        if (cur == 1) {
            return process1(2, n, rest - 1, ami);
        }
        if (cur == n) {
            return process1(n - 1, n, rest - 1, ami);
        }
        return process1(cur - 1, n, rest - 1, ami) + process1(cur + 1, n, rest - 1, ami);
    }


    public static int ways2(int start, int n, int k, int ami) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start, n, k, ami, dp);
    }

    /**
     * 递归过程中，如果计算过当前位置的信息，直接返回结果，避免重复计算
     *
     * @param cur
     * @param n
     * @param rest
     * @param ami
     * @param dp
     * @return
     */
    private static int process2(int cur, int n, int rest, int ami, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        //base case
        if (rest == 0) {//如果当前没有步数可走了
            ans = ami == cur ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, n, rest - 1, ami, dp);
        } else if (cur == n) {
            ans = process2(n - 1, n, rest - 1, ami, dp);
        } else {
            ans = process2(cur - 1, n, rest - 1, ami, dp) + process2(cur + 1, n, rest - 1, ami, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    public static int ways3(int start, int n, int k, int ami) {
        int[][] dp = new int[n + 1][k + 1];
        dp[ami][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            //只有目标位置 rest = 0时 其数值为1；其他的均为0
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 1; cur < n; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[n][rest] = dp[n - 1][rest - 1];
        }
        return dp[start][k];
    }


    public static void main(String[] args) {
        System.out.println(ways1(2, 5, 6, 4));
        System.out.println(ways2(2, 5, 6, 4));
        System.out.println(ways3(2, 5, 6, 4));
    }

}
