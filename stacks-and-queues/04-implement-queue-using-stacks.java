/*
 * Implement Queue using Stacks
 *
 * Two stacks: inbox, outbox. push always goes to inbox.
 * pop/peek: if outbox empty, drain all of inbox into outbox (reversal makes
 * oldest element the new top). Never transfer when outbox is non-empty —
 * that would break existing order. Amortized O(1) per op.
 */
import java.util.Stack;

public class ImplementQueueUsingStacks {
    private final Stack<Integer> inbox = new Stack<>();
    private final Stack<Integer> outbox = new Stack<>();

    public void push(int x) {
        inbox.push(x);
    }

    public int pop() {
        shiftIfNeeded();
        if (outbox.isEmpty()) throw new RuntimeException("Queue is empty");
        return outbox.pop();
    }

    public int peek() {
        shiftIfNeeded();
        if (outbox.isEmpty()) throw new RuntimeException("Queue is empty");
        return outbox.peek();
    }

    public boolean isEmpty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }

    private void shiftIfNeeded() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
    }
}
