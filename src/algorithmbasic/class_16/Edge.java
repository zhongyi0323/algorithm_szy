package algorithmbasic.class_16;

/**
 * @Author sunzhongyi
 * @Date 2021/4/2
 * 边对应的描述
 */
public class Edge {
    public int weight;//边的权重
    public Node from;//起始点
    public Node to;//终点

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
