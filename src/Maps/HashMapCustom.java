package Maps;
//check out my medium blog for hashmap
//https://medium.com/@nisabmohd/hashmap-in-java-c010dec8fbd0

public class HashMapCustom<K, V> {

    private final int DEFAULT_CAPACITY = 17;
    private final Entry[] bucket = new Entry[DEFAULT_CAPACITY];
    private int size = 0;

    private class Entry<K, V> {

        K key;
        V val;
        Entry next;

        public Entry(K key, V val, Entry next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

    }

    private int hashCode(K key) {
        return key.hashCode() % bucket.length;
    }

    public boolean put(K key, V val) {
        int hash = hashCode(key);
        if (bucket[hash] == null) {
            bucket[hash] = new Entry(key, val, null);
            size++;
            return true;
        }
        Entry temp = bucket[hash];
        if (temp.key.equals(key)) {
            temp.val = val;
            return true;
        }
        while (temp.next != null) {
            if (temp.key.equals(key)) {
                temp.val = val;
                return true;
            }
            temp = temp.next;
        }
        size++;
        temp.next = new Entry(key, val, null);
        return true;
    }

    public V get(K key) {
        int hash = hashCode(key);
        Entry temp = bucket[hash];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return (V) temp.val;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean containsValue(V val) {
        for (int i = 0; i < bucket.length; i++) {
            Entry temp = bucket[i];
            while (temp != null) {
                if (temp.val.equals(val)) {
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    public V remove(K key) throws Exception {
        int hash = hashCode(key);
        Entry temp = bucket[hash];
        if (temp == null) {
            throw new Exception("No such element exception");
        }
        if (temp.key.equals(key)) {
            V retval = (V) bucket[hash].val;
            bucket[hash] = temp.next;
            size--;
            return retval;
        }
        while (temp.next != null) {
            if (temp.next.key.equals(key)) {
                break;
            }
            temp = temp.next;
        }
        if (temp.next == null) {
            throw new Exception("No such element exception");
        }
        V retval = (V) temp.next.val;
        temp.next = temp.next.next;
        size--;
        return retval;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public void clear() {
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = null;
        }
    }

    @Override
    public String toString() {
        String str = "{ ";
        for (int i = 0; i < bucket.length; i++) {
            Entry temp = bucket[i];
            while (temp != null) {
                str += temp.key + "=" + temp.val + " ";
                temp = temp.next;
            }
        }
        str += "}";
        return str;
    }

    public int size() {
        return size;
    }

    public Object[] toKeyArray() {
        Object[] t = new Object[size()];
        int j = 0;
        for (int i = 0; i < bucket.length; i++) {
            Entry temp = bucket[i];
            while (temp != null) {
                t[j++] = temp.key;
                temp = temp.next;
            }
        }
        return t;
    }

}
