/*
 * Single pass: increment counter on 1, reset to 0 on 0, track running max.
 * O(n) time, O(1) space.
 */
class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, max = 0;
        for (int n : nums) {
            if (n == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }
}
