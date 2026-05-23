/*
 * Minimum Number of Platforms Required
 *
 * Sort arrivals and departures independently. Two pointers: if next arrival <=
 * next departure, a new train needs a platform (curr++, advance i); else a train
 * has left (curr--, advance j). Track running max. Sort by start time — we're
 * counting concurrent resource usage, not maximising selection.
 */

import java.util.Arrays;

class MinimumNumberOfPlatformsRequired {
    static int findPlatform(int[] arr, int[] dep, int n) {
        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 1, j = 0;
        int curr = 1, max = 1;
        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                curr++;
                i++;
            } else {
                curr--;
                j++;
            }
            max = Math.max(max, curr);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {900, 940, 950, 1100, 1500, 1800};
        int[] dep = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println(findPlatform(arr, dep, arr.length)); // 3
    }
}
