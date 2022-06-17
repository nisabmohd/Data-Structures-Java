package Maps;

import java.util.*;

public class LinkedHashMapCustom<K, V> {

    private final int DEFAULT_CAPACITY = 17;
    private Node previous = null;
    private Node[] bucket = new Node[DEFAULT_CAPACITY];
    private Node root = null;

    private class Node<K, V> {

        K key;
        V val;
        Node next;
        Node after;
        Node before;

        public Node(K key, V val, Node next, Node after, Node before) {
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
        Node t = new Node(key, val, null, null, previous);
        if (bucket[hash] == null) {
            bucket[hash] = t;
        } else {
            Node temp = bucket[hash];
            while (temp.next != null) {
                if (temp.key == key) {
                    temp.val = val;
                    return;
                };
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

    @Override
    public String toString() {
        String ret = "[ ";
        Node temp = root;
        while (temp != null) {
            ret += temp.key + "=" + temp.val + " ";
            temp = temp.after;
        }
        ret += "]";
        return ret;
    }

    public V get(K key) {
        int hash = hashCode(key);
        if (bucket[hash] == null) {
            return null;
        }
        Node temp = bucket[hash];
        while (temp != null) {
            if (temp.key == key) {
                return (V) temp.val;
            }
            temp = temp.next;
        }
        return null;

    }

    public boolean containsKey(K key) {
        if (get(key) == null) {
            return false;
        }
        return true;
    }

    public boolean containsValue(V val) {
        Node temp = root;
        while (temp != null) {
            if (temp.val == val) {
                return true;
            }
            temp = temp.after;
        }
        return false;

    }

}
