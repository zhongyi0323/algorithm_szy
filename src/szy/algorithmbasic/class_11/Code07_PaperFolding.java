package szy.algorithmbasic.class_11;

/**
 * @Author sunzhongyi
 * @Date 2021/3/26
 *
 * 折纸问题
 */
public class Code07_PaperFolding {

    //折n次后对应的输出结果
    public static void printAllFolds(int N){
        process(1,N,true);
    }
    private static void process(int i, int n, boolean b) {
        if (i<=n){
            process(i+1,n,true);
            System.out.print(b == true?"凹 ":"凸 ");
            process(i+1,n,false);
        }
    }

    public static void main(String[] args) {
        printAllFolds(3);
    }
}
