package algorithmbasic.class_16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author sunzhongyi
 * @Date 2021/4/5
 * <p>
 * Dijkstra算法
 */
public class Code09_Dijkstra {


    public static HashMap<Node, Integer> dijkstraMST(Node from) {
        HashMap<Node, Integer> distancemap = new HashMap<>();
        HashSet<Node> selectNode = new HashSet<>();
        distancemap.put(from, 0);
        Node minNode = getMinNode(distancemap, selectNode);
        while (!selectNode.contains(minNode)) {
            int distance = distancemap.get(minNode);
            for (Edge edge : minNode.edges) {
                if (!distancemap.containsKey(edge.to)) {
                    distancemap.put(edge.to, edge.weight + distance);
                } else {
                    distance = Math.min(distancemap.get(edge.to), distance + edge.weight);
                    distancemap.put(edge.to, distance);
                }
            }
            selectNode.add(minNode);
            minNode = getMinNode(distancemap, selectNode);
        }
        return distancemap;
    }

    private static Node getMinNode(HashMap<Node, Integer> distancemap, HashSet<Node> selectNode) {
        Node minNode = null;
        int distance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distancemap.entrySet()) {
            Node node = entry.getKey();
            Integer value = entry.getValue();
            if (!selectNode.contains(node) && value <= distance) {
                minNode = node;
                distance = value;
            }
        }
        return minNode;
    }
}
