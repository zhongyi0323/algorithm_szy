package algorithmbasic.class_15;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author sunzhongyi
 * @Date 2021/4/1
 * <p>
 * 并查集
 */
public class Code01_UnionFind {

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizes;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizes = new HashMap<>();
            for (V v : values) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parents.get(cur)) {
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(a));
        }

        public void union(V a, V b) {
            Node nodeA = nodes.get(a);
            Node nodeB = nodes.get(b);
            int sizeA = sizes.get(nodeA);
            int sizeB = sizes.get(nodeB);
            Node<V> big = sizeA > sizeB ? nodeA : nodeB;
            Node<V> small = big == nodeA ? nodeB : nodeA;
            parents.put(small, big);
            sizes.put(big, sizeA + sizeB);
            sizes.remove(small);

        }

        public int sets(){
            return sizes.size();
        }
    }

}
