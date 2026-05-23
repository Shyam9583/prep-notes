/*
 * Boyer-Moore Voting: maintain a candidate and count. Same element → increment,
 * different → decrement. Reset candidate when count hits 0. Majority element
 * always survives because it outnumbers all others combined.
 */
class MajorityElementN2 {
    public int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
