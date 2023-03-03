package Sets;

import java.util.*;
import java.util.function.Consumer;

import Maps.HashMapCustom;

public class HashSetCustom<E> implements Iterable<E> {

    public HashSetCustom() {
    }

    private HashMapCustom<E, Object> map = new HashMapCustom<>();
    private final Object obj = new Object();

    public boolean add(E val) {
        return map.put(val, obj);
    }

    public void clear() {
        map.clear();
    }

    public boolean contains(E val) {
        return map.containsKey(val);
    }

    public E remove(E val) throws Exception {
        return (E) map.remove(val);
    }

    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        return Arrays.toString(map.toKeyArray());
    }

    public void forEach(Consumer<? super E> o) {
        map.forEach((k, v) -> {
            o.accept(k);
        });
    }

    @Override
    public Iterator<E> iterator() {
        return new HashSetItr();
    }

    private class HashSetItr implements Iterator<E> {
        private Object[] arr = map.toKeyArray();
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < arr.length;
        }

        @Override
        public E next() {
            return (E) arr[cursor++];
        }
    }
}
