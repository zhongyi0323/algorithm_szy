package algorithmbasic.class_16;

import algorithmzuo.zuo_class33.Hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author sunzhongyi
 * @Date 2021/4/2
 * 图的宽度优先遍历
 */
public class Code01_BFS {


    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            ArrayList<Node> nexts = cur.nexts;
            for (Node next : nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
