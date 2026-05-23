/*
 * Online Stock Span
 *
 * Monotonic decreasing stack of [index, price] pairs with sentinel [-1, -1].
 * Pop while top price <= current (those days are "covered");
 * span = i - st.peek()[0]. Same left-boundary trick as Largest Rectangle —
 * the surviving top is the nearest day with a strictly greater price.
 */
import java.util.Stack;

public class OnlineStockSpan {
    private final Stack<int[]> st = new Stack<>(); // [index, price]
    private int index = 0;

    public OnlineStockSpan() {
        st.push(new int[]{-1, Integer.MAX_VALUE}); // sentinel
    }

    public int next(int price) {
        while (st.peek()[1] <= price) {
            st.pop();
        }
        int span = index - st.peek()[0];
        st.push(new int[]{index, price});
        index++;
        return span;
    }
}
