/*
 * Matrix Chain Multiplication
 * Memoised recursion on [l, r] (index range in dims array). Split at every k
 * in (l, r): cost = find(l,k) + find(k,r) + arr[l]*arr[k]*arr[r].
 * Base case: l+1 == r → 0 (single matrix).
 * arr stores dimensions so matrix i is arr[i-1] × arr[i].
 */
import java.util.*;

class MatrixChainMultiplication {
    static int matrixMultiplication(int arr[]) {
        Map<List<Integer>, Integer> dp = new HashMap<>();
        return find(arr, 0, arr.length - 1, dp);
    }

    private static int find(int[] arr, int l, int r, Map<List<Integer>, Integer> dp) {
        if (l + 1 == r) return 0;

        List<Integer> key = List.of(l, r);
        if (dp.containsKey(key)) return dp.get(key);

        int min = Integer.MAX_VALUE;
        for (int k = l + 1; k < r; k++) {
            min = Math.min(min, find(arr, l, k, dp) + find(arr, k, r, dp) + arr[l] * arr[k] * arr[r]);
        }
        dp.put(key, min);
        return min;
    }
}
