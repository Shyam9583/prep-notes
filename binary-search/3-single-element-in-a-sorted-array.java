/*
 * Single Element in a Sorted Array
 *
 * Invariant: before the single element, pairs sit at even indices; after it,
 * pairs sit at odd indices. At mid: if m is even and nums[m]==nums[m+1] (or m
 * is odd and nums[m]==nums[m-1]), the single element is to the right — go
 * right. Otherwise go left. Return nums[m] when neither neighbor matches.
 */

class SingleElementInASortedArray {
    static int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            // Ensure m is even so the "pair starts at even" invariant is easy to check
            if (m % 2 == 1) m--;
            if (nums[m] == nums[m + 1]) l = m + 2;
            else r = m;
        }
        return nums[l];
    }

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8})); // 2
        System.out.println(singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));  // 10
    }
}
