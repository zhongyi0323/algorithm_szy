package szy.algorithmbasic.class_06;

import sun.applet.Main;
import szy.algorithmbasic.util.MainUtil;

public class Heap {

    /**
     * 实现最大堆操作
     */
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public void add(int num) {
            if (heapSize == limit) {
                throw new RuntimeException("当前堆已满");
            }
            heap[heapSize] = num;
            //做最大堆的维护
            heapInsert(heap, heapSize++);
        }

        public void heapInsert(int[] arr, int index) {
            while (arr[(index - 1) / 2] < arr[index]) {
                MainUtil.swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public int pop() {
            int ans = heap[0];
            MainUtil.swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }

        public void heapify(int[] arr, int i, int heapSize) {
            int leftIndex = i * 2 + 1;
            //找出子数中较大的索引值
            while (leftIndex < heapSize) {
                //找出子树中较大值的索引 和 i位置进行比较
                int maxIndex = (leftIndex + 1 == heapSize || arr[leftIndex] >= arr[leftIndex + 1]) ? leftIndex : leftIndex + 1;
                maxIndex = arr[i] >= arr[maxIndex] ? i : maxIndex;
                //如果当前i位置的值就是最大值，直接返回
                if (maxIndex == i) return;
                //交换位置，继续计算
                MainUtil.swap(arr, i, maxIndex);
                i = maxIndex;
                leftIndex = maxIndex * 2 + 1;
            }
        }

        public void heapInsert2(int[] arr, int index) {
            while (arr[index] > arr[index / 2 - 1]) {
                MainUtil.swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void heapify11(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            MainUtil.swap(arr, index, --heapSize);
            while (left < heapSize) {
                int largeI = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largeI = arr[largeI] > arr[index] ? largeI : index;
                if (largeI == index) break;
                MainUtil.swap(arr, index, largeI);
                index = largeI;
                left = largeI * 2 + 1;
            }
        }

    }


}
