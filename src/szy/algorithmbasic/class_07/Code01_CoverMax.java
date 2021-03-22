package szy.algorithmbasic.class_07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class Code01_CoverMax {
    public static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static int getMaxCover(int[][] lines) {
        Line[] lines1 = new Line[lines.length];

        for (int i = 0; i < lines.length; i++) {
            lines1[i] = new Line(lines[i][0], lines[i][1]);
        }
        //排序
        Arrays.sort(lines1, new StartComparator());
        // 小根堆，每一条线段的结尾数值，使用默认的
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines1.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines1[i].start) {
                heap.poll();
            }
            heap.add(lines1[i].end);
            max = Math.max(max, heap.size());
        }
        return max;
    }
}
