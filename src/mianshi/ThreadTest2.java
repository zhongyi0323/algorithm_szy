package mianshi;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author sunzhongyi
 * @Date 2021/8/25
 */
public class ThreadTest2 implements Callable {

    static ReentrantLock lock = new ReentrantLock();
    @Override
    public Object call() throws Exception {
        Thread.sleep(100000);
        return "null";
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            System.out.println(111);
            lock.lock();
            System.out.println("---------");
        },"Thread1");
        t1.start();
        boolean b = lock.tryLock(1000,TimeUnit.MILLISECONDS);
        if (b ){
            System.out.println("获取锁chenggo");
        }else{
            System.out.println("获取锁失败");
        }

        Thread t2 = new Thread(()->{
            System.out.println(222);
        });
        t2.start();
    }

}

