package leetcode.lengthofLastWord;

/**
 * @Author: sunzhongyi
 * @Date: 2019/7/16 20:39
 * @description：${description}
 */
public class Method_1 {

    public int lengthOfLastWord(String s) {

        String[] array = s.split(" ");
        if(array.length == 0){
            return 0;
        }
        return array[array.length-1].length();
    }
}
