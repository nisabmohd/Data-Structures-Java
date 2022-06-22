package Queues;

import Lists.DoubleLinkedList;

public class QueueCustom<T> extends DoubleLinkedList<T> {

    public boolean offer(T val) {
        return super.add(val);
    }

    public T remove() throws Exception {
        return super.remove(0);
    }

    public T poll() throws Exception {
        return super.remove(0);
    }
    public T peek() throws Exception{
        return super.get(0);
    }
}
