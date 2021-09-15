package algorithmbasic.class_21;

import static algorithmzuo.zuo_class21.Code01_MinPathSum.generateRandomMatrix;
import static algorithmzuo.zuo_class21.Code01_MinPathSum.minPathSum1;

/**
 * @Author sunzhongyi
 * @Date 2021/9/15
 */
public class Code01_MinPathSum {


    public static int getMinDp(int[][] arr) {
        int l = arr.length;
        int r = arr[0].length;

        int[][] dp = new int[l][r];
        dp[0][0] = arr[0][0];
        for (int y = 1; y < r; y++) {
            dp[0][y] = dp[0][y - 1] + arr[0][y];
        }
        for (int x = 1; x < l; x++) {
            dp[x][0] = dp[x - 1][0] + arr[x][0];
        }
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < r; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[l - 1][r - 1];
    }

    public static int getMinDp2(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        int[] dp = new int[col];
        dp[0] = arr[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + arr[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] = dp[0] + arr[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = arr[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }
        return dp[col - 1];

    }


    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(getMinDp2(m));
        System.out.println(getMinDp(m));

    }

}
