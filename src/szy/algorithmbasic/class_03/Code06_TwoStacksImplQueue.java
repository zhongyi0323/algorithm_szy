package szy.algorithmbasic.class_03;

import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class Code06_TwoStacksImplQueue {

    public static Stack<Integer> stackPush = new Stack<>();//入栈
    public static Stack<Integer> stackPop = new Stack<>();//出栈

    /**
     * 将数据写入入栈
     * 同步倒入出栈
     *
     * @param integer
     */
    public static void push(Integer integer) {
        stackPush.push(integer);
        pushToPop(stackPush, stackPop);
    }

    /**
     * 直接从出栈输出
     *
     * @return
     */
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

    /**
     * 将入栈数据倒入出栈中
     *
     * @param stackPush
     * @param stackPop
     */
    private static void pushToPop(Stack stackPush, Stack stackPop) {
        while (!stackPush.isEmpty()) {
            stackPop.push(stackPush.pop());
        }
    }

    public static void main(String[] args) {
        Code06_TwoStacksImplQueue.push(1);
        Code06_TwoStacksImplQueue.push(2);
        Code06_TwoStacksImplQueue.push(3);
        Code06_TwoStacksImplQueue.push(4);
        System.out.println(Code06_TwoStacksImplQueue.pop());
        System.out.println(Code06_TwoStacksImplQueue.pop());
        System.out.println(Code06_TwoStacksImplQueue.pop());
        Code06_TwoStacksImplQueue.push(5);
        Code06_TwoStacksImplQueue.push(6);
        System.out.println(Code06_TwoStacksImplQueue.pop());
        System.out.println(Code06_TwoStacksImplQueue.pop());
        System.out.println(Code06_TwoStacksImplQueue.pop());
        System.out.println(Code06_TwoStacksImplQueue.pop());


    }


}
