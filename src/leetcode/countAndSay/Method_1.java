package leetcode.countAndSay;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sunzhongyi
 * @Date: 2019/7/16 15:39
 */

public class Method_1 {

    public static void main(String[] args) {
        Method_1 method_1 = new Method_1();
        String str = method_1.countAndSay(4);
        System.out.println(str);
    }

    public String countAndSay(int n) {

        if (n == 1) return "1";
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        for (int i = 2; i <= n; i++) {
            String str = map.get(i - 1);
            char[] ch = str.toCharArray();
            int index = 0;
            char num = ch[0];
            String newStr = "";
            for (int j = 0; j < ch.length; j++) {
                if (num == ch[j]) {
                    index++;
                } else {
                    newStr += index + String.valueOf(ch[j-1]);
                    index = 1;
                    num = ch[j];
                }
            }
            newStr += index+ String.valueOf(ch[ch.length-1]);
            map.put(i, newStr);
        }
        return map.get(n);
    }
}
