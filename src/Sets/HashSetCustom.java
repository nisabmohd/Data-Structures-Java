package Sets;

import java.util.*;
import Maps.HashMapCustom;

public class HashSetCustom<E> {

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

}
