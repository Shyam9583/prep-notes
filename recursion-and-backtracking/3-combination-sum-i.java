/*
 * Combination Sum I
 *
 * DFS with remaining. Include branch reuses the same index i (unlimited
 * picks); exclude branch advances i+1. Base cases: remaining == 0 -> add
 * clone; remaining < 0 || i == len -> prune. No sorting required but helps
 * with pruning.
 */

import java.util.ArrayList;
import java.util.List;

class CombinationSumI {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
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
        // include candidates[i] (reuse same index)
        current.add(candidates[i]);
        dfs(candidates, i, remaining - candidates[i], current, result);
        current.remove(current.size() - 1);

        // exclude candidates[i], advance to next
        dfs(candidates, i + 1, remaining, current, result);
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        // [[2, 2, 3], [7]]
    }
}
