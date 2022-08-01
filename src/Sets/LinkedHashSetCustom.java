package Sets;

import Maps.LinkedHashMapCustom;
import java.util.Arrays;

public class LinkedHashSetCustom<E> {

    public LinkedHashSetCustom() {
    }

    private final Object obj = new Object();
    LinkedHashMapCustom<E, Object> map = new LinkedHashMapCustom<>();

    public boolean add(E val) {
        return map.put(val, obj);
    }

    public void clear() {
        map.clear();
        map = new LinkedHashMapCustom<>();
    }

    public boolean contains(E val) {
        return map.containsKey(val);
    }

    public int size() {
        return map.size();
    }

    public E remove(E val) throws Exception {
        return (E) map.remove(val);
    }

    @Override
    public String toString() {
        return Arrays.toString(map.toKeyArray());
    }
}
