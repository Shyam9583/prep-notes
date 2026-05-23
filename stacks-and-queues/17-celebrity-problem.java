/*
 * Celebrity Problem
 *
 * Push all indices onto stack. While size > 1: pop a and b; if a knows b then
 * a is eliminated (push back b), else b is eliminated (push back a). Final
 * candidate must be validated: every other person knows them, and they know
 * nobody.
 */
import java.util.Stack;

public class CelebrityProblem {
    // knows(a, b) returns true if person a knows person b
    private boolean knows(int[][] matrix, int a, int b) {
        return matrix[a][b] == 1;
    }

    public int findCelebrity(int[][] matrix) {
        int n = matrix.length;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) st.push(i);

        while (st.size() > 1) {
            int a = st.pop();
            int b = st.pop();
            if (knows(matrix, a, b)) {
                st.push(b); // a is eliminated
            } else {
                st.push(a); // b is eliminated
            }
        }

        int candidate = st.pop();
        // validate
        for (int i = 0; i < n; i++) {
            if (i == candidate) continue;
            if (!knows(matrix, i, candidate) || knows(matrix, candidate, i)) {
                return -1;
            }
        }
        return candidate;
    }
}
