package algorithmbasic.class_16;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @Author sunzhongyi
 * @Date 2021/4/4
 * <p>
 * K算法 最小生成树
 */
public class Code07_Kruskal {

    public static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight- o2.weight;
        }
    }

    public static class UnionFind {

        public HashMap<Node, Node> parent;

        public HashMap<Node, Integer> size;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.size = new HashMap<>();
        }

        public void keySet(Collection<Node> list) {
            parent.clear();
            size.clear();
            for (Node node : list) {
                parent.put(node, node);
                size.put(node, 1);
            }
        }

        public Node findFathor(Node n) {
            Queue<Node> queue = new LinkedList<>();
            while (n != parent.get(n)) {
                queue.add(n);
                n = parent.get(n);
            }
            while (!queue.isEmpty()) {
                parent.put(queue.poll(), n);
            }
            return n;
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node f1 = findFathor(a);
            Node f2 = findFathor(b);
            if (f1 != f2) {
                int s1 = size.get(f1);
                int s2 = size.get(f2);
                if (s1 >= s2) {
                    parent.put(b, a);
                    size.put(a, s1 + s2);
                    size.remove(b);
                } else {
                    parent.put(a, b);
                    size.put(b, s2 + s1);
                    size.remove(a);
                }
            }
        }

        public int getSize() {
            return size.size();
        }
        public boolean isSameSet(Node n1,Node n2){
            return findFathor(n1) == findFathor(n2);
        }

        public static Set<Edge> kruskalMST(Graph graph){
            UnionFind uf = new UnionFind();
            uf.keySet(graph.nodes.values());
            // 从小的边到大的边，依次弹出，小根堆！
            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
            for (Edge edge : graph.edges) { // M 条边
                priorityQueue.add(edge);  // O(logM)
            }
            Set<Edge> result = new HashSet<>();
            while (!priorityQueue.isEmpty()){
                Edge edge = priorityQueue.poll();
                if (uf.isSameSet(edge.from, edge.to)){
                    result.add(edge);
                    uf.union(edge.from, edge.to);
                }
            }
            return result;
        }
    }

}
