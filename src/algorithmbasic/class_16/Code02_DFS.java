package algorithmbasic.class_16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

/**
 * @Author sunzhongyi
 * @Date 2021/4/2
 *
 * 深度优先遍历
 */
public class Code02_DFS {

    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        HashSet<Node> set = new HashSet<>();//存放已经遍历过的节点
        Stack<Node> stack = new Stack<>();
        set.add(start);
        stack.add(start);
        System.out.println(start.value);//入栈打印
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            ArrayList<Node> nexts = node.nexts;
            for (Node next : nexts) {//如果next节点
                if (!set.contains(next)) {//没有打印过
                    stack.add(node);//现将父节点压栈
                    stack.add(next);//当前节点压栈
                    set.add(next);//保存起来
                    System.out.println(next.value);//打印当前节点信息
                }
            }
        }
    }
}
