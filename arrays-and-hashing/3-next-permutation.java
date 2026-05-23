/*
 * 3 steps: (1) find rightmost breakpoint where `nums[i] < nums[i+1]`;
 * (2) swap it with the rightmost element greater than it;
 * (3) reverse the suffix after breakpoint. If no breakpoint, array is
 * fully descending — just reverse all.
 */
class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int bp = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) { bp = i; break; }
        }
        if (bp != -1) {
            for (int i = n - 1; i > bp; i--) {
                if (nums[i] > nums[bp]) { swap(nums, i, bp); break; }
            }
        }
        reverse(nums, bp + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) swap(nums, l++, r--);
    }
}
