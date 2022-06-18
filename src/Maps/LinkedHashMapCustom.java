package Maps;

public class LinkedHashMapCustom<K, V> {

    private final int DEFAULT_CAPACITY = 17;
    private Entry previous = null;
    private final Entry[] bucket = new Entry[DEFAULT_CAPACITY];
    private Entry root = null;

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
        return key.toString().length() > 13 ? key.toString().length() % DEFAULT_CAPACITY : key.toString().length() % DEFAULT_CAPACITY % 10;
    }

    public void put(K key, V val) {

        int hash = hashCode(key);
        Entry t = new Entry(key, val, null, null, previous);
        if (bucket[hash] == null) {
            bucket[hash] = t;
        } else {
            Entry temp = bucket[hash];
            while (temp.next != null) {
                if (temp.key == key) {
                    temp.val = val;
                    return;
                }
                temp = temp.next;
            }
            temp.next = t;
            t.before = previous;
        }
        if (previous != null) {
            previous.after = t;
        }
        previous = t;
        if (root == null) {
            root = t;
        }
    }

    public V get(K key) {
        int hash = hashCode(key);
        if (bucket[hash] == null) {
            return null;
        }
        Entry temp = bucket[hash];
        while (temp != null) {
            if (temp.key == key) {
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
            if (temp.val == val) {
                return true;
            }
            temp = temp.after;
        }
        return false;

    }

    public void remove(K key) throws Exception {
        int hash = hashCode(key);
        Entry temp = bucket[hash];
        if (temp.key == key) {
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
        } else {
            while (temp.next != null) {
                if (temp.next.key == key) {
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
            }
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
        String ret = "[ ";
        Entry temp = root;
        while (temp != null) {
            ret += temp.key + "=" + temp.val + " ";
            temp = temp.after;
        }
        ret += "]";
        return ret;
    }

}

// check out the structure of LinkedHashMap image to understand more better
//https://lh4.googleusercontent.com/5M89iub9wNrKR-jmctcmLtGIHiy0lbqL5Oz18AvQyuHdFXGDC4qKgCVZO73WPujj3qqquh_WXlKp7zem4vCfEynJzcN-t0a_wa_mSxDO62G2C-YgEvUhQRBdXdWvpBIhDDM0BxE
