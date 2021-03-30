package algorithmbasic.class_08;


/**
 * 前缀树
 */
public class Code03_TrieTree {


    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String str) {
            if (str == null) return ;
            char[] chars = str.toCharArray();
            Node1 node = root;
            node.pass++;
            for (char c : chars) {
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String str) {
            if (!search(str)){
               return ;
            }
            char[] chars = str.toCharArray();
            Node1 node = root;
            node.pass--;
            for (char c : chars) {
                int index = c - 'a';
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    break;
                }
                node = node.nexts[index];
            }
            if (node != null) {
                node.end--;
            }
        }

        public boolean search(String str) {
            char[] chars = str.toCharArray();
            Node1 node = root;
            for (char c : chars) {
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    return false;
                } else {
                    node = node.nexts[index];
                }
            }
            if (node.end >= 1) {
                return true;
            }
            return false;
        }

        public int searchNum(String str) {
            char[] chars = str.toCharArray();
            Node1 node = root;
            for (char c : chars) {
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                } else {
                    node = node.nexts[index];
                }
            }
            if (node != null) {
                return node.end;
            } else {
                return 0;
            }

        }

        public int prefixNum(String pre) {
            if (pre == null) return 0;
            char[] chars = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (char c : chars) {
                index = c - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }


}
