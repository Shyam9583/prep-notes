/*
 * Implement Max Heap
 *
 * Array-backed heap: element i has parent (i-1)/2, children 2i+1/2i+2.
 * push: place at arr[size++], then heapifyUp — swap with parent while child >
 * parent. pop: swap root with arr[--size] (logical removal), then heapifyDown
 * — track largest among parent and both children, swap and move down.
 * peek returns arr[0], or -1 if empty. Dynamic resize at 75% capacity by 1.5x.
 */

import java.util.Arrays;

class ImplementMaxHeap {
    private int[] arr;
    private int size;

    ImplementMaxHeap(int capacity) {
        arr = new int[capacity];
        size = 0;
    }

    void push(int val) {
        if (size >= arr.length * 0.75) arr = Arrays.copyOf(arr, (int)(arr.length * 1.5));
        arr[size++] = val;
        heapifyUp(size - 1);
    }

    int pop() {
        if (size == 0) throw new RuntimeException("Heap is empty");
        int top = arr[0];
        arr[0] = arr[--size];
        heapifyDown(0);
        return top;
    }

    int peek() { return size == 0 ? -1 : arr[0]; }

    int size() { return size; }

    private void heapifyUp(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (arr[i] > arr[p]) { swap(i, p); i = p; }
            else break;
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int largest = i, l = 2 * i + 1, r = 2 * i + 2;
            if (l < size && arr[l] > arr[largest]) largest = l;
            if (r < size && arr[r] > arr[largest]) largest = r;
            if (largest == i) break;
            swap(i, largest);
            i = largest;
        }
    }

    private void swap(int a, int b) { int t = arr[a]; arr[a] = arr[b]; arr[b] = t; }

    public static void main(String[] args) {
        ImplementMaxHeap h = new ImplementMaxHeap(4);
        h.push(3); h.push(1); h.push(5); h.push(2);
        System.out.println(h.peek());  // 5
        System.out.println(h.pop());   // 5
        System.out.println(h.peek());  // 3
    }
}
