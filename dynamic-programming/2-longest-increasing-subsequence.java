/*
 * Longest Increasing Subsequence
 * Patience sorting: maintain a tails list where tails[i] is the smallest tail
 * of all IS of length i+1. For each number, binary search for the first tail
 * >= num and replace it; if none, append. tails.size() is the LIS length.
 * tails is not the actual LIS — just a length oracle.
 */
import java.util.*;

class LongestIncreasingSubsequence {
    static int lis(int arr[]) {
        List<Integer> tails = new ArrayList<>();

        for (int num : arr) {
            int size = tails.size();
            if (size == 0 || tails.get(size - 1) < num) {
                tails.add(num);
                continue;
            }
            int loc = find(tails, num);
            tails.set(loc, num);
        }

        return tails.size();
    }

    private static int find(List<Integer> tails, int item) {
        int l = 0, r = tails.size();
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (tails.get(m) < item) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}
