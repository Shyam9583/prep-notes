/*
 * Running sum `sumTillNow`; update global max before resetting. Reset to 0 when
 * sum goes negative (a negative prefix can only hurt). Init global max to
 * `MinInt` to handle all-negative arrays.
 */
class KadanesAlgorithm {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int sumTillNow = 0;
        for (int num : nums) {
            sumTillNow += num;
            maxSum = Math.max(maxSum, sumTillNow);
            if (sumTillNow < 0) sumTillNow = 0;
        }
        return maxSum;
    }
}
