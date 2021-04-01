package algorithmbasic.class_15;

import algorithmzuo.zuo_class33.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author sunzhongyi
 * @Date 2021/4/1
 * <p>
 * // 本题为leetcode原题
 * // 测试链接：https://leetcode.com/problems/number-of-islands/
 * // 所有方法都可以直接通过
 */
public class Code03_NumberOfIslands {

    public static int numIslands1(char[][] board) {
        int islands = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    islands++;
                    infect(board, i, j);
                }
            }
        }
        return islands;
    }

    private static void infect(char[][] board, int i, int j) {
        //数组下标越界或者board[i][j]位置为'1'的都变成0
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = 0;
        infect(board, i - 1, j);//上
        infect(board, i + 1, j);//下
        infect(board, i, j - 1);//左
        infect(board, i, j + 1);//右
    }

    public static int numIslands2(char[][] board) {
        UnionFind unionFind = new UnionFind(board);
        int row = board.length;
        int col = board[0].length;
        for (int i = 1; i < col; i++) {
            if (board[0][i] == '1' && board[0][i - 1] == '1') {
                unionFind.union(0, i, 0, i - 1);
            }
        }
        for (int i = 1; i < row; i++) {
            if (board[i][0] == '1' && board[i - 1][0] == '1') {
                unionFind.union(i, 0, i - 1, 0);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1') {
                    if (board[i - 1][j] == '1') {
                        unionFind.union(i - 1, j, i, j);
                    }
                    if (board[i][j - 1] == '1') {
                        unionFind.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return unionFind.getSets();
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int sets;

        public UnionFind(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;
            int num = row * col;
            parent = new int[num];
            size = new int[num];
            help = new int[num];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == '1') {
                        int index = index(i, j);
                        parent[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
        }

        public int find(int i) {
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
            int index1 = index(r1, c1);
            int index2 = index(r2, c2);
            int f1 = find(index1);
            int f2 = find(index2);
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

        public int getSets() {
            return sets;
        }

        private int index(int r, int c) {
            return col * r + c;
        }
    }

    public static int numIslands3(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        List<Dot> dataList = new ArrayList<>();
        Dot[][] dots = new Dot[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dataList.add(dots[i][j]);
                }
            }
        }
        UnionFind2<Dot> unionFind2 = new UnionFind2(dataList);
        for (int c = 1; c < col; c++) {
            if (dots[0][c] != null && dots[0][c - 1] != null) {
                unionFind2.union(dots[0][c], dots[0][c - 1]);
            }
        }
        for (int r = 1; r < row; r++) {
            if (dots[r][0] != null && dots[r - 1][0] != null) {
                unionFind2.union(dots[r][0], dots[r - 1][0]);
            }
        }
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (dots[r][c] != null) {
                    if (dots[r - 1][c] != null) {
                        unionFind2.union(dots[r][c], dots[r - 1][c]);
                    }
                    if (dots[r][c - 1] != null) {
                        unionFind2.union(dots[r][c], dots[r][c - 1]);
                    }
                }
            }
        }
        return unionFind2.getSize();
    }

    public static class Dot {
    }

    public static class Node<V> {
        private V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind2<V> {
        public HashMap<V, Node<V>> nodes;//存放当前数据对应的node节点
        public HashMap<Node<V>, Node<V>> parent;//node节点的父集关系
        public HashMap<Node<V>, Integer> sizeMap;//只存放父集，size即为岛的个数

        public UnionFind2(List<V> datas) {
            nodes = new HashMap<>();
            parent = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : datas) {
                Node node = new Node(cur);
                nodes.put(cur, node);
                parent.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parent.get(cur)) {
                stack.push(cur);
                cur = parent.get(cur);
            }
            while (!stack.isEmpty()) {
                parent.put(stack.pop(), cur);
            }
            return cur;
        }

        //两个岛合并
        public void union(V a, V b) {
            Node<V> father1 = findFather(nodes.get(a));
            Node<V> father2 = findFather(nodes.get(b));
            if (father1 != father2) {
                Integer size1 = sizeMap.get(father1);
                Integer size2 = sizeMap.get(father2);
                Node big = size1 >= size2 ? father1 : father2;
                Node small = big == father1 ? father2 : father1;
                parent.put(small, big);
                sizeMap.put(big, size1 + size2);
                sizeMap.remove(small);//移除非父节点对应的数据
            }
        }

        public int getSize() {
            return sizeMap.size();
        }

    }


    public static void main(String[] args) {
        int row = 0;
        int col = 0;
        char[][] board1 = null;
        board1 = generateRandomMatrix(4, 4);
        numIslands3(board1);
    }

    // 为了测试
    public static char[][] generateRandomMatrix(int row, int col) {
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return board;
    }

}
