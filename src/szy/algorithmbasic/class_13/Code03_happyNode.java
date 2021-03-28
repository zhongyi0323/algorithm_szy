package szy.algorithmbasic.class_13;

import java.util.List;

/**
 * @Author sunzhongyi
 * @Date 2021/3/28
 * <p>
 * 派对的最大快乐值
 * 可能性分析最关键
 */
public class Code03_happyNode {

    public static class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> subordinates; // 这名员工有哪些直接下级

        public Employee(int happy, List<Employee> subordinates) {
            this.happy = happy;
            this.subordinates = subordinates;
        }
    }

    public static class Info {
        private int no;
        private int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    public static Info process(Employee x) {
        if (x == null) return new Info(0, 0);
        int no = 0;
        int yes = x.happy;
        for (Employee employee : x.subordinates) {
            Info nextInfo = process(employee);
            yes += nextInfo.no;
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new Info(no, yes);
    }

}
