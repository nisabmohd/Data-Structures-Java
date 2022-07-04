package Heaps;

import java.util.*;

public class HeapUsingHeapify {

    static class Heap {

        public int[] buildheap(int[] arr) {
            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                heapify(arr, i);
            }
            return arr;
        }

        private void heapify(int[] arr, int i) {
            int maxindex = i;
            int left = (2 * i) + 1;
            int right = (2 * i) + 2;
            if (left < arr.length && arr[left] > arr[maxindex]) {
                maxindex = left;
            }
            if (right < arr.length && arr[right] > arr[maxindex]) {
                maxindex = right;
            }
            if (maxindex != i) {
                int temp = arr[i];
                arr[i] = arr[maxindex];
                arr[maxindex] = temp;
                heapify(arr, maxindex);
            }
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        System.out.println(Arrays.toString(heap.buildheap(new int[]{10, 30, 50, 20, 35, 95})));
    }
}
