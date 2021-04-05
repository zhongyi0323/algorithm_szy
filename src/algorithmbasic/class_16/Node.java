package algorithmbasic.class_16;

import java.util.ArrayList;

/**
 * @Author sunzhongyi
 * @Date 2021/4/2
 * <p>
 * 点结构的描述
 */

public class Node {
    public int value;//当前节点的值
    public int in;//当前节点入度
    public int out;//出度
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
