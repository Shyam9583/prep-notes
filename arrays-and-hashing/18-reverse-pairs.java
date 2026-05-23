/*
 * Merge sort variant. Count cross-pairs (`left[i] > 2*right[j]`) in `countPairs`
 * *before* merging (merge destroys order). Two-pointer in `countPairs` works
 * because both halves are sorted — `j` never resets, giving O(n) per level.
 */
class ReversePairs {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int l, int r) {
        if (l >= r) return 0;
        int mid = (l + r) / 2;
        int count = mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);
        count += countPairs(nums, l, mid, r);
        merge(nums, l, mid, r);
        return count;
    }

    private int countPairs(int[] nums, int l, int mid, int r) {
        int count = 0, j = mid + 1;
        for (int i = l; i <= mid; i++) {
            while (j <= r && nums[i] > 2L * nums[j]) j++;
            count += j - (mid + 1);
        }
        return count;
    }

    private void merge(int[] nums, int l, int mid, int r) {
        int[] left  = java.util.Arrays.copyOfRange(nums, l, mid + 1);
        int[] right = java.util.Arrays.copyOfRange(nums, mid + 1, r + 1);
        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) nums[k++] = left[i++];
            else nums[k++] = right[j++];
        }
        while (i < left.length)  nums[k++] = left[i++];
        while (j < right.length) nums[k++] = right[j++];
    }
}
