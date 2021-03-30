package algorithmbasic.class_05;

import java.util.Stack;

import static algorithmbasic.class_05.Code02_PartitionAndQuickSort.swap;

/**
 * 非递归版快速排序
 * 利用栈实现，+ partation
 */
public class Code03_UnrecursiveQuickSort {

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

    public static void quickSort2(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        swap(arr, (int) Math.random() * arr.length, arr.length - 1);
        int[] rang = partition1(arr, 0, arr.length - 1);
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, rang[0] - 1));
        stack.push(new Op(rang[1] + 1, arr.length - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop();
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r + 1)), op.r);
                int[] partition = partition(arr, op.l, op.r);
                stack.push(new Op(op.l, partition[0] - 1));
                stack.push(new Op(partition[1] + 1, op.r));
            }
        }

    }

    private static int[] partition1(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int lessIndex = l - 1;
        int moreIndex = r;
        int index = l;
        while (index < moreIndex) {
            if (arr[index] < arr[r]) {
                swap(arr, index++, ++lessIndex);
            } else if (arr[index] == arr[r]) {
                index++;
            } else {
                swap(arr, index, --moreIndex);
            }
        }
        swap(arr, moreIndex, r);
        return new int[]{++lessIndex, moreIndex};

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
