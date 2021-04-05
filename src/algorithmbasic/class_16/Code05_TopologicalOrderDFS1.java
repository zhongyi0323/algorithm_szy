package algorithmbasic.class_16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @Author sunzhongyi
 * @Date 2021/4/4
 */
public class Code05_TopologicalOrderDFS1 {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    public static class MyComparator implements Comparator<Record>{

        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        ArrayList<Record> records = new ArrayList<>();
        for (DirectedGraphNode cur : graph){
            records.add(f(cur,order));
        }
        records.sort(new MyComparator());
        ArrayList<DirectedGraphNode> ans  = new ArrayList<>();
        for (Record re : records){
            ans.add(re.node);
        }
        return ans;
    }

    public Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {

        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int deep = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            deep = Math.max(deep, f(next, order).deep);
        }
        Record ans = new Record(cur,deep+1);
        order.put(cur,ans);
        return ans;
    }
}
