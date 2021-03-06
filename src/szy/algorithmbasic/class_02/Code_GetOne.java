package szy.algorithmbasic.class_02;

public class Code_GetOne {

    /**
     * 在一个数组中，只有一个数出现了奇数次，输出该数
     * @param arr
     */
    public static int getOne(int[] arr){
        int eor = 0;
        for (int i = 0;i<arr.length;i++){
            eor^=arr[i];
        }
        return eor;
    }


    public static void main(String[] args) {
        int[] arr = {1,1,2,3,3,};
        System.out.println(getOne(arr));
    }
}
