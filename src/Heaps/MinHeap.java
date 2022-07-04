package Heaps;

import java.util.*;

public class MinHeap {

    private final ArrayList<Integer> list;

    public MinHeap() {
        list = new ArrayList<>();
        list.add(null);
    }

    public boolean add(int val) {
        list.add(val);
        int i = list.size() - 1;
        while (i > 1) {
            if (list.get(i / 2) > list.get(i)) {
                int temp = list.get(i);
                list.set(i, list.get(i / 2));
                list.set(i / 2, temp);
                i = i / 2;
            } else {
                break;
            }
        }
        return true;
    }

    public int poll() {
        if (list.size() == 1) {
            throw new RuntimeException("Empty Heap Exception");
        }
        if (list.size() == 2) {
            int tr = list.get(1);
            clear();
            return tr;
        }
        int ret = list.get(1);
        list.set(1, list.remove(list.size() - 1));
        int i = 1;
        while (i < list.size()) {
            int left = 2 * i;
            int right = (2 * i) + 1;
            int maxIndex = i;
            if (left < list.size() && list.get(maxIndex) > list.get(left)) {
                maxIndex = left;
            }
            if (right < list.size() && list.get(maxIndex) > list.get(right)) {
                maxIndex = right;
            }
            if (i != maxIndex) {
                int t = list.get(maxIndex);
                list.set(maxIndex, list.get(i));
                list.set(i, t);
                i = maxIndex;
            } else {
                break;
            }
        }
        return ret;
    }

    public void clear() {
        list.clear();
        list.add(null);
    }

    public boolean offer(int val) {
        return add(val);
    }

    public int peek() {
        if (list.size() == 1) {
            throw new RuntimeException("Empty Heap Exception");
        }
        return list.get(1);
    }

    public int size() {
        return list.size() - 1;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        int i = 1;
        for (; i < list.size() - 1; i++) {
            builder.append(list.get(i) + ",");
        }
        if (list.size() == 1) {
            return builder.append("]").toString();
        }
        builder.append(list.get(i) + "]");
        return builder.toString();
    }
}
