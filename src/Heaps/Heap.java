package Heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

public class Heap<T extends Comparable<T>> implements Iterable<T> {

    private ArrayList<T> list = new ArrayList<>();
    private int index;

    private int size = 0;

    // Default as maxheap
    public Heap() {
        list.add(null);
        index = 0;
    }

    // pass Comparator for min Heap
    // pass  Comparator.reverseOrder() for min heap
    public Heap(Comparator<T> comparator) {
        this.comparator = comparator;
        list.add(null);
        index = 0;
    }

    public boolean add(T val) {
        return offer(val);
    }

    private Comparator<T> comparator = (o1, o2) -> {
        return o1.compareTo(o2);
    };

    public boolean offer(T val) {
        index++;
        size++;
        list.add(index, val);
        int i = index;
        while (i > 1) {
            int parentIndex = i / 2;
            if (comparator.compare(list.get(i), list.get(parentIndex)) > 0) {
                T t = list.get(i);
                list.set(i, list.get(parentIndex));
                list.set(parentIndex, t);
                i = parentIndex;
            } else {
                break;
            }
        }
        return true;
    }

    public T poll() {
        if (index == 0) {
            throw new RuntimeException("Empty Heap");
        }
        T deleted = list.get(1);
        T t = list.get(index);
        list.set(1, t);
        int i = 1;
        while (i < index - 1) {
            int maxIndex = i, left = 2 * i, right = (2 * i) + 1;
            if (left < index && comparator.compare(list.get(maxIndex), list.get(left)) < 0)
                maxIndex = left;
            if (right < index && comparator.compare(list.get(maxIndex), list.get(right)) < 0)
                maxIndex = right;
            if (maxIndex != i) {
                t = list.get(i);
                list.set(i, list.get(maxIndex));
                list.set(maxIndex, t);
                i = maxIndex;
            } else break;
        }
        index--;
        size--;
        return deleted;
    }

    public T peek() {
        if (index == 0) {
            throw new RuntimeException("Empty Heap");
        }
        return list.get(1);
    }

    public int size() {
        return size;
    }

    public void clear() {
        list.clear();
        index = 0;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size()];
        for (int i = 1; i < size(); i++) {
            arr[i - 1] = list.get(i);
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder("[");
        if (list.size() == 1) return ans.append("]").toString();
        int i;
        for (i = 1; i < index; i++) {
            ans.append(list.get(i) + ", ");
        }
        ans.append(list.get(i));
        return ans.append("]").toString();
    }

    @Override
    public void forEach(Consumer<? super T> c) {
        for (int i = 1; i < size(); i++) {
            c.accept(list.get(i));
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HeapItr();
    }

    private class HeapItr implements Iterator<T> {
        int cursor = 1;

        @Override
        public boolean hasNext() {
            return cursor < Heap.this.size();
        }

        @Override
        public T next() {
            return Heap.this.list.get(cursor++);
        }
    }
}