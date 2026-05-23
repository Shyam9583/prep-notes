/*
 * Implement Min Stack
 *
 * Auxiliary min stack that only pushes when val <= min.peek(); on pop, remove
 * from min only if the popped value equals min.peek(); both stacks stay in sync
 * because equal values are tracked (handles duplicate minimums).
 */
import java.util.Stack;

public class ImplementMinStack {
    private final Stack<Integer> main = new Stack<>();
    private final Stack<Integer> min = new Stack<>();

    public void push(int val) {
        main.push(val);
        if (min.isEmpty() || val <= min.peek()) {
            min.push(val);
        }
    }

    public void pop() {
        int val = main.pop();
        if (val == min.peek()) {
            min.pop();
        }
    }

    public int top() {
        return main.peek();
    }

    public int getMin() {
        return min.peek();
    }
}
