/*
 * Median of Two Sorted Arrays
 *
 * Binary search on partition of the smaller array. cut1 in [0, m] (counts
 * elements taken, not index — so l=0, r=m), cut2 = half - cut1 where
 * half = (m+n+1)/2 (+1 so odd-total left side gets the extra element).
 * Valid partition when l1 <= r2 && l2 <= r1; if l1 > r2 took too many from
 * nums1 so go left, else go right. Sentinels MIN/MAX_VALUE handle edge
 * partitions uniformly. Odd total -> max(l1, l2); even -> (max(l1,l2) +
 * min(r1,r2)) / 2.0. O(log(min(m,n))).
 */

class MedianOfTwoSortedArrays {
    static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) return findMedianSortedArrays(b, a);
        int m = a.length, n = b.length;
        int half = (m + n + 1) / 2;
        int l = 0, r = m;

        while (l <= r) {
            int cut1 = l + (r - l) / 2;
            int cut2 = half - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : a[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : b[cut2 - 1];
            int r1 = cut1 == m ? Integer.MAX_VALUE : a[cut1];
            int r2 = cut2 == n ? Integer.MAX_VALUE : b[cut2];

            if (l1 <= r2 && l2 <= r1) {
                if ((m + n) % 2 == 1) return Math.max(l1, l2);
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            } else if (l1 > r2) r = cut1 - 1;
            else l = cut1 + 1;
        }
        return 0.0;
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));       // 2.0
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));     // 2.5
    }
}
