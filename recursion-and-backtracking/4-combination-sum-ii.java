/*
 * Combination Sum II
 *
 * Sort first. Same include/exclude DFS as Subsets II: include advances i+1
 * (each element used once); before exclude branch, skip consecutive
 * duplicates. Combines the no-reuse rule from Subsets II with the remaining
 * pruning from Combination Sum I.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private static void dfs(int[] candidates, int i, int remaining,
                             List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (remaining < 0 || i == candidates.length) {
            return;
        }
        // include candidates[i] (advance to i+1, each element used once)
        current.add(candidates[i]);
        dfs(candidates, i + 1, remaining - candidates[i], current, result);
        current.remove(current.size() - 1);

        // exclude: skip consecutive duplicates
        while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
            i++;
        }
        dfs(candidates, i + 1, remaining, current, result);
    }

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
    }
}
