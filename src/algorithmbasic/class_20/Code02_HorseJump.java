package algorithmbasic.class_20;

/**
 * @Author sunzhongyi
 * @Date 2021/9/14
 * <p>
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 */
public class Code02_HorseJump {

    public static int jump(int a, int b, int k) {

        return process(0, 0, k, a, b);
    }

    private static int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (rest == 0) {
            return x == a && y == b ? 1 : 0;
        }
        int ways = process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        return ways;
    }


    public static int dp(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = 0;
                    dp[x][y][rest] += turn(x - 1, y - 2, rest - 1,dp);
                    dp[x][y][rest] += turn(x - 2, y - 1, rest - 1,dp);
                    dp[x][y][rest] += turn(x - 2, y + 1, rest - 1,dp);
                    dp[x][y][rest] += turn(x - 1, y + 2, rest - 1,dp);
                    dp[x][y][rest] += turn(x + 1, y + 2, rest - 1,dp);
                    dp[x][y][rest] += turn(x + 2, y + 1, rest - 1,dp);
                    dp[x][y][rest] += turn(x + 2, y - 1, rest - 1,dp);
                    dp[x][y][rest] += turn(x + 1, y - 2, rest - 1,dp);
                }
            }
        }
        return dp[0][0][k];
    }

    public static int turn(int x,int y,int rest,int[][][] dp){
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }


    public static int ways(int a, int b, int step) {
        return f(0, 0, step, a, b);
    }

    public static int f(int i, int j, int step, int a, int b) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (step == 0) {
            return (i == a && j == b) ? 1 : 0;
        }
        return f(i - 2, j + 1, step - 1, a, b) + f(i - 1, j + 2, step - 1, a, b) + f(i + 1, j + 2, step - 1, a, b)
                + f(i + 2, j + 1, step - 1, a, b) + f(i + 2, j - 1, step - 1, a, b) + f(i + 1, j - 2, step - 1, a, b)
                + f(i - 1, j - 2, step - 1, a, b) + f(i - 2, j - 1, step - 1, a, b);

    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways(x, y, step));

        System.out.println(jump(x, y, step));
        System.out.println(dp(x, y, step));
    }
}
