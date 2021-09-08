package algorithmbasic.class_17;



/**
 * @Author sunzhongyi
 * @Date 2021/9/8
 *
 *  汉诺塔问题
 */
public class Code01_Hanoi {

    public static void hanoi1(int n){
        leftToRight(n);
    }

    /**
     * 递归函数 处理，通过增加参数来增加可能性
     * @param n
     * @param from
     * @param to
     * @param other
     */
    public static void hanoi2(int n, String from ,String to ,String other){
        if (n == 1){
            System.out.println("move 1  from "+from +" to "+to);
        }else{
            hanoi2(n-1,from,other,to);
            System.out.println("move "+ n + " from "+ from +" to "+to);
            hanoi2(n-1,other,to,from);
        }
    }


    /**
     * basecase 如果n == 1 直接移动到右边 结束；
     * 1.首先将n-1个位置的元素移动到中间
     * 2。将n位置的元素移动到右边
     * 3。再将n-1位置的元素从中间移动到右边
     * @param n
     */
    public static void leftToRight(int n){
        if (n == 1){
            System.out.println("move 1 from left to right !");
            return ;
        }
        leftToMid(n-1);
        System.out.println("move "+n+" from left to right !");
        midToRight(n-1);

    }

    private static void midToRight(int n) {
        if (n == 1){
            System.out.println("move 1 from mid to right !");
            return ;
        }
        midToLeft(n-1);
        System.out.println("move "+n+" from mid to right !");
        leftToRight(n-1);
    }

    private static void midToLeft(int n) {
        if (n == 1){
            System.out.println("move 1 from mid to left !");
            return ;
        }
        midToRight(n-1);
        System.out.println("move "+n+" from mid to right !");
        rightToLeft(n-1);

    }

    private static void rightToLeft(int n) {
        if (n == 1){
            System.out.println("move 1 from right to left !");
            return ;
        }
        rightToMid(n-1);
        System.out.println("move "+n+" from right to left !");
        midToLeft(n-1);
    }

    private static void rightToMid(int n) {
        if (n == 1){
            System.out.println("move 1 from right to Mid !");
            return ;
        }
        rightToLeft(n-1);
        System.out.println("move "+n+" from right to left !");
        leftToMid(n-1);
    }

    private static void leftToMid(int n) {
        if (n == 1){
            System.out.println("move 1 from left to Mid !");
            return ;
        }
        leftToRight(n-1);
        System.out.println("move "+n+" from left to mid !");
        rightToMid(n-1);
    }

    public static void main(String[] args) {
        Code01_Hanoi.hanoi1(3);
        System.out.println("----");
        Code01_Hanoi.hanoi2(3,"left","right","mid");
    }

}
