package algorithmbasic.sort;

import algorithmbasic.util.MainUtil;

import java.util.Stack;

/**
 * @Author sunzhongyi
 * @Date 2021/4/28
 */
public class Code05_QuickSort {

    public static class Op {
        private int l;
        private int r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    //非递归
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int l = 0;
        int r = arr.length - 1;
        MainUtil.swap2(arr, (int) (Math.random() * (r+1)), r);
        int[] range = patition(arr, l, r);
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(l, range[0] - 1));
        stack.push(new Op(range[1] + 1, r));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                MainUtil.swap2(arr, op.l + (int) (Math.random() * (op.r-op.l+1)), op.r);
                int[] range2 = patition(arr, op.l, op.r);
                stack.push(new Op(op.l, range2[0] - 1));
                stack.push(new Op(range2[1] + 1, op.r));
            }
        }
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) return;
        MainUtil.swap2(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] range = patition(arr, l, r);
        process(arr, l, range[0] - 1);
        process(arr, range[1] + 1, r);
    }

    private static int[] patition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] < arr[r]) {
                MainUtil.swap2(arr, ++less, index++);
            } else if (arr[index] == arr[r]) {
                index++;
            } else {
                MainUtil.swap2(arr, index, --more);
            }
        }


        MainUtil.swap2(arr, more, r);
        return new int[]{less + 1, more};

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = MainUtil.generatorRandomArray(100, 10);
            int[] arr1 = MainUtil.copyArray(arr);
            quickSort2(arr1);
            MainUtil.compareArray(arr);
            if (!MainUtil.compareTo(arr, arr1)) {
                MainUtil.printArray(arr);
                MainUtil.printArray(arr1);
                System.out.println("fuck");
            }
        }
    }

}
