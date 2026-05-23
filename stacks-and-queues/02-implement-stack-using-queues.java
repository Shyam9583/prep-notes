/*
 * Implement Stack using Queues
 *
 * Single queue. On push(x): enqueue x, then rotate the queue size-1 times
 * (add(poll())) so x moves to the front. pop/top just poll/peek.
 * O(n) push, O(1) pop/top.
 */
import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueues {
    private final Queue<Integer> q = new LinkedList<>();

    public void push(int x) {
        q.add(x);
        int size = q.size();
        for (int i = 0; i < size - 1; i++) {
            q.add(q.poll());
        }
    }

    public int pop() {
        if (q.isEmpty()) throw new RuntimeException("Stack Underflow");
        return q.poll();
    }

    public int top() {
        if (q.isEmpty()) throw new RuntimeException("Stack is empty");
        return q.peek();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }
}
