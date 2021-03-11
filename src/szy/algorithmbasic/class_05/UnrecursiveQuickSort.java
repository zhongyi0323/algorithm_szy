package szy.algorithmbasic.class_05;

import java.util.Stack;

import static szy.algorithmbasic.class_05.PartitionAndQuickSort.swap;

/**
 * 非递归版快速排序
 */
public class UnrecursiveQuickSort {

    public static class Op {
        public int l;
        public int r;

        public Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        swap(arr, (int) (Math.random() * arr.length), arr.length - 1);
        int[] range = partition(arr, 0, arr.length - 1);
        Stack<Op> stack = new Stack();
        stack.push(new Op(0, range[0] - 1));
        stack.push(new Op(range[1] + 1, arr.length - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r + 1)), op.r);
                int[] partition = partition(arr, 0, arr.length - 1);
                stack.push(new Op(op.l, partition[0] - 1));
                stack.push(new Op(partition[1] + 1, op.r));
            }
        }
    }

    /**
     * 返回下一次计算的左右边界
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        }
        if (l == r) {
            return new int[]{l, r};
        }
        int lessIndex = l - 1;
        int upperIndex = r;
        int index = l;
        while (index < upperIndex) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] > arr[r]) {
                swap(arr, index, --upperIndex);
            } else {
                swap(arr, index++, ++lessIndex);
            }
        }
        swap(arr, upperIndex, r);
        return new int[]{lessIndex + 1, upperIndex};
    }
}
