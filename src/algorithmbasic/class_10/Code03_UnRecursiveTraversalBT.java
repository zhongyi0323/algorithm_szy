package algorithmbasic.class_10;

import java.util.Stack;

/**
 * @Author sunzhongyi
 * @Date 2021/3/23
 */
public class Code03_UnRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    /**
     * 先序遍历
     *
     * 先将头压栈 ，如果栈不为空，弹出栈顶，输出，如果栈顶有右孩子压栈、如果有左孩子压栈
     * @param head
     */
    public static void pre(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        stack.add(cur);
        while (!stack.isEmpty()){
            cur = stack.pop();
            System.out.println(cur.value);
            if (cur.right!=null){
                stack.add(cur.right);
            }
            if (cur.left!=null){
                stack.add(cur.left);
            }
        }
    }

    /**
     * 后续遍历
     * 参照先序遍历，利用两个栈实现后续遍历
     * @param head
     */
    public static void pos1(Node head){
        if (head== null){
            return;
        }
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        Stack<Node> stackPop = new Stack<>();
        stack.add(cur);
        while (!stack.isEmpty()){
            cur = stack.pop();
            stackPop.push(cur);
            //将头、左 、右一次压栈，输出步骤替换成压入另外一个栈
            if (cur.left!=null){
                stack.push(cur.left);
            }
            if (cur.right!=null){
                stack.push(cur.right);
            }
        }
        while (!stackPop.isEmpty()){
            System.out.println(stackPop.pop().value);
        }
    }

    /**
     * 如果cur左子树不为空，cur = cur.left 压栈
     * cur为空时，stack.pop()输出，cur = cur.right
     * cur为空，stack为空时停止操作
     * @param cur
     */
    public static void in(Node cur){
        if (cur== null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty()||cur!=null){
            if (cur!=null){
                stack.add(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                if (cur!=null){
                    System.out.println(cur.value);
                    cur = cur.right;
                }
            }

        }

    }

    /**
     * 单栈实现后序遍历
     * @param h
     */
    public static void pos2(Node h) {
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }
}
