package szy.algorithmbasic.class_03;

import java.util.Stack;

/**
 * 用栈实现获取当前栈中最小元素方法
 */
public class Code04_StackGetMin {

    //数据栈
    public static Stack<Integer> stackData = new Stack<>();
    //最小值栈
    public static Stack<Integer> stackMin = new Stack<>();


    /**
     * 每次插入时会向最小栈栈中插入当前栈中最小的值
     *
     * @param val
     */
    public void push(Integer val) {
        stackData.push(val);
        if (!stackMin.isEmpty()) {
            stackMin.push(stackMin.peek() > val ? val : stackMin.peek());
        } else {
            stackMin.push(val);
        }
    }

    /**
     * 出栈时最小栈每次都出，保证两个栈长度相同
     *
     * @return
     */
    public Integer pop() {
        Integer val = null;
        if (!stackData.isEmpty()) {
            stackMin.pop();
            val = stackData.pop();
        } else {
            throw new RuntimeException("当前栈中无元素");
        }
        return val;
    }

    /**
     * 栈顶为最小值
     *
     * @return
     */
    public Integer getMinVal() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("当前栈中无元素");
        } else {
            return stackMin.peek();
        }
    }

}
