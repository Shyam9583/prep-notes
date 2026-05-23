/*
 * Merge sort variant. During merge, when `left[i] > right[j]`, all remaining
 * elements in left half also form inversions with `right[j]` → add
 * `leftHalf.length - i`. Count inversions while sorting in O(n log n).
 */
class InversionOfArray {
    public long inversionCount(long[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    private long mergeSort(long[] arr, int l, int r) {
        if (l >= r) return 0;
        int mid = (l + r) / 2;
        long count = mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r);
        count += merge(arr, l, mid, r);
        return count;
    }

    private long merge(long[] arr, int l, int mid, int r) {
        long[] left  = java.util.Arrays.copyOfRange(arr, l, mid + 1);
        long[] right = java.util.Arrays.copyOfRange(arr, mid + 1, r + 1);
        int i = 0, j = 0, k = l;
        long count = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                count += left.length - i;
                arr[k++] = right[j++];
            }
        }
        while (i < left.length)  arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
        return count;
    }
}
