package algorithmbasic.class_16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @Author sunzhongyi
 * @Date 2021/4/2
 *
 * 描述
 * 给定一个有向图，图节点的拓扑排序定义如下:
 *
 * 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
 * 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
 * 针对给定的有向图找到任意一种拓扑排序的顺序.
 *
 * [0, 1, 2, 3, 4, 5]
 * [0, 2, 3, 1, 5, 4]
 * // OJ链接：https://www.lintcode.com/problem/topological-sorting
 */
public class Code04_TopologicalOrderDFS2 {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 提交下面的
    public static class Record {
        public DirectedGraphNode node;
        public long nodes;

        public Record(DirectedGraphNode n, long o) {
            node = n;
            nodes = o;
        }
    }

    public static class MyComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o1.nodes == o2.nodes ? 0 : o1.nodes < o2.nodes ? 1 : -1;
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<Record> result = new ArrayList<>();
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        HashMap<DirectedGraphNode, Record> map = new HashMap<>();
        for (DirectedGraphNode cur : graph) {
//            result.add(f(cur, map));
            f(cur, map);
        }
        for (Record r : map.values()) {
            result.add(r);
        }
        result.sort(new MyComparator());
        for (Record record : result) {
            ans.add(record.node);
        }
        return ans;
    }


    //根据每个点node 拿到所有点对应的全值 map存储
    //只要出现在map中即表示该点计算过，可直接取值
    public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        long nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            nodes += f(next, order).nodes;
        }
        Record ans = new Record(cur, nodes + 1);
        order.put(cur, ans);
        return ans;
    }
}
