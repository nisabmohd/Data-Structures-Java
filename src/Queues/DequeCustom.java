package Queues;

public class DequeCustom<T> extends QueueCustom<T> {

    public boolean addFirst(T val) throws Exception {
        return super.add(0, val);
    }

    public boolean addLast(T val) throws Exception {
        return super.add(val);
    }

    public T removeFirst() throws Exception {
        return super.remove();
    }

    public T removeLast() throws Exception {
        return super.remove(size() - 1);
    }

}
