package algorithmbasic.class_20;

/**
 * @Author sunzhongyi
 * @Date 2021/9/12
 *
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/submissions/
 */
public class Code01_PalindromeSubsequence {

    public static int getLength(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        return process(str, 0, n - 1);
    }

    /**
     * 返回当前L。。。R位置上的公共最大子序列
     *
     * @param str
     * @param L
     * @param R
     * @return
     */
    private static int process(char[] str, int L, int R) {
        if (L > R) {
            return 0;
        }
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = process(str, L + 1, R - 1);
        int p2 = process(str, L, R - 1);
        int p3 = process(str, L + 1, R);
        int p4 = str[L] == str[R] ? 2 + process(str, L + 1, R - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    public static int dp(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        dp[n - 1][n - 1] = 1;
        for (int l = 0; l < n - 1; l++) {
            dp[l][l] = 1;
            dp[l][l + 1] = str[l] == str[l + 1] ? 2 : 1;
        }
        for (int l = n-3; l >=0; l--) {
            for (int r = l + 2; r < n; r++) {
                dp[l][r] = Math.max(dp[l+1][r],dp[l][r-1]);

                if (str[l] == str[r]){
                    dp[l][r] = Math.max(dp[l][r],2+dp[l+1][r-1]);
                }
            }
        }
        return dp[0][n - 1];
    }

}
