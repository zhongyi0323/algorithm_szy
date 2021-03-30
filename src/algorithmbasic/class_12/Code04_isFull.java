package algorithmbasic.class_12;

/**
 * @Author sunzhongyi
 * @Date 2021/3/27
 * <p>
 * 满二叉树
 * 节点数和树的高度关系： nodes = 2^n-1 - 1
 */
public class Code04_isFull {


    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static class Info {
        private int height;//树的高度
        private int nodes;//节点数

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static Info process(Node head) {
        if (head == null) return new Info(0, 0);
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = 1;
        int nodes = 1;
        height = Math.max(leftInfo.height, rightInfo.height) + 1;
        nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height, nodes);
    }

    /**
     * 节点数 和树的高度
     * @param head
     * @return
     */
    public static boolean isFull(Node head) {
        Info info = process(head);
        return info.nodes == 1 << info.height - 1;
    }

}
