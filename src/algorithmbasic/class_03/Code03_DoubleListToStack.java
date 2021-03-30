package algorithmbasic.class_03;

/**
 * 双向链表实现栈
 * 先进后出
 */
public class Code03_DoubleListToStack<T> {

    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T data) {
            value = data;
        }
    }

    public static class DoubleEndsQueue<T> {
        private static Node head;//头节点
        private static Node tail;//尾节点

        //添加元素至链表的头部
        public void addFromHead(T val) {
            Node node = new Node(val);
            if (head == null) {
                //如果头节点没有值，直接将头节点和尾节点进行赋值
                head = node;
                tail = node;
            } else {
                //将数据插入到链表的头部，移动头部指针
                node.next = head;
                head.last = node;
                head = node;
            }
        }

        //添加元素至链表的尾部
        public void addFromTail(T val) {
            Node node = new Node(val);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public T popFromHead() {
            if (head == null) return null;
            Node node = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                node.next = null;
            }
            return (T) node.value;
        }

        public T popFromTail() {
            if (head == null) return null;
            Node node = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                node.last = null;
            }
            return (T) node.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class MyStack<T> {
        public DoubleEndsQueue<T> stack;

        public MyStack() {
            stack = new DoubleEndsQueue<T>();
        }

        public void push(T val) {
            stack.addFromTail(val);
        }

        public T pop() {
            return stack.popFromTail();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    public static class MyQueue<T> {
        public DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T val) {
            queue.addFromTail(val);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void main(String[] args) {

        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());

    }
}
