package Queues;

import Lists.SingleLinkedList;

public class QueueCustom<T> extends SingleLinkedList<T> {

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
