/*
 * Floyd's cycle detection on the array as a linked list (value = next index).
 * Phase 1: find intersection point. Phase 2: reset slow to 0, advance both
 * one step — they meet at the cycle entry = duplicate.
 */
class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
