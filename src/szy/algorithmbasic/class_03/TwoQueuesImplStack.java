package szy.algorithmbasic.class_03;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个数组实现栈
 */
public class TwoQueuesImplStack {

    //用一个队列作为辅助队列，添加元素正常添加，移除时将数据存到辅助队列，切换引用

    public static Queue<Integer> queue = new LinkedList<>();
    public static Queue<Integer> help = new LinkedList<>();


    public static void push(Integer val){
        queue.offer(val);
    }

    public static Integer pop(){
        if (queue.isEmpty()){
            throw new RuntimeException("当前队列为空");
        }
        while(queue.size()>1){
            help.offer(queue.poll());
        }
        Integer val = queue.poll();
        Queue tmp = queue;
        queue = help;
        help = tmp;
        return val;
    }

}
