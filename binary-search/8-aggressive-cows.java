/*
 * Aggressive Cows
 *
 * Sort stalls. Binary search on answer range [1, stalls[n-1]-stalls[0]].
 * Greedily count cows placeable with min distance m (track lastFit, place next
 * cow when gap >= m). If count >= k the distance is achievable — go right to
 * maximise; else go left. Return r.
 * Pattern — maximise minimum: valid condition pushes l right, return r.
 * Pattern — minimise maximum (e.g. Book Allocation): valid condition pushes r
 * left, return l.
 */

import java.util.Arrays;

class AggressiveCows {
    static boolean canPlace(int[] stalls, int k, int minDist) {
        int count = 1, last = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - last >= minDist) { count++; last = stalls[i]; }
            if (count >= k) return true;
        }
        return false;
    }

    static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int l = 1, r = stalls[stalls.length - 1] - stalls[0];
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (canPlace(stalls, k, mid)) l = mid + 1;
            else r = mid - 1;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(aggressiveCows(new int[]{1, 2, 4, 8, 9}, 3)); // 3
        System.out.println(aggressiveCows(new int[]{10, 1, 2, 7, 5}, 3)); // 4
    }
}
