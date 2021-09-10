package algorithmbasic.class_19;

/**
 * @Author sunzhongyi
 * @Date 2021/9/10
 *
 * https://leetcode-cn.com/problems/longest-common-subsequence/submissions/
 * 求两个字符串的最大公共子序列
 *
 */
public class Code04_LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process(str1, str2, s1.length() - 1, s2.length() - 1);
    }

    /**
     * 返回 str1 从i位置  和str2 从j位置 最大公共子序列
     *
     * @param str1
     * @param str2
     * @param i
     * @param j
     * @return
     */
    private static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[0] == str2[0] ? 1 : 0;
        } else if (i == 0) {
            if (str1[0] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str2[0] == str1[i]) {
                return 1;
            } else {
                return process(str1, str2, i - 1, j);
            }
        } else {

            int p1 = process(str1, str2, i - 1, j);
            int p2 = process(str1, str2, i, j - 1);
            int p3 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;
            return Math.max(Math.max(p1, p2), p3);
        }
    }


    public static int longestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n][m];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        //填充第0行
        for (int j = 1; j < m; j++) {
            if (str1[0] == str2[j]) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        //填充第0列；
        for (int i = 1;i<n;i++){
            if (str2[0] == str1[i]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i-1][0];
            }
        }
        for (int i = 1;i<n;i++){
            for (int j = 1;j<m;j++){
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j-1];
                int p3 = str1[i] == str2[j] ? 1+dp[i-1][j-1] : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }
        return dp[n-1][m-1];
    }
}
