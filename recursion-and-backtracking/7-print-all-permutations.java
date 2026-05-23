/*
 * Print all Permutations of a String/Array
 *
 * Insertion-based: recurse on nums[i+1:] first, then for each partial
 * permutation insert nums[i] at every position 0..len(partial). Alternatively,
 * swap-based in-place: fix index i, swap nums[i] with each nums[j] for j>=i,
 * recurse on i+1, then swap back.
 */

import java.util.ArrayList;
import java.util.List;

class PrintAllPermutations {
    // Swap-based in-place approach
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }

    private static void backtrack(int[] nums, int i, List<List<Integer>> result) {
        if (i == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int num : nums) perm.add(num);
            result.add(perm);
            return;
        }
        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            backtrack(nums, i + 1, result);
            swap(nums, i, j);  // undo
        }
    }

    private static void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
        // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,2,1],[3,1,2]]
    }
}
