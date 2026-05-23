/*
 * Treat as flattened 1D sorted array. Binary search on `[0, m*n-1]`; map index
 * back via `row = mid/n`, `col = mid%n`. Works because rows are sorted and last
 * element of each row < first of next.
 */
class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m * n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = matrix[mid / n][mid % n];
            if (val == target) return true;
            else if (val < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return false;
    }
}
