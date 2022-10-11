package Maps;

public class LinkedHashMapCustom<K, V> {

    private final int DEFAULT_CAPACITY = 17;
    private Entry previous = null;
    private final Entry[] bucket = new Entry[DEFAULT_CAPACITY];
    private Entry root = null;
    private int size = 0;

    private class Entry<K, V> {

        K key;
        V val;
        Entry next;
        Entry after;
        Entry before;

        public Entry(K key, V val, Entry next, Entry after, Entry before) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.after = after;
            this.before = before;
        }

    }

    private int hashCode(K key) {
        return key.hashCode() % bucket.length;
    }

    public boolean put(K key, V val) {
        int hash = hashCode(key);
        if (root == null) {
            root = new Entry<>(key, val, null, null, null);
            previous = root;
            bucket[hash] = root;
            size++;
            return true;
        }
        Entry<K, V> temp = bucket[hash];
        if (temp == null) {
            bucket[hash] = new Entry<>(key, val, null, null, previous);
            previous.after = bucket[hash];
            previous = bucket[hash];
            size++;
            return true;
        }
        while (temp.next != null) {
            if (temp.key.equals(key)) {
                temp.val = val;
                return true;
            }
            temp = temp.next;
        }
        if (temp.key.equals(key)) {
            temp.val = val;
            return true;
        }
        temp.next = new Entry<>(key, val, null, null, previous);
        previous.after = temp.next;
        previous = temp.next;
        size++;
        return true;
    }

    public V get(K key) {
        int hash = hashCode(key);
        if (bucket[hash] == null) {
            return null;
        }
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
        Entry temp = root;
        while (temp != null) {
            if (temp.val.equals(val)) {
                return true;
            }
            temp = temp.after;
        }
        return false;

    }

    public V remove(K key) throws Exception {
        int hash = hashCode(key);
        Entry temp = bucket[hash];
        if (temp.key.equals(key)) {
            V retval = (V) temp.val;
            if (temp == root) {
                root = temp.after;
            }
            if (temp.before != null) {
                temp.before.after = temp.after;
            }
            if (temp.after != null) {
                temp.after.before = temp.before;
            }
            bucket[hash] = temp.next;
            size--;
            return retval;
        } else {
            while (temp.next != null) {
                if (temp.next.key.equals(key)) {
                    break;
                }
                temp = temp.next;
            }
            if (temp != null) {
                Entry delete = temp.next;
                if (delete == null) {
                    // you can remove exception if you dont want and replace it it return
                    throw new Exception("No such element exception");
                    //return;
                }
                if (delete.before != null) {
                    delete.before.after = delete.after != null ? delete.after : null;
                }
                if (delete.after != null) {
                    delete.after.before = delete.before != null ? delete.before : null;
                }
                temp.next = temp.next.next;
                size--;
                return (V) delete.val;
            }
            return null;
        }
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public void clear() {
        root = null;
        previous = null;
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = null;
        }
    }

    @Override
    public String toString() {
        Entry<K, V> temp = root;
        StringBuilder builder = new StringBuilder("{");
        while (temp != null) {
            builder.append(temp.key + "=" + temp.val + ",");
            temp = temp.after;
        }
        if(builder.length()==1) builder.append(" ");
        String t = builder.substring(0, builder.length() - 1);
        return new StringBuilder(t).append("}").toString();
    }

    public int size() {
        return size;
    }

    public Object[] toKeyArray() {
        Object[] t = new Object[size()];
        int j = 0;
        Entry temp = root;
        while (temp != null) {
            t[j++] = temp.key;
            temp = temp.after;
        }
        return t;
    }

}

// check out the structure of LinkedHashMap image to understand more better
//https://lh4.googleusercontent.com/5M89iub9wNrKR-jmctcmLtGIHiy0lbqL5Oz18AvQyuHdFXGDC4qKgCVZO73WPujj3qqquh_WXlKp7zem4vCfEynJzcN-t0a_wa_mSxDO62G2C-YgEvUhQRBdXdWvpBIhDDM0BxE
