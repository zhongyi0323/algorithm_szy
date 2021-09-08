package algorithmbasic.class_17;

import java.util.Stack;

/**
 * @Author sunzhongyi
 * @Date 2021/9/8
 *
 * 给你一个栈，请你逆序这个栈
 */
public class Code04_ReverseStackUsingRecursive {



    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return ;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);

    }

    /**
     * 返回栈底元素，其他位置不变
     * @param stack
     * @return
     */
    private static int f(Stack<Integer> stack) {
        int cur = stack.pop();
        if (stack.isEmpty()){
            return cur;
        }
        int last = f(stack);
        stack.push(cur);
        return last;
    }

    public static void main(String[] args) {
        Stack<Integer> stack =  new Stack<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        Code04_ReverseStackUsingRecursive.reverse(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
