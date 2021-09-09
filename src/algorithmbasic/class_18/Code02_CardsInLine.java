package algorithmbasic.class_18;

/**
 * @Author sunzhongyi
 * @Date 2021/9/9
 */
public class Code02_CardsInLine {

    public static int ways1(int[] arrs) {
        int l = 0;
        int r = arrs.length - 1;
        int first = f1(arrs, l, r);
        int second = g1(arrs, l, r);
        return Math.max(first, second);

    }

    private static int g1(int[] arrs, int l, int r) {
        if (l == r) {
            return 0;
        }
        int first = f1(arrs, l + 1, r);
        int second = f1(arrs, l, r - 1);
        return Math.min(first, second);
    }

    private static int f1(int[] arrs, int l, int r) {
        if (l == r) {
            return arrs[l];
        }
        int first = arrs[l] + g1(arrs, l + 1, r);
        int second = arrs[r] + g1(arrs, l, r - 1);
        return Math.max(first, second);
    }

    /**
     * 加缓存
     *
     * @param arrs
     * @return
     */
    public static int ways2(int[] arrs) {
        int l = 0;
        int r = arrs.length - 1;
        int n = arrs.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(arrs, l, r, fmap, gmap);
        int second = g2(arrs, l, r, fmap, gmap);
        return Math.max(first, second);

    }

    private static int g2(int[] arrs, int l, int r, int[][] fmap, int[][] gmap) {
        if (gmap[l][r] != -1) {
            return gmap[l][r];
        }
        int ans = 0;
        if (l == r) {
            ans = 0;
        } else {
            int f1 = f2(arrs, l, r - 1, fmap, gmap);
            int f2 = f2(arrs, l + 1, r, fmap, gmap);
            ans = Math.min(f1, f2);
        }
        gmap[l][r] = ans;
        return ans;
    }

    private static int f2(int[] arrs, int l, int r, int[][] fmap, int[][] gmap) {
        if (fmap[l][r] != -1) {
            return fmap[l][r];
        }
        int ans = 0;
        if (l == r) {
            ans = arrs[l];
        } else {
            int f1 = arrs[l] + g2(arrs, l + 1, r, fmap, gmap);
            int f2 = arrs[r] + g2(arrs, l, r - 1, fmap, gmap);
            ans = Math.max(f1, f2);
        }
        fmap[l][r] = ans;
        return ans;
    }


    public static int ways3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        //构建先手表结构
        int[][] fmap = new int[N][N];
        //构建后手表结构
        int[][] gmap = new int[N][N];

        //先手情况下，l == r 时 值为 arr[l]
        //后手情况下，l == r 时 值为 0；
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }
        //填充表
        //fmap中的位置数据，依赖于 gmap中的数据
        //gmap中的位置数据，依赖于 fmap中的数据 相互依赖
        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) {
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }


    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(ways1(arr));
        System.out.println(ways2(arr));
        System.out.println(ways3(arr));

    }
}
