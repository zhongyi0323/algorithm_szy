package algorithmbasic.class_14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author sunzhongyi
 * @Date 2021/3/28
 */
public class Code04_IPO {

    public static class Program {

        private int in;//花费
        private int out;//利润
    }

    //按照项目花费升序
    public static class InComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.in - o2.in;
        }
    }

    //按照利润升序
    public static class OutComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.out - o1.out;
        }
    }

    public static int getMoney(Program[] programs, int m, int k) {
        PriorityQueue<Program> inQ = new PriorityQueue<>(new InComparator());
        for (Program program : programs) {
            inQ.add(program);
        }
        PriorityQueue<Program> outQ = new PriorityQueue<>(new OutComparator());
        for (int i = 0; i < k; i++) {
            while (!inQ.isEmpty() && inQ.peek().in < m) {
                outQ.add(inQ.poll());
            }
            if (outQ.isEmpty()){
                return m;
            }else {
                m += outQ.poll().out;
            }
        }
        return m;
    }

}
