package algorithmbasic.class_17;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sunzhongyi
 * @Date 2021/9/8
 *
 * 打印一个字符串的全部排列
 */
public class Code03_PrintAllPermutations {

    public static List<String> permus(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0){
            return ans;
        }
        String path = "";
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<Character>();
        for (char c :str){
            rest.add(c);
        }
        f(rest,path,ans);
        return ans;
    }

    /**
     * 思想：挨个让char数组中的元素作为第一个元素，移除当前元素，赋值到path中 对剩下的元素做递归，
     * 最后要还原现场，保证第二个拿到的还是完整的数组
     *
     * @param rest 当前char数组中剩余的字符
     * @param path  为当前保存的内容
     * @param ans  最终结果
     */
    private static void f(ArrayList<Character> rest, String path, List<String> ans) {
        if (rest == null || rest.size() == 0){
            ans.add(path);
            return;
        }
        for (int i = 0;i<rest.size();i++){
            final Character c = rest.get(i);
            rest.remove(i);
            f(rest,path+String.valueOf(c),ans);
            rest.add(i,c);
        }

    }

    public static List<String> permus2(String s){
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0){
            return ans;
        }
        char[] str = s.toCharArray();
        f2(str,0,ans);
        return ans;
    }


    private static void f2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, i, index);
                f2(str, index + 1, ans);
                swap(str, i, index);
            }
        }
    }

    private static void swap(char[] str, int i, int index) {
        char next =  str[i];
        str[i] = str[index];
        str[index] = next;

    }


    public static void main(String[] args) {
        Code03_PrintAllPermutations.permus("abc").forEach(s -> {
            System.out.println(s);
        });
        System.out.println("===========");
        Code03_PrintAllPermutations.permus2("abc").forEach(s -> {
            System.out.println(s);
        });
    }
}
