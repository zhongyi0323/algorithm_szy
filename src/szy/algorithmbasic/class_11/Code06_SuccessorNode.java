package szy.algorithmbasic.class_11;

/**
 * @Author sunzhongyi
 * @Date 2021/3/26
 * <p>
 * 获取给定节点的后继节点
 */
public class Code06_SuccessorNode {

    public static class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int val) {
            value = val;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        //1.如果当前节点有右孩子，输出改右孩子的最左子数
        if (node.right != null) {
            return getLeftNode(node);
        } else {//2。如果没有右孩子,如果我是父节点的做孩子了，返回父节点
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static Node getLeftNode(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
