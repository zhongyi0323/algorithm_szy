package algorithmbasic.class_01;


import algorithmbasic.util.MainUtil;

/**
 * 在有序数组中查询小于num的最右索引值
 */
public class Code06_BSNearRight {

    public static int getRightIndex(int[] arr, int num) {
        if (arr == null) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int index = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] < num) {
                index = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    public static int getRightIndex2(int[] arr, int num) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < num) {
                index = i;
            } else {
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxVal = 1000;
        int maxLength = 100;
        for (int i = 0;i<testTimes;i++){
            int[] arr = MainUtil.generatorRandomArray(maxVal, maxLength);
            MainUtil.compareArray(arr);
            int[] arr1 = MainUtil.copyArray(arr);
//            MainUtil.printArray(arr);
//            MainUtil.printArray(arr1);
            int i1 = getRightIndex(arr, 50);
            int i2 = getRightIndex2(arr1, 50);
           if (i1!=i2){
               System.out.println("fuck");
           }
        }

    }


}
