package szy.algorithmbasic.class_07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 手写堆，实现增强功能
 *
 * @param <T>
 */
public class HeapGreater<T> {

    //存储堆中数据
    private ArrayList<T> heap;
    //记录每一条记录对应的索引位置
    private HashMap<T, Integer> indexMap;
    //数组大小
    private int heapSize;
    //比较器
    private Comparator<? super T> comp;

    public HeapGreater(Comparator<T> comp) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comp = comp;
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public T peek() {
        return heap.get(0);
    }

    public T pop() {
        T obj = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        heapify(0);
        return obj;
    }

    /**
     * 移除元素
     *
     * @param obj
     */
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj != replace) {
            indexMap.put(replace, index);
            heap.set(index, replace);
            resign(obj);
        }
    }

    /**
     * 返货全部元素
     *
     * @return
     */
    public List<T> getAllElems() {
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }


    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    //
    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) > 0 ? left + 1 : left;
            largest = comp.compare(heap.get(largest), heap.get(index)) <= 0 ? index : largest;
            if (largest == index) break;
            swap(index, largest);
            index = largest;
            left = index * 2 + 1;
        }

    }


    private void heapInsert(int index) {
        while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 两个位置数据交换，反向索引一并调整
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        T obj1 = heap.get(i);
        T obj2 = heap.get(j);
        heap.set(i, obj2);
        heap.set(j, obj1);
        indexMap.put(obj1, j);
        indexMap.put(obj2, i);
    }


    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public int size() {
        return indexMap.size();
    }

    public boolean isEmpty() {
        return indexMap.isEmpty();
    }
}
