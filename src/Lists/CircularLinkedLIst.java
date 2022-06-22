package Lists;

public class CircularLinkedLIst<T> {

    private int size = 0;
    private Node head, tail;

    private class Node<T> {

        T val;
        Node next;

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }

    }

    public void add(T val) {
        if (head == null) {
            head = new Node(val, null);
            tail = head;
            tail.next = head;
            size++;
            return;
        }
        tail.next = new Node(val, head);
        tail = tail.next;
        size++;
    }

    public void add(int index, T val) {
        if (index > size) {
            int t = index - size;
            while (t > 0) {
                add(null);
                t--;
            }
            add(val);
            return;
        }
        if (index == size) {
            add(val);
            return;
        }
        Node temp = head;
        while (index > 1) {
            temp = temp.next;
            index--;
        }
        Node node = new Node(val, temp.next);
        temp.next = node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T remove(int index) throws Exception {
        if (index > size - 1) {
            throw new Exception("No such index exception");
        }
        if (index == 0) {
            T retval = (T) head.val;
            head = head.next;
            return retval;
        }
        if (index == size - 1) {
            int tsize = size - 1;
            Node temp = head;
            while (tsize > 1) {
                temp = temp.next;
                tsize--;
            }
            T retval = (T) temp.next.val;
            temp.next = head;
            return retval;
        }
        Node temp = head;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        T retval = (T) temp.val;
        temp.val = temp.next.val;
        temp.next = temp.next.next;
        return retval;
    }

    public T get(int index) throws Exception {
        Node temp = head;
        if (index > size - 1) {
            throw new Exception("No Such Index Exception");
        }
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return (T) temp.val;
    }

    @Override
    public String toString() {
        String ret = "[ ";
        Node temp = head;
        if (temp == null) {
            return ret + "]";
        }
        do {
            ret += temp.val + " ";
            temp = temp.next;
        } while (temp != head);
        return ret + "]";
    }

}
