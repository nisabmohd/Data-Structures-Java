package Lists;

import java.util.*;

public class ArrayListCustom<T> {

    private final int DEFAULT_CAPACITY = 17;
    private int size = -1;
    private Object[] arr;

    public ArrayListCustom(int initialSize) {
        arr = new Object[initialSize];
    }

    public ArrayListCustom() {
        arr = new Object[DEFAULT_CAPACITY];
    }

    private void doubleArr() {
        Object[] temp = new Object[2 * arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    private void sizeplusone() {
        Object[] temp = new Object[1 + arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    public void add(T val) {
        if (arr.length == size + 1) {
            doubleArr();
        }
        arr[++size] = val;
    }

    public boolean addAll(Collection<? extends T> c) {
        c.forEach(elem -> {
            add(elem);
        });
        return true;
    }

//    private int in = 0;
//
//    public boolean addAll(int index, Collection<? extends T> c) {
//        in = index;
//        c.forEach(elem -> {
//            add(in++, elem);
//        });
//        return true;
//    }
//
//    public boolean removeAll() {
//        return true;
//    }
//
//    protected void removeRange(int fromIndex, int toIndex) {
//        for(int i=toIndex+1;i<arr.length;i++){
//            
//        }
//    }

    public int size() {
        return size;
    }

    public boolean conatains(T val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size() == -1;
    }

    public void add(int index, T val) {
        if (index == size) {
            add(val);
            return;
        }
        if (index > size) {
            doubleArr();
            for (int i = arr.length; i < index; i++) {
                add((T) new Object());
            }
            add(val);
            return;
        }
        sizeplusone();
        for (int j = arr.length - 2; j >= index; j--) {
            arr[j + 1] = arr[j];
        }
        arr[index] = val;
    }

    public T set(int index, T val) {
        arr[index] = val;
        return (T) arr[index];
    }

    public T get(int index) {
        return (T) arr[index];
    }

    public void clear() {
        size = -1;
    }

    @Override
    public String toString() {
        String ret = "[";
        int i = 0;
        for (; i < size; i++) {
            ret += arr[i] + ",";
        }
        if (size != -1) {
            ret += arr[i];
        }
        ret += "]";
        return ret;

    }

}
