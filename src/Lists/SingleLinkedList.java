package Lists;

import java.util.Collection;

public class SingleLinkedList<T> implements Cloneable {

    private Node root, tail;
    private int size = 0;

    private class Node<T> {

        T val;
        Node next;

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }

    }

    public boolean add(T val) {
        if (root == null) {
            root = new Node(val, null);
            tail = root;
            size++;
            return tail.val.equals(val);
        }
        tail.next = new Node(val, null);
        tail = tail.next;
        size++;
        return tail.val.equals(val);
    }

    public void add(int index, T val) throws Exception {
        Node temp = root;
        if (index > size) {
            throw new Exception("No such index found exception");
        }
        if (index == size) {
            add(val);
            return;
        }
        if (index == 0) {
            Node node = new Node(val, root);
            root = node;
            size++;
            return;
        }
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        Node node = new Node(val, temp.next);
        temp.next = node;
        size++;
    }

    public boolean contains(T val) {
        Node temp = root;
        while (temp != null) {
            if (temp.val.equals(val)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        int prevSize = size();
        c.forEach(elem -> add(elem));
        return size() - prevSize == c.size();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        size = 0;
        root = null;
        tail = root;
    }

    public T get(int index) throws Exception {
        Node temp = root;
        if (index >= size) {
            throw new Exception("No such index found exception");
        }
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return (T) temp.val;
    }

    public T set(int index, T val) throws Exception {
        Node temp = root;
        if (index > size - 1) {
            throw new Exception("No such index found exception");
        }
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.val = val;
        return (T) temp.val;
    }

    public T remove(int index) throws Exception {
        if (index > size - 1) {
            throw new RuntimeException("No such index found exception");
        }
        Node temp = root;
        if (index == 0) {
            T ret = (T) root.val;
            root = root.next;
            size--;
            return ret;
        }
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        T ret = (T) temp.next.val;
        temp.next = temp.next.next;
        size--;
        return ret;
    }

    @Override
    public String toString() {
        String ret = "[";
        Node temp = root;
        if (temp == null) {
            ret += "]";
            return ret;
        }
        while (temp.next != null) {
            ret += temp.val + ",";
            temp = temp.next;
        }
        ret += temp.val + "]";
        return ret;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Object[] toArray() {
        Node temp = root;
        Object[] arr = new Object[size];
        int i = 0;
        while (temp != null) {
            arr[i++] = temp.val;
            temp = temp.next;
        }
        return arr;
    }

    public T pop() throws Exception {
        return remove(size - 1);
    }

    public T remove() throws Exception {
        //removes head as per Java Docs
        return remove(0);
    }

    public T removeFirst() throws Exception {
        return remove();
    }

    public T removeLast() throws Exception {
        return pop();
    }

    public T peek() {
        if (root != null) {
            return (T) root.val;
        } else {
            throw new RuntimeException("Index Out of Bound Exception");
        }
    }

}
