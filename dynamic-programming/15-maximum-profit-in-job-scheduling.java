/*
 * Maximum Profit in Job Scheduling
 * Sort by start time. dp[i] = max profit from jobs i..n. For each job:
 * skip = dp[i+1]; take = profit[i] + dp[nextValid] where nextValid is
 * binary-searched as first job with start >= end[i]. dp[0] is the answer.
 */
import java.util.*;

class MaximumProfitInJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs, (a, b) -> a.start - b.start);

        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int skip = dp[i + 1];
            int next = nextValid(jobs, jobs[i].end);
            int take = jobs[i].profit + dp[next];
            dp[i] = Math.max(skip, take);
        }

        return dp[0];
    }

    private int nextValid(Job[] jobs, int lastEnd) {
        int l = 0, r = jobs.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (jobs[m].start < lastEnd) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    private static class Job {
        private int start, end, profit;
        Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
}
