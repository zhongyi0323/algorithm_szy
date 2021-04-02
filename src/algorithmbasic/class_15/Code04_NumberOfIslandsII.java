package algorithmbasic.class_15;

import java.util.ArrayList;
import java.util.HashMap;
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


    //避免并查集初始化问题
    public List<Integer> numIslandsIII(int m, int n, int[][] positions) {

        UnionFind2 unionFind2 = new UnionFind2();
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] position : positions) {
            list.add(unionFind2.connect(position[0], position[1]));

        }
        return list;
    }

    public static class UnionFind2 {
        public HashMap<String, String> parent;
        public HashMap<String, Integer> size;
        private ArrayList<String> help;
        public int sets;

        public UnionFind2() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            sets = 0;
        }

        public String find(String cur) {
            while (!cur.equals(parent.get(cur))) {
                help.add(cur);
                cur = parent.get(cur);
            }
            for (String str : help) {
                parent.put(str, cur);
            }
            return cur;
        }

        public void union(String a, String b) {
            if (parent.containsKey(a) && parent.containsKey(b)) {
                String f1 = find(a);
                String f2 = find(b);
                if (!f1.equals(f2)) {
                    int size1 = size.get(a);
                    int size2 = size.get(b);
                    if (size1 >= size2) {
                        size.put(a, size1 + size2);
                        parent.put(b, a);
                    } else {
                        size.put(b, size1 + size2);
                        parent.put(a, b);
                    }
                    sets--;
                }
            }
        }

        public int getSets() {
            return sets;
        }

        public int connect(int r, int c) {
            String cur = r + "_" + c;
            if (!parent.containsKey(cur)) {
                parent.put(cur, cur);
                size.put(cur, 1);
                sets++;
                union(cur, r - 1 + "_" + c);
                union(cur, r + 1 + "_" + c);
                union(cur, r + "_" + (c - 1));
                union(cur, r + "_" + (c + 1));
            }
            return sets;
        }
    }

}
