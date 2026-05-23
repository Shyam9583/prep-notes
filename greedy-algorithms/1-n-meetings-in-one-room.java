/*
 * N Meetings in One Room
 *
 * Sort by end time (greedy: earliest finish frees the room soonest). Track `last`
 * meeting; include `curr` if `last.end < curr.start`. Count starts at 1.
 * Rule: sort by end time to maximise utilisation with limited resources; sort by
 * start time to calculate resources required (e.g. minimum platforms).
 */

import java.util.Arrays;

class NMeetingsInOneRoom {
    static int maxMeetings(int[] start, int[] end, int n) {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> end[a] != end[b] ? end[a] - end[b] : a - b);

        int count = 1;
        int lastEnd = end[idx[0]];
        for (int i = 1; i < n; i++) {
            int cur = idx[i];
            if (start[cur] > lastEnd) {
                count++;
                lastEnd = end[cur];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end   = {2, 4, 6, 7, 9, 9};
        System.out.println(maxMeetings(start, end, start.length)); // 4
    }
}
