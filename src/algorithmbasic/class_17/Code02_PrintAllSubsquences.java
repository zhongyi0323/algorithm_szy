package algorithmbasic.class_17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunzhongyi
 * @Date 2021/9/8
 *
 * 返回当前字符串的全部子序列
 */
public class Code02_PrintAllSubsquences {


    public static List<String> subs(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        String path = "";
        f(str, 0, ans, path);
        return ans;
    }

    private static void f(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
        } else {
            f(str, index + 1, ans, path);
            f(str, index + 1, ans, path + String.valueOf(str[index]));
        }

    }

    public static void main(String[] args) {
        final List<String> list = Code02_PrintAllSubsquences.subs("abc");
        list.forEach(s -> {
            System.out.println(s);
        });

    }
}
