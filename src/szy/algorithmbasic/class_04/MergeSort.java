package szy.algorithmbasic.class_04;

public class MergeSort {

    //递归实现归并排序
    public static void megerSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int l = 0;
        int r = arr.length - 1;
        process(arr, l, r);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        //创建辅助数组
        int[] help = new int[r - l + 1];
        int i = 0;//辅助数组索引
        int p1 = l;//左边第一个值
        int p2 = mid + 1;//右边第一个值
        //每一个值进行比较，知道达到左边或右边的最大长度
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        //如果左边没有走到终点，代表着后续的数字都比右边的大，需要赋值到辅助索引中
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        //右侧同理
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; ) {
            arr[l + i] = arr[i++];
        }
    }

}
