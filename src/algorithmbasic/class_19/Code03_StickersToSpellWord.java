package algorithmbasic.class_19;

import java.util.HashMap;

/**
 * @Author sunzhongyi
 * @Date 2021/9/9
 * <p>
 * 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
 */
public class Code03_StickersToSpellWord {

    public static int minStickers(String[] stickers, String target) {

        int n = stickers.length;
        //记录输入的字符串数组中每个元素的个数；
        int[][] sticker = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                sticker[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();

        int min = process4(sticker, target, dp);
        return min == Integer.MAX_VALUE ? -1 : min;

    }

    private static int process4(int[][] sticker, String target, HashMap<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;

        char[] str = target.toCharArray();

        int[] tcounts = new int[26];

        for (char cha : str) {
            tcounts[cha - 'a']++;
        }

        int n = sticker.length;

        for (int i = 0; i < n; i++) {
            if (sticker[i][str[0]-'a'] > 0) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0;j<26;j++){
                    int k = tcounts[j] - sticker[i][j];
                    if (k>0){
                        while (k-->0){
                            sb.append((char)(j+'a'));
                        }
                    }
                }
                String rest =  sb.toString();
                min = Math.min(min,process4(sticker,rest,dp)) ;
            }
        }
        min += (min == Integer.MAX_VALUE)? 0:1;
        dp.put(target,min);
        return min;
    }


    public static int ways(String[] stickers, String target) {
        int way = process(stickers, target);
        return way == Integer.MAX_VALUE ? -1 : way;
    }

    // 所有贴纸stickers，每一种贴纸都有无穷张
    // target
    // 最少张数
    private static int process(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = method(first, target);
            //不相等代表的是以当前first处理时，可以减少目标字符串的长度，如果不变，那证明它不合适
            if (rest.length() != target.length()) {
                min = Math.min(min, process(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    /**
     * 返回剩余字符
     *
     * @param first
     * @param target
     * @return
     */
    private static String method(String first, String target) {
        char[] str1 = first.toCharArray();
        char[] str2 = target.toCharArray();
        int[] count = new int[26];
        for (char cha : str2) {
            count[cha - 'a']++;
        }
        for (char cha : str1) {
            count[cha - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public static int ways2(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] count = new int[N][26];
        for (int i = 0; i < N; i++) {
            final char[] chars = stickers[i].toCharArray();
            for (char cha : chars) {
                count[i][cha - 'a']++;
            }
        }
        int ans = process2(count, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int num = stickers.length;
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        for (int i = 0; i < num; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            sb.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);

    }


    public static int ways3(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] count = new int[N][26];
        for (int i = 0; i < N; i++) {
            final char[] chars = stickers[i].toCharArray();
            for (char cha : chars) {
                count[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        int ans = process3(count, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        if (t.length() == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] - 'a'] > 0) {
                StringBuffer sb = new StringBuffer();
                for (int j = 0; j < 26; j++) {
                    int num = tcounts[j] - sticker[j];
                    for (int k = 0; k < num; k++) {
                        sb.append((char) (j + 'a'));
                    }
                }
                String rest = sb.toString();
                ans = Math.min(ans, process3(stickers, rest, dp));
            }
        }
        ans += (ans == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }


    public static void main(String[] args) {

        String[] strings = {"heavy", "claim", "seven", "set", "had", "it", "dead", "jump", "design", "question", "sugar", "dress", "any", "special", "ground", "huge", "use", "busy", "prove", "there", "lone",
                "window", "trip", "also", "hot", "choose", "tie", "several", "be", "that", "corn", "after",
                "excite", "insect", "cat", "cook", "glad", "like", "wont", "gray", "especially", "level",
                "when", "cover", "ocean", "try", "clean", "property", "root", "wing"};
        String target = "travelbell";
//        final int ways = ways(strings, target);
//        System.out.println(ways);
//        System.out.println(ways2(strings, target));
        System.out.println(ways3(strings, target));
        System.out.println(minStickers(strings, target));
    }
}
