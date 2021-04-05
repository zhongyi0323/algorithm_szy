package algorithmbasic.class_16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author sunzhongyi
 * @Date 2021/4/2
 * <p>
 * 拓扑排序（有向无环图）
 */
public class Code03_TopologySort {


    public static List<Node> topologySort(Graph graph) {
        HashMap<Integer, Node> nodes = graph.nodes;
        List<Node> result = new ArrayList<>();
        HashMap<Node, Integer> inMap = new HashMap<>();//所有点的入度信息
        Queue<Node> zeroInQueue = new LinkedList<>();//所有入度为0的队列
        for (Node cur : nodes.values()) {
            inMap.put(cur, cur.in);
            if (cur.in == 0) {
                zeroInQueue.add(cur);
            }
        }
        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();//顺序出队列
            result.add(node);//加入到结果集
            ArrayList<Node> nexts = node.nexts;//将后续节点入度-1，如果为0，直接进入队列
            for (Node next : nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    result.add(next);
                }
            }
        }
        return result;
    }

}
