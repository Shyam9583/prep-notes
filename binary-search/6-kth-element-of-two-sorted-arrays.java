/*
 * K-th Element of Two Sorted Arrays
 *
 * Value-range binary search on [min(a[0],b[0]), max(a[m-1],b[n-1])]. Count
 * elements <= x across both arrays via lowerBound (returns lastIndex + 1).
 * If count < k go right, else go left. Return l — converges to smallest value
 * where count >= k, which is the k-th element. Same pattern as Matrix Median.
 */

class KthElementOfTwoSortedArrays {
    static int lowerBound(int[] arr, int x) {
        int lo = 0, hi = arr.length - 1, idx = -1;
        while (lo <= hi) {
            int m = lo + (hi - lo) / 2;
            if (arr[m] <= x) { idx = m; lo = m + 1; }
            else hi = m - 1;
        }
        return idx + 1;
    }

    static int kthElement(int[] a, int[] b, int k) {
        int lo = Math.min(a[0], b[0]);
        int hi = Math.max(a[a.length - 1], b[b.length - 1]);

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = lowerBound(a, mid) + lowerBound(b, mid);
            if (count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        System.out.println(kthElement(new int[]{2,3,6,7,9}, new int[]{1,4,8,10}, 5)); // 6
        System.out.println(kthElement(new int[]{100,112,256,349,770}, new int[]{72,86,113,119,265,445,892}, 7)); // 256
    }
}
