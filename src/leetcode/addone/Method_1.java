package leetcode.addone;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sunzhongyi
 * @Date: 2019/7/17 16:45
 * @description：${description}
 */
public class Method_1 {

    public int[] plusOne(int[] digits) {
        int index = digits.length;
        for(int i = digits.length-1;i>=0;i--){
            if(digits[i]+1 == 10){
                digits[i] = 0;
                index = i;
                continue;
            }else{
                digits[i] = digits[i]+1;
                break;
            }
        }
        if(index == 0){
            int[] newDigits = new int[digits.length+1];
            newDigits[0] = 1;
            for(int i = 0;i<digits.length;i++){
                newDigits[i+1] = digits[i];
            }
            return newDigits;
        }else{
            return digits;
        }
    }

}
