/*
 * Each cell = sum of two cells above. Use a `get(prev, j)` helper that returns 0
 * for out-of-bounds indices — cleanly handles the edge 1s without special-casing.
 */
import java.util.ArrayList;
import java.util.List;

class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> prev = i > 0 ? result.get(i - 1) : null;
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                row.add(get(prev, j - 1) + get(prev, j));
            }
            result.add(row);
        }
        return result;
    }

    private int get(List<Integer> row, int j) {
        if (row == null || j < 0 || j >= row.size()) return 0;
        return row.get(j);
    }
}
