package algorithmbasic.sort;

import algorithmbasic.util.MainUtil;

/**
 * @Author sunzhongyi
 * @Date 2021/4/28
 */
public class Code01_selectSort {

    //选择排序
    public static void selectionSort(int[] arr) {
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < size; j++) {
                index = arr[j] < arr[index] ? j : index;
            }
            if (index != i) {
                MainUtil.swap(arr, i, index);
            }
        }
    }

}
