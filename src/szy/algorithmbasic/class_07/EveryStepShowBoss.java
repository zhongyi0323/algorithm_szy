package szy.algorithmbasic.class_07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class EveryStepShowBoss {


    public static class Customer {
        int id;
        int buy;
        int enterTime;

        public Customer(int id, int buy, int time) {
            this.id = id;
            this.buy = buy;
            this.enterTime = time;
        }
    }

    //候选区排序规则(按照购买数量降序、按照购入时间升序)
    public static class CandidateComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
        }
    }

    //中奖区排序规则（按照购买数量、购入时间升序）
    public static class DaddyComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? o1.buy - o2.buy : o1.enterTime - o1.enterTime;
        }
    }

    public static class WhosYourDaddy {
        private HashMap<Integer, Customer> heapMap;
        private HeapGreater<Customer> candHeap;
        private HeapGreater<Customer> daddyHeap;
        private int limit;

        public WhosYourDaddy(int limit) {
            heapMap = new HashMap<>();
            candHeap = new HeapGreater<>(new CandidateComparator());
            daddyHeap = new HeapGreater<>(new DaddyComparator());
            this.limit = limit;

        }

        public void operate(int time, int id, boolean isBuy) {
            if (!heapMap.containsKey(id) && !isBuy) {
                return;
            }
            if (!heapMap.containsKey(id)) {
                heapMap.put(id, new Customer(id, 0, 0));
            }
            Customer c = heapMap.get(id);
            if (isBuy) {
                c.buy++;
            } else {
                c.buy--;
            }
            if (c.buy == 0) {
                heapMap.remove(c);
            }
            if (!candHeap.contains(c) && !daddyHeap.contains(c)) {
                if (daddyHeap.size() < limit) {
                    c.enterTime = time;
                    daddyHeap.push(c);
                } else {
                    c.enterTime = time;
                    candHeap.push(c);
                }
            } else if (candHeap.contains(c)) {
                if (c.buy == 0) {
                    candHeap.remove(c);
                } else {
                    candHeap.resign(c);
                }
            } else {
                if (c.buy == 0) {
                    daddyHeap.remove(c);
                } else {
                    daddyHeap.resign(c);
                }
            }
            move(time);
        }

        private void move(int time) {
            if (candHeap.isEmpty()) {
                return;
            }
            if (daddyHeap.size() < limit) {
                Customer c = candHeap.pop();
                c.enterTime = time;
                daddyHeap.push(c);
            } else {
                if (candHeap.peek().buy > candHeap.peek().buy) {
                    Customer c1 = candHeap.pop();
                    Customer c2 = daddyHeap.pop();
                    c1.enterTime = time;
                    c2.enterTime = time;
                    candHeap.push(c2);
                    daddyHeap.push(c1);
                }
            }
        }

        public List<Integer> getDaddies() {
            List<Customer> customers = daddyHeap.getAllElems();
            List<Integer> ans = new ArrayList<>();
            for (Customer c : customers) {
                ans.add(c.id);
            }
            return ans;
        }
    }

    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhosYourDaddy whoDaddies = new WhosYourDaddy(k);
        for (int i = 0; i < arr.length; i++) {
            whoDaddies.operate(i, arr[i], op[i]);
            ans.add(whoDaddies.getDaddies());
        }
        return ans;
    }


    /**
     * @param arr 产品编号
     * @param op  购买还是退货
     * @param k   有多少可以获奖
     * @return
     */
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> daddys = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean isBuy = op[i];
            //1。没有记录，第一次就是退回
            //2。没有记录，第一次购买
            //3。有记录，购买
            //4。有记录，退回

            //1.如果是退货、并且没有这个人的记录，直接返回上次的中奖结果
            if (!isBuy && !map.containsKey(id)) {
                ans.add(getCurAns(daddys));
                continue;//继续计算下一个
            }

            //2.没有记录
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            //3.此时不管是否有记录都已经记录完成，即可读取数据
            Customer curCus = map.get(id);
            //购买
            if (isBuy) {
                curCus.buy++;
            } else {//退回
                curCus.buy--;
            }
            if (curCus.buy == 0) {
                map.remove(id);
            }

            //判断在哪个区里面
            //1.如果没有在任何区内
            if (!cands.contains(curCus) && !daddys.contains(curCus)) {
                if (daddys.size() < k) {
                    curCus.enterTime = i;
                    daddys.add(curCus);
                } else {
                    curCus.enterTime = i;
                    cands.add(curCus);
                }
            }
            clearZeroBuy(cands);
            clearZeroBuy(daddys);
            cands.sort(new CandidateComparator());
            daddys.sort(new DaddyComparator());
            move(cands, daddys, k, i);
            ans.add(getCurAns(daddys));

        }
        return ans;
    }

    private static void move(ArrayList<Customer> cands, ArrayList<Customer> daddys, int k, int endTime) {
        if (cands.isEmpty()) {
            return;
        }
        if (daddys.size() < k) {
            Customer customer = cands.get(0);
            customer.enterTime = endTime;
            daddys.add(customer);
            cands.remove(customer);
        } else {
            Customer c1 = cands.get(0);
            Customer c2 = daddys.get(0);
            if (c1.buy > c2.buy) {
                c1.enterTime = endTime;
                c2.enterTime = endTime;
                daddys.remove(0);
                daddys.add(c1);
                cands.remove(0);
                cands.add(c2);
            }
        }

    }

    /**
     * 清楚为0的数据
     *
     * @param arr
     * @return
     */
    private static void clearZeroBuy(ArrayList<Customer> arr) {
        ArrayList<Customer> noZeros = new ArrayList<>();
        for (Customer c : arr) {
            if (c.buy != 0) {
                noZeros.add(c);
            }
        }
        arr.clear();
        for (Customer reC : arr) {
            arr.add(reC);
        }
    }

    /**
     * 返回中奖人id
     *
     * @param daddys
     * @return
     */
    private static List<Integer> getCurAns(ArrayList<Customer> daddys) {
        List<Integer> list = new ArrayList<>();
        for (Customer cus : daddys) {
            list.add(cus.id);
        }
        return list;
    }


}
