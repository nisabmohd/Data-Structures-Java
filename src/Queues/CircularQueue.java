package Queues;

public class CircularQueue<E> {

    private Object[] arr;
    private int front = -1, rear = 0;

    public CircularQueue(int capacity) {
        arr = new Object[capacity];
    }

    public boolean add(E val) throws Exception {
        if (isFull()) {
            throw new Exception("StackOverflow Exception");
        }
        if (isEmpty()) {
            front = (front + 1) % arr.length;
            arr[rear] = val;
            rear = (rear + 1) % arr.length;
            return true;
        }
        arr[rear] = val;
        rear = (rear + 1) % arr.length;
        return true;
    }

    public E peek() {
        return (E) arr[front];
    }

    public E remove() throws Exception {
        if (isEmpty()) {
            throw new Exception("Empty Stack Exception");
        }

        E ret = (E) arr[front];
        if ((front + 1) % arr.length == rear) {
            front = -1;
            return ret;
        }
        front = (front + 1) % arr.length;
        return ret;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return front == rear;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");
        int i = front;
        do {
            if (!isEmpty()) {
                builder.append(arr[i] + " ");
                i = (i + 1) % arr.length;
            }else{
                break;
            }
        } while (i != rear);
        return builder.append(']').toString();
    }

}
