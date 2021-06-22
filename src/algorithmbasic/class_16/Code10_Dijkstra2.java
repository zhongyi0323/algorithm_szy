package algorithmbasic.class_16;

import java.util.HashMap;

/**
 * @Author sunzhongyi
 * @Date 2021/4/5
 */
public class Code10_Dijkstra2 {


    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes;

        private HashMap<Node, Integer> heapIndexMap;

        private HashMap<Node, Integer> distanceMap;

        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }


        public void heapInsert(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                left = (left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])) ?
                        (left + 1) : left;
                int smallest = (distanceMap.get(nodes[left]) < distanceMap.get(nodes[index])) ? left : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node){
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node){
            return isEntered(node) && heapIndexMap.get(node)!=-1;
        }
        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

}
