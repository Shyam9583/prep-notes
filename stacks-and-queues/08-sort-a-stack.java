/*
 * Sort a Stack
 *
 * Recursion: pop top, recurse to sort the rest, then insert(st, top).
 * insert pops elements larger than val onto the call stack, pushes val,
 * then restores them — placing val in sorted position.
 */
import java.util.Stack;

public class SortAStack {
    public void sort(Stack<Integer> st) {
        if (st.isEmpty()) return;
        int top = st.pop();
        sort(st);
        insert(st, top);
    }

    private void insert(Stack<Integer> st, int val) {
        if (st.isEmpty() || st.peek() <= val) {
            st.push(val);
            return;
        }
        int top = st.pop();
        insert(st, val);
        st.push(top);
    }
}
