package algorithmbasic.class_19;

import static algorithmzuo.zuo_class19.Code02_ConvertToLetterString.dp;

/**
 * @Author sunzhongyi
 * @Date 2021/9/9
 * <p>
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code02_ConvertToLetterString {

    public static int ways(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process(s.toCharArray(), 0);
    }

    //从i位置之前的不管
    //从i位置后所有的可能性
    private static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }
        if (str[index] == '0') {
            return 0;
        }
        //如果选了当前位置 后续所有的可能性；
        int ways = process(str, index + 1);
        //如果选了这个位置index 和 index+1；
        if (index + 1 < str.length && (str[index] - '0') * 10 + str[index + 1] - '0' < 27) {
            ways += process(str, index + 2);
        }
        return ways;
    }

    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int index = n - 1; index >= 0; index--) {
            if (str[index] == '0') {
                dp[index] = 0;
            } else {
                int ans = dp[index + 1];
                if (index + 1 < str.length && (str[index] - '0') * 10 + str[index + 1] - '0' < 27) {
                    ans += dp[index + 2];
                }
                dp[index] = ans;
            }

        }
        return dp[0];

    }


    public static void main(String[] args) {
        System.out.println(ways("7210231231232031203123"));
        System.out.println(dp("7210231231232031203123"));
    }

}
