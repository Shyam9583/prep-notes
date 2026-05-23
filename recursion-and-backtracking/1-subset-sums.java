/*
 * Subset Sums
 *
 * Binary choice DFS: at each index, branch into exclude (dfs(sum, i+1)) then
 * include (dfs(sum+arr[i], i+1)). Base case at i == len adds sum to result.
 * Yields all 2^n subset sums. Pre-sorting isn't required for correctness but
 * gives a sorted output.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SubsetSums {
    public static List<Integer> subsetSums(int[] arr) {
        List<Integer> result = new ArrayList<>();
        dfs(arr, 0, 0, result);
        Collections.sort(result);
        return result;
    }

    private static void dfs(int[] arr, int i, int sum, List<Integer> result) {
        if (i == arr.length) {
            result.add(sum);
            return;
        }
        // exclude
        dfs(arr, i + 1, sum, result);
        // include
        dfs(arr, i + 1, sum + arr[i], result);
    }

    public static void main(String[] args) {
        System.out.println(subsetSums(new int[]{3, 1, 2})); // [0, 1, 2, 3, 3, 4, 5, 6]
    }
}
