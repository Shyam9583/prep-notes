/*
 * Fractional Knapsack Problem
 *
 * Sort by value/weight ratio descending. Greedily take whole items while
 * capacity allows; for the item that doesn't fit, take the fraction
 * remaining / wt[i]. Unlike 0-1 knapsack, fractional allows splitting so
 * greedy is optimal.
 */

import java.util.Arrays;

class FractionalKnapsackProblem {
    static double fractionalKnapsack(int W, int[] wt, int[] val, int n) {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> Double.compare(
                (double) val[b] / wt[b], (double) val[a] / wt[a]));

        double totalValue = 0;
        int remaining = W;
        for (int i : idx) {
            if (remaining <= 0) break;
            if (wt[i] <= remaining) {
                totalValue += val[i];
                remaining -= wt[i];
            } else {
                totalValue += (double) val[i] * remaining / wt[i];
                remaining = 0;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        int[] wt  = {10, 20, 30};
        int[] val = {60, 100, 120};
        System.out.println(fractionalKnapsack(50, wt, val, 3)); // 240.0
    }
}
