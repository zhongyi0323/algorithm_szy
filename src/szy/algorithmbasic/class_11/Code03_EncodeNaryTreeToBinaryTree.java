package szy.algorithmbasic.class_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:sunzhongyi
 * @Date:2021/3/24 将多叉树转成二叉树的形式
 * 构建思想：
 * 将子节点对应的值都以左孩子的右边树进行处理
 */
public class Code03_EncodeNaryTreeToBinaryTree {
    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    class CodeC {

        public TreeNode encode(Node node) {
            if (node == null) return null;
            TreeNode head = new TreeNode(node.val);
            head.left = en(node.children);
            return head;
        }

        private TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node node : children) {
                TreeNode treeNode = new TreeNode(node.val);
                if (head == null) {
                    head = treeNode;
                } else {
                    cur.right = treeNode;
                }
                cur = treeNode;
                cur.left = en(node.children);
            }
            return head;
        }

        public Node deCode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));

        }

        private List<Node> de(TreeNode root) {
            List<Node> children = new ArrayList<>();
            while (root != null) {
                Node cur = new Node(root.val, de(root.left));
                children.add(cur);
                root = root.right;
            }
            return children;
        }

    }


}
