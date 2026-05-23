/*
 * Implement Queue using Arrays
 *
 * Circular array queue: front and rear start at -1.
 * enqueue: if full throw overflow, else rear = (rear+1) % capacity, arr[rear] = x
 *   (set front=0 on first insert).
 * dequeue: if empty throw underflow, else save arr[front], advance
 *   front = (front+1) % capacity (reset both to -1 when last element removed).
 * peek returns arr[front]. All ops O(1).
 */
public class ImplementQueueUsingArrays {
    private final int[] arr;
    private int front;
    private int rear;
    private int size;
    private final int capacity;

    public ImplementQueueUsingArrays(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(int x) {
        if (size == capacity) throw new RuntimeException("Queue Overflow");
        if (front == -1) front = 0;
        rear = (rear + 1) % capacity;
        arr[rear] = x;
        size++;
    }

    public int dequeue() {
        if (size == 0) throw new RuntimeException("Queue Underflow");
        int val = arr[front];
        size--;
        if (size == 0) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        return val;
    }

    public int peek() {
        if (size == 0) throw new RuntimeException("Queue is empty");
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
