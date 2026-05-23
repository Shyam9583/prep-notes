/*
 * Subsets II
 *
 * Sort first. DFS with include/exclude: always recurse into include; before
 * the exclude branch, skip all consecutive duplicates (while nums[i] ==
 * nums[i+1]: i++). This ensures a duplicate value is only ever excluded at
 * the first occurrence, preventing duplicate subsets.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SubsetsII {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void dfs(int[] nums, int i, List<Integer> current, List<List<Integer>> result) {
        if (i == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        // include nums[i]
        current.add(nums[i]);
        dfs(nums, i + 1, current, result);
        current.remove(current.size() - 1);

        // exclude: skip all consecutive duplicates
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }
        dfs(nums, i + 1, current, result);
    }

    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
        // [[1, 2, 2], [1, 2], [1], [2, 2], [2], []]
    }
}
