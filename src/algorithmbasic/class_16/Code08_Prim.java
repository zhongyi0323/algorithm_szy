package algorithmbasic.class_16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author sunzhongyi
 * @Date 2021/4/5
 * <p>
 * prim算法求最小生成树
 */
public class Code08_Prim {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> prim(Graph graph) {
        HashSet<Node> nodeSet = new HashSet<>();//存放计算过的节点
        PriorityQueue<Edge> queue = new PriorityQueue<>();//优先级队列
        HashSet<Edge> result = new HashSet<>();//返回结果
        for (Node node : graph.nodes.values()) {//防森林
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()) {
                    Edge edge = queue.poll();//弹出要解锁的边
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)) {//如果当前边不会产生环路
                        result.add(edge);//将该边添加到结果集
                        nodeSet.add(toNode);//将节点加入到set中
                        for (Edge toEdge : toNode.edges) {
                            queue.add(toEdge);//解锁新的边
                        }
                    }
                }
            }
        }
        return result;
    }
}
