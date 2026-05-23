/*
 * Dutch National Flag: 3 pointers `left/mid/right`. 0→swap with left, advance
 * both; 2→swap with right, decrement right only (don't advance mid, swapped val
 * is unexamined); 1→just advance mid.
 */
class SortColors {
    public void sortColors(int[] nums) {
        int left = 0, mid = 0, right = nums.length - 1;
        while (mid <= right) {
            if (nums[mid] == 0) {
                swap(nums, left++, mid++);
            } else if (nums[mid] == 2) {
                swap(nums, mid, right--);
            } else {
                mid++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i]; nums[i] = nums[j]; nums[j] = t;
    }
}
