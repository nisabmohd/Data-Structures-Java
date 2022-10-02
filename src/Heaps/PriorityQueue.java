package Heaps;

import java.util.ArrayList;
import java.util.Comparator;

public class PriorityQueue<T extends Comparable<T>> {

    ArrayList<T> list = new ArrayList<>();
    final Comparator<T> comparator;

    public PriorityQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public PriorityQueue() {
        this.comparator = Comparator.naturalOrder();
    }

    // Incomplete yet
    
}
