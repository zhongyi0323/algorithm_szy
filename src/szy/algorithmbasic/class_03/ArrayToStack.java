package szy.algorithmbasic.class_03;

import java.util.ArrayList;

/**
 *
 */
public class ArrayToStack {

    public static class MyQueue {
        private static int[] arr;
        private static int pushi;
        private static int polli;
        private static int size;
        private static int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int val) {
            if (size == limit) {
                System.out.println("队列已满，不能再加了！");
                return ;
            }
            arr[pushi] = val;
            size++;
            pushi++;
            pushi = changeIndex(pushi);

        }

        public int pop() {
            int val = 0;
            if (size == 0) {
                System.out.println("队列为空，不能读数据！");
                return -1;
            }
            val = arr[polli];
            size--;
            polli++;
            polli = changeIndex(polli);
            return val;
        }

        /**
         * 栈出
         * @return
         */
        public int popStack(){
            int val = 0;
            if (size == 0){
                System.out.println("栈为空，不能读数据！");
            }
            val = arr[size-1];
            size--;
            return val;
        }

        public static int changeIndex(int index) {
            if (index == limit) {
                return 0;
            } else {
                return index;
            }
        }

        public static void main(String[] args) {
        }


    }

}
