package szy.algorithmbasic.class_14;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author sunzhongyi
 * @Date 2021/3/28
 *
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 */
public class Code03_BestArrange {

    public static class Program {
        public int start;
        public int end;
    }

    public static class MyComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int getBestArrange(Program[] programs) {
        Arrays.sort(programs, new MyComparator());
        int timeLine = 0;
        int ans = 0;
        for (int i = 0; i < programs.length; i++) {
            if (timeLine <= programs[i].start) {
                ans++;
                timeLine = programs[i].end;
            }
        }
        return ans;

    }
}
