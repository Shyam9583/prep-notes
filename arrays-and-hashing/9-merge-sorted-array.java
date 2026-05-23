/*
 * Fill `nums1` from the back using 3 pointers: `i=m-1`, `j=n-1`, `k=m+n-1`.
 * Place the larger of `nums1[i]`/`nums2[j]` at `k`. Remaining `nums2` elements
 * need copying; remaining `nums1` elements are already in place.
 */
class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
        while (j >= 0) nums1[k--] = nums2[j--];
    }
}
