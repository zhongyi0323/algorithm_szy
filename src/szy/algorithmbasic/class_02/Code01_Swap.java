package szy.algorithmbasic.class_02;

public class Code01_Swap {

    /**
     *数组中两个位置的数进行交换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
