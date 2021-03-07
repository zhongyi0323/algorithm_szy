package szy.algorithmbasic.class_03;

import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class TwoStacksImplQueue {

    public static Stack<Integer> stackPush = new Stack<>();//入栈
    public static Stack<Integer> stackPop = new Stack<>();//出栈

    public static void push(Integer integer) {
        stackPush.push(integer);
    }

    public static Integer pop() {
        if (stackPop.isEmpty()) {
            if (stackPush.isEmpty()) {
                throw new RuntimeException("当前队列中五元素");
            } else {
                //将入栈数据倒入到出栈里
                pushToPop(stackPush, stackPop);
            }
        }
        return stackPop.pop();
    }

    private static void pushToPop(Stack stackPush, Stack stackPop) {
        while (!stackPush.isEmpty()) {
            stackPop.push(stackPush.pop());
        }
    }

    public static void main(String[] args) {
        TwoStacksImplQueue.push(1);
        TwoStacksImplQueue.push(2);
        TwoStacksImplQueue.push(3);
        TwoStacksImplQueue.push(4);
        System.out.println(TwoStacksImplQueue.pop());
        System.out.println(TwoStacksImplQueue.pop());
        System.out.println(TwoStacksImplQueue.pop());
        TwoStacksImplQueue.push(5);
        TwoStacksImplQueue.push(6);
        System.out.println(TwoStacksImplQueue.pop());
        System.out.println(TwoStacksImplQueue.pop());
        System.out.println(TwoStacksImplQueue.pop());
        System.out.println(TwoStacksImplQueue.pop());


    }


}
