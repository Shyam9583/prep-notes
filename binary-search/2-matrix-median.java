/*
 * Matrix Median
 *
 * Binary search on value range [globalMin, globalMax]. For each mid, count
 * elements <= mid across all rows using per-row lowerBound (tracks last index
 * where nums[m] <= item, returns index + 1 as count). If count <= target
 * (target = rows*cols/2), go right; else go left. Return l — when loop ends,
 * l is the smallest value where countBefore > target, i.e. more than half the
 * matrix is <= l, which is exactly the median.
 */

class MatrixMedian {
    // Count of elements <= item in a sorted row
    static int lowerBound(int[] row, int item) {
        int lo = 0, hi = row.length - 1, idx = -1;
        while (lo <= hi) {
            int m = lo + (hi - lo) / 2;
            if (row[m] <= item) { idx = m; lo = m + 1; }
            else hi = m - 1;
        }
        return idx + 1;
    }

    static int median(int[][] matrix, int rows, int cols) {
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            lo = Math.min(lo, row[0]);
            hi = Math.max(hi, row[cols - 1]);
        }

        int target = (rows * cols) / 2;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            for (int[] row : matrix) count += lowerBound(row, mid);
            if (count <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        System.out.println(median(mat, 3, 3)); // 5
    }
}
