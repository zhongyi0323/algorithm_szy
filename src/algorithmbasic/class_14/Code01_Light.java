package algorithmbasic.class_14;

/**
 * @Author sunzhongyi
 * @Date 2021/3/30
 *
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code01_Light {


    public static int getLights(String str){
        if (str == null || str.isEmpty()){
            return 0;
        }
        char[] chars = str.toCharArray();
        int nums = 0;
        int i = 0;
        while (i< chars.length){
            if (chars[i] == 'X'){//当前位置如果为X不能放灯，直接去下一个位置看
                i++;
            }else{//如果当前节点是。
                nums++;//一定需要加一盏灯，不管后续情况如何
                if (i + 1 == chars.length) {//如果当前节点是最后一个节点直接返回
                    break;
                } else { //如果不是看i+1位置是啥
                    if (chars[i + 1] == 'X') {//如果是X则直接看i+2位置从新走逻辑
                        i = i + 2;
                    } else {//如果i+1位置也是点即 i位置是。 i+1位置是。那么i+2不管是X还是。都只需要1盏灯，下次看的时候可以直接看i+3的位置
                        i = i + 3;
                    }
                }
            }
        }
        return nums;
    }

}
