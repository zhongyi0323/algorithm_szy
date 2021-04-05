package algorithmbasic.class_16;


import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author sunzhongyi
 * @Date 2021/4/2
 * <p>
 * 图
 */
public class Graph {

    public HashMap<Integer, Node> nodes;

    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
