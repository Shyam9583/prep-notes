/*
 * Search in Rotated Sorted Array
 *
 * One half is always sorted. Check nums[l] <= nums[m]: if true, left half is
 * sorted — target in [nums[l], nums[m]] -> go left, else go right. Otherwise
 * right half is sorted — target in [nums[m], nums[r]] -> go right, else go
 * left. Return m on hit, -1 if loop exits.
 */

class SearchInRotatedSortedArray {
    static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;

            if (nums[l] <= nums[m]) {           // left half is sorted
                if (target >= nums[l] && target < nums[m]) r = m - 1;
                else l = m + 1;
            } else {                            // right half is sorted
                if (target > nums[m] && target <= nums[r]) l = m + 1;
                else r = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0)); // 4
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3)); // -1
    }
}
