package algorithmbasic.class_20;

import java.util.Comparator;
import java.util.PriorityQueue;

import static algorithmzuo.zuo_class20.Code03_Coffee.printArray;
import static algorithmzuo.zuo_class20.Code03_Coffee.randomArray;
import static algorithmzuo.zuo_class20.Code03_Coffee.right;

/**
 * @Author sunzhongyi
 * @Date 2021/9/14
 */
public class Code03_Coffee {

    static class Mechine {
        int timePoint;//可以使用的时间点
        int workTime;//制作一个咖啡所需的时间

        public Mechine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    static class MechineCompare implements Comparator<Mechine> {
        @Override
        public int compare(Mechine o1, Mechine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    public static int cleanTime(int[] arr, int n, int a, int b) {
        PriorityQueue<Mechine> heap = new PriorityQueue<>(new MechineCompare());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Mechine(0, arr[i]));
        }
        int[] drinks = new int[n];
        //取出小根堆的堆顶，当前timePoint为使用改咖啡机的时间，
        //下一个人使用这个咖啡机制作咖啡的时间为 timePoint + workTime
        for (int i = 0; i < n; i++) {
            Mechine mechine = heap.poll();
            mechine.timePoint += mechine.workTime;
            drinks[i] = mechine.timePoint;
            heap.add(mechine);
        }
        return forceWash(drinks, a, b, 0, 0);
    }

    public static int bestTimeDp(int[] arr, int n, int a, int b) {
        PriorityQueue<Mechine> heap = new PriorityQueue<>(new MechineCompare());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Mechine(0, arr[i]));
        }
        int[] drinks = new int[n];
        //取出小根堆的堆顶，当前timePoint为使用改咖啡机的时间，
        //下一个人使用这个咖啡机制作咖啡的时间为 timePoint + workTime
        for (int i = 0; i < n; i++) {
            Mechine mechine = heap.poll();
            mechine.timePoint += mechine.workTime;
            drinks[i] = mechine.timePoint;
            heap.add(mechine);
        }
        int maxFree = 0;
        for (int i = 0; i < n; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + a;
        }
        int[][] dp = new int[n + 1][maxFree + 1];
        //dp[N][*] 都为0；
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest < maxFree + 1; rest++) {
                int selfWash = Math.max(drinks[index], rest) + a;
                if (selfWash > maxFree) {
                    continue;
                }
                int restWash = dp[index + 1][selfWash];
                int p1 = Math.max(selfWash, restWash);
                int selfWash2 = drinks[index] + b;
                int restWash2 = dp[index + 1][rest];
                int p2 = Math.max(selfWash2, restWash2);
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }


    // drinks 所有杯子可以洗的时间
    // a 洗杯子的时间
    // b 挥发的时间
    //
    public static int forceWash(int[] drinks, int a, int b, int index, int time) {
        if (index == drinks.length) {
            return 0;
        }
        //index 位置决定洗
        int selfWash = Math.max(drinks[index], time) + a;
        int restWash = forceWash(drinks, a, b, index + 1, selfWash);
        int p1 = Math.max(selfWash, restWash);
        //index 位置决定挥发
        int selfWash2 = drinks[index] + b;
        int restWash2 = forceWash(drinks, a, b, index + 1, time);
        int p2 = Math.max(selfWash2, restWash2);
        return Math.min(p1, p2);
    }

    public static int bestTimeDp(int[] drinks, int wash, int air) {
        int n = drinks.length;
        int maxFree = 0;
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(drinks[i], maxFree) + wash;
        }
        int[][] dp = new int[n + 1][maxFree + 1];
        //dp[n][0.....maxFree] 都为0；
        for (int index = n - 1; index >= 0; index--) {
            for (int free = 0; free < maxFree + 1; free++) {
                int selfWash = Math.max(drinks[index], free) + wash;
                if (selfWash > maxFree) {
                    continue;
                }
                int restWash = dp[index + 1][selfWash];
                int p1 = Math.max(selfWash, restWash);

                //index 位置决定挥发
                int selfWash2 = drinks[index] + air;
                int restWash2 = dp[index + 1][free];
                int p2 = Math.max(selfWash2, restWash2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];

    }


    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = bestTimeDp(arr, n, a, b);
            int ans2 = cleanTime(arr, n, a, b);
//            int ans2 = minTime1(arr, n, a, b);
//            int ans3 = minTime2(arr, n, a, b);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
