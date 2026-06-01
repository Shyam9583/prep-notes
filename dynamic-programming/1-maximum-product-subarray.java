/*
 * Maximum Product Subarray
 * Track currMin and currMax ending at each index — a negative number can flip
 * min to max. Save prevMin before updating since currMax update needs the old
 * min. Candidate for each: num alone, currMin*num, currMax*num.
 * Update global res from currMax each step.
 */

class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int currMin = 1;
        int currMax = 1;
        int res = Integer.MIN_VALUE;

        for (int num : nums) {
            int prevMin = currMin;
            currMin = Math.min(num, Math.min(currMin * num, currMax * num));
            currMax = Math.max(num, Math.max(prevMin * num, currMax * num));
            res = Math.max(res, currMax);
        }

        return res;
    }
}
