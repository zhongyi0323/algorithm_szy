package algorithmbasic.util;

import java.util.Arrays;

public class MainUtil {


    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int val){
            this.value = val;
        }
    }

    public static int[] generatorRandomArray(int maxValue, int maxLength) {
        //创建最大长度为maxLength的随机数组[0,maxLength+1)
        int length = (int) (Math.random() * (maxLength)) + 1;
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            //随机生成-N~N的数组值
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static int[] copyArray(int[] arr){
        int[] arr1 = new int[arr.length];
        for (int i = 0;i<arr.length;i++){
            arr1[i] = arr[i];
        }
        return arr1;
    }

    public static void printArray(int[] arr){
        for (int i = 0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void compareArray(int[] arr){
        Arrays.sort(arr);
    }


    public static boolean compareTo(int[] arr,int[] arr1){
        if(arr == null) return arr1==null;
        if(arr1 == null) return arr==null;
        boolean  bool = arr.length == arr1.length;
        if (bool){
            for (int i = 0;i<arr.length;i++){
                if(arr[i]!= arr1[i]) return false;
            }
        }
        return bool;

    }

    /**
     * 两个位置的数进行交换，值相等的情况下禁止调用
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 两个位置的数进行交换，值相等的情况下禁止调用
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap2(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
