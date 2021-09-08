package mianshi;

/**
 * @Author sunzhongyi
 * @Date 2021/8/25
 */
public class ThreadTest implements Runnable{


    @Override
    public void run() {
        System.out.println("---------");
    }

    public static void main(String[] args) {
         ThreadTest threadTest = new ThreadTest();
         new Thread(threadTest).start();
    }
}
