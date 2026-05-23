/*
 * Implement Stack using Arrays
 *
 * Array-backed stack: arr[top] is the current top, top starts at -1.
 * push: if top == capacity-1 throw overflow, else arr[++top] = x.
 * pop: if top == -1 throw underflow, else return arr[top--].
 * peek returns arr[top]. All ops O(1).
 */
public class ImplementStackUsingArrays {
    private final int[] arr;
    private int top;
    private final int capacity;

    public ImplementStackUsingArrays(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.top = -1;
    }

    public void push(int x) {
        if (top == capacity - 1) throw new RuntimeException("Stack Overflow");
        arr[++top] = x;
    }

    public int pop() {
        if (top == -1) throw new RuntimeException("Stack Underflow");
        return arr[top--];
    }

    public int peek() {
        if (top == -1) throw new RuntimeException("Stack is empty");
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}
