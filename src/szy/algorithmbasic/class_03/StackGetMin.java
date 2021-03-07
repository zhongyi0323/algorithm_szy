package szy.algorithmbasic.class_03;

import java.util.Stack;

public class StackGetMin {


    public static Stack<Integer> stackData = new Stack<>();
    public static Stack<Integer> stackMin = new Stack<>();

    public void push(Integer val) {
        stackData.push(val);
        if (!stackMin.isEmpty()) {
            stackMin.push(stackMin.peek() > val ? val : stackMin.peek());
        } else {
            stackMin.push(val);
        }
    }

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
    public Integer getMinVal(){
        if(stackMin.isEmpty()){
            throw new RuntimeException("当前栈中无元素");
        }else{
            return stackMin.peek();
        }
    }

}
