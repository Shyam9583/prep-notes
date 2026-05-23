/*
 * Two pointers: l = last unique, r scans ahead. On new value (nums[r] != nums[l]),
 * increment l and swap into place. Return l+1.
 */
class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int l = 0;
        for (int r = 1; r < nums.length; r++) {
            if (nums[r] != nums[l]) {
                l++;
                nums[l] = nums[r];
            }
        }
        return l + 1;
    }
}
