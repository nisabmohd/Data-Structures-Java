package Lists;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArrayListCustom<T> implements Cloneable, Iterable<T> {

    private final int DEFAULT_CAPACITY = 7;
    private int size = 0;
    private Object[] arr;

    public ArrayListCustom(int initialSize) {
        arr = new Object[initialSize];
    }

    public ArrayListCustom() {
        arr = new Object[DEFAULT_CAPACITY];
    }

    private void doubleArr() {
        Object[] temp = new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    public boolean add(T val) {
        if (arr.length == size) {
            doubleArr();
        }
        arr[size++] = (T) val;
        return ((T) arr[size - 1]).equals(val);
    }

    public void add(int index, T val) {
        if (index >= size) {
            for (int i = 0; i < index - size - 1; i++) {
                this.add((T) new Object());
            }
            this.add(val);
        } else {
            doubleArr();
            for (int i = size - 1; i >= index; i--) {
                arr[i + 1] = arr[i];
            }
            arr[index] = val;
            size++;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean addAll(Collection<? extends T> c) {
        int prevSize = size();
        c.forEach(item -> this.add(item));
        return (size() - prevSize) == c.size();
    }

    public T get(int index) {
        if (index >= size) {
            throw new RuntimeException("No Such Element Exception");
        }
        return (T) arr[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T remove(int index) {
        if (index >= size) {
            throw new RuntimeException("Index Out Of Bounds Exception");
        }
        T rem = (T) arr[index];
        for (int i = index + 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        size -= 1;
        return rem;
    }

    public boolean contains(T val) {
        for (int i = 0; i < size; i++) {
            if (((T) arr[i]).equals(val)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (isEmpty()) {
            return builder.append("]").toString();
        }
        int i = 0;
        for (; i < size - 1; i++) {
            builder.append((T) arr[i]);
            builder.append(", ");
        }
        builder.append((T) arr[i]);
        return builder.append("]").toString();
    }

    // forEach method takes Consumer<T>
    public void forEach(Consumer<? super T> o) {
        Objects.requireNonNull(o);
        for (int i = 0; i < size; i++) {
            o.accept((T) arr[i]);
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayListItr<>();
    }

    private class ArrayListItr<T> implements Iterator<T> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < ArrayListCustom.this.size;
        }

        @Override
        public T next() {
            return (T) ArrayListCustom.this.arr[cursor++];
        }
    }

    public Object[] toArray() {
        Object[] ret = new Object[size];
        for (int i = 0; i < size; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }


    // Stream API methods default support in this Arraylist
    public ArrayListCustom<T> filter(Predicate<T> o) {
        Objects.requireNonNull((o));
        ArrayListCustom<T> newList = new ArrayListCustom<>();
        this.forEach(item -> {
            if (o.test(item)) newList.add(item);
        });
        return newList;
    }

    public <R> ArrayListCustom<R> map(Function<T, R> o) {
        Objects.requireNonNull((o));
        ArrayListCustom<R> newList = new ArrayListCustom<>();
        this.forEach(item -> {
            newList.add(o.apply(item));
        });
        return newList;
    }

    public T findFirst(Predicate<T> o) {
        Objects.requireNonNull(o);
        for (Object item : this.toArray()) {
            T t = (T) item;
            if (o.test(t)) return t;
        }
        return null;
    }

    public boolean allMatch(Predicate<T> o) {
        Objects.requireNonNull(o);
        for (Object item : this.toArray()) {
            T t = (T) item;
            if (!o.test(t)) return false;
        }
        return true;
    }

    public boolean anyMatch(Predicate<T> o) {
        Objects.requireNonNull(o);
        for (Object item : this.toArray()) {
            T t = (T) item;
            if (o.test(t)) return true;
        }
        return false;
    }

    public int findFirstIndex(Predicate<T> o) {
        Objects.requireNonNull(o);
        int i = 0;
        for (Object item : this.toArray()) {
            T t = (T) item;
            if (o.test(t)) return i;
            i++;
        }
        return -1;
    }

    public int count() {
        return size();
    }

    public T max(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        if (arr.length == 0) return null;
        T maxValue = (T) arr[0];
        for (Object val : toArray()) {
            T item = (T) val;
            int compareValue = comparator.compare(item, maxValue);
            if (compareValue > 0) maxValue = item;
        }
        return maxValue;
    }

    public T min(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        if (arr.length == 0) return null;
        T minValue = (T) arr[0];
        for (Object val : toArray()) {
            T item = (T) val;
            int compareValue = comparator.compare(item, minValue);
            if (compareValue < 0) minValue = item;
        }
        return minValue;
    }

    public boolean noneMatch(Predicate<T> p) {
        Objects.requireNonNull(p);
        for (Object val : toArray()) {
            T item = (T) val;
            if (p.test(item)) return false;
        }
        return true;
    }

}
