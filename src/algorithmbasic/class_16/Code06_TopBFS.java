package algorithmbasic.class_16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author sunzhongyi
 * @Date 2021/4/4
 */
public class Code06_TopBFS {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        HashMap<DirectedGraphNode, Integer> inMap = new HashMap<>();
        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
        for (DirectedGraphNode cur : graph) {
            inMap.put(cur, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode next : node.neighbors) {
                inMap.put(next, inMap.get(next) + 1);
            }
        }
        for (DirectedGraphNode keyNode : inMap.keySet()) {
            if (inMap.get(keyNode) == 0){
                zeroInQueue.add(keyNode);
            }
        }
        while (!zeroInQueue.isEmpty()){
            DirectedGraphNode cur = zeroInQueue.poll();
            ans.add(cur);
            for (DirectedGraphNode next : cur.neighbors){
                inMap.put(next,inMap.get(next)-1);
                if (inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }

}
