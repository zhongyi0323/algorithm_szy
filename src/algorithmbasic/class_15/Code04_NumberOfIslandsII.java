package algorithmbasic.class_15;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunzhongyi
 * @Date 2021/4/1
 */
public class Code04_NumberOfIslandsII {


    /**
     * 输入 m * n2为数组
     *
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public List<Integer> numIslandsII(int m, int n, int[][] positions) {
        UnionFind unionFind = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(unionFind.connect(position[0], position[1]));
        }
        return ans;
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int row;
        private int col;
        private int sets;

        public UnionFind(int m, int n) {
            int len = m * n;
            row = m;
            col = n;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            sets = 0;
        }

        public int getIndex(int r, int c) {
            return r * col + c;
        }

        public int findFather(int i) {
            int index = 0;
            while (i != parent[i]) {
                help[index++] = i;
                i = parent[i];
            }
            while (index > 0) {
                parent[help[--index]] = i;
            }
            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (r1 == row || c1 == col || r2 == row || c2 == col ||
                    r1 < 0 || r2 < 0 || c1 < 0 || c2 < 0) {
                return;
            }
            int i1 = getIndex(r1, c1);
            int i2 = getIndex(r2, c2);
            if (i1 == i2) {
                return;
            }
            int f1 = findFather(i1);
            int f2 = findFather(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int connect(int r, int c) {
            int index = getIndex(r, c);
            //如果当前位置从来没有来过开始进行验证
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r - 1, c, r, c);
                union(r, c - 1, r, c);
                union(r, c + 1, r, c);
                union(r + 1, c, r, c);
            }
            return sets;
        }
    }

}
