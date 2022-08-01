package Sets;

import Maps.TreeMapCustom;

public class TreeSetCustom<K extends Comparable<K>> {

    private TreeMapCustom<K, Object> map = new TreeMapCustom<>();
    private final Object obj = new Object();

    public TreeSetCustom() {
    }

    public boolean add(K val) {
        return map.put(val, obj);
    }

    public void clear() {
        map.clear();
    }

    public boolean contains(K val) {
        return map.containsKey(val);
    }

    public K remove(K val) throws Exception {
        return (K) map.remove(val);
    }

    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        return map.toKeyArray().toString();
    }

}
