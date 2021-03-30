package algorithmbasic.class_03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个数组实现栈
 */
public class Code05_TwoQueuesImplStack {

    //用一个队列作为辅助队列，添加元素正常添加，移除时将数据存到辅助队列，切换引用

    public static Queue<Integer> queue = new LinkedList<>();
    public static Queue<Integer> help = new LinkedList<>();

    /**
     * 向queue队列正常添加元素
     *
     * @param val
     */
    public static void push(Integer val) {
        queue.offer(val);
    }

    /**
     * 取元素时，从queue队列取值，将queue队列元素移动到help队列中，queue弹出最后一个元素出栈
     * 将queue 《---》 help引用交换
     *
     * @return
     */
    public static Integer pop() {
        if (queue.isEmpty()) {
            throw new RuntimeException("当前队列为空");
        }
        while (queue.size() > 1) {
            help.offer(queue.poll());
        }
        Integer val = queue.poll();
        Queue tmp = queue;
        queue = help;
        help = tmp;
        return val;
    }

    /**
     * 将最后一个值也保存到help队列中
     *
     * @return
     */
    public static Integer peek() {
        if (queue.isEmpty()) {
            throw new RuntimeException("当前栈为空！");
        }
        while (queue.size() > 1) {
            help.offer(queue.poll());
        }
        Integer ans = queue.poll();
        help.offer(ans);
        Queue<Integer> tmp = help;
        help = queue;
        queue = tmp;
        return ans;
    }

}
