package algorithmbasic.class_19;


/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code01_Knapsack {


    public static int maxVal1(int[] w, int[] v, int bag) {
        if (v == null || w == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process1(w, v, 0, bag);

    }

    private static int process1(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        //p1 没选当前index位置的货物，其后续index+1 位置，剩余rest个包大小的所有可能性
        int p1 = process1(w, v, index + 1, rest);
        int p2 = 0;
        //next 先看下选中当前index位置后，是否有剩余的空间？
        int next = process1(w, v, index + 1, rest - w[index]);
        //无剩余空间表示当前位置不能选返回-1，有剩余的话表示可选，p2 记录为选择当前位置和后续的选择的val
        p2 = next == -1 ? -1 : v[index] + next;
        //p1、p2 谁大 返回谁
        return Math.max(p1, p2);
    }


    /**
     * 动态规划
     * 1。n+1行 bag+1列的二维数组，第n+1行都为0表示越界了
     * 2。从n行开始向上填充，填充从左到右
     * @param w
     * @param v
     * @param bag
     * @return
     */
    private static int dp(int[] w, int[] v, int bag) {
        if (v == null || w == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int n = w.length;
        int[][] dp = new int[n + 1][bag + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int ans = 0;
                int p1 = dp[index + 1][rest];
                int p2 = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]] + v[index];
                ans = Math.max(p1, p2);
                dp[index][rest] = ans;
            }
        }
        return dp[0][bag];

    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxVal1(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }


}
