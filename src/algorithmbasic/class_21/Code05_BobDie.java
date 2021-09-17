package algorithmbasic.class_21;

import static algorithmzuo.zuo_class21.Code05_BobDie.livePosibility1;

/**
 * @Author sunzhongyi
 * @Date 2021/9/17
 * <p>
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class Code05_BobDie {

    public static double lifePre(int N, int M, int row, int col, int k) {

        return process(N, M, row, col, k) / Math.pow(4, k);

    }

    public static double lifePre2(int N, int M, int row, int col, int k) {

        return dp(N, M, row, col, k) / Math.pow(4, k);

    }

    private static int process(int n, int m, int row, int col, int rest) {
        if (row >= n || row < 0 || col >= m || col < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int left = process(n, m, row - 1, col, rest - 1);
        int right = process(n, m, row + 1, col, rest - 1);
        int up = process(n, m, row, col - 1, rest - 1);
        int down = process(n, m, row, col + 1, rest - 1);
        return left + right + up + down;
    }


    public static int dp(int n, int m, int row, int col, int k) {
        int[][][] dp = new int[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int left = pick(dp, n, m, i - 1, j, rest - 1);
                    int right = pick(dp, n, m, i + 1, j, rest - 1);
                    int up = pick(dp, n, m, i, j - 1, rest - 1);
                    int down = pick(dp, n, m, i, j + 1, rest - 1);
                    dp[i][j][rest] = left + right + up + down;
                }
            }
        }
        return dp[row][col][k];
    }

    private static int pick(int[][][] dp, int n, int m, int row, int col, int rest) {
        if (row >= n || row < 0 || col >= m || col < 0) {
            return 0;
        }
        return dp[row][col][rest];
    }


    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(lifePre(50, 50, 6, 6, 10));
        System.out.println(lifePre2(50, 50, 6, 6, 10));
    }


}
