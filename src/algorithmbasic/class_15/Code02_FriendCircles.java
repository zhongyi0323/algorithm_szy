package algorithmbasic.class_15;

/**
 * @Author sunzhongyi
 * @Date 2021/4/1
 *
 * leetCode 省份数量
 */
public class Code02_FriendCircles {


    public int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getSets();
    }

    public static class UnionFind {
        //parent[i] = k 表示 i的父亲是k
        public int[] parents;
        //sizes[i] = k 表示 i为父时元素个数
        public int[] sizes;
        //辅助数组，记录沿途的父 替代栈
        public int[] help;
        //有效集合的个数
        public int sets;

        public UnionFind(int N) {
            sizes = new int[N];
            parents = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                sizes[i] = 1;
                parents[i] = i;
            }
        }

        public int find(int i) {
            int k = 0;
            while (parents[i] != i) {
                help[k++] = i;
                i = parents[i];
            }
            while (k > 0) {
                parents[help[--k]] = i;

            }
            return i;
        }

        public void union(int a, int b) {
            int findA = find(a);
            int findB = find(b);
            if (findA != findB) {
                int sizeA = sizes[a];
                int sizeB = sizes[b];
                int big = sizeA >= sizeB ? a : b;
                if (big == a) {
                    sizes[a] = sizeA + sizeB;
                    parents[findB] = findA;
                } else {
                    sizes[b] = sizeA + sizeB;
                    parents[findA] = findB;
                }
                sets--;
            }
        }

        public int getSets() {
            return sets;
        }
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        Code02_FriendCircles friendCircles = new Code02_FriendCircles();
        friendCircles.findCircleNum(m);
    }
}
