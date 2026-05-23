/*
 * Math: let x=repeat, y=missing. Compute `S = Σ(arr[i]-i)` → gives `x-y`.
 * Compute `S2 = Σ(arr[i]²-i²)` → gives `x²-y² = (x+y)(x-y)`, so
 * `x+y = S2/S`. Solve: `x=(S+S2/S)/2`, `y=(S2/S-S)/2`.
 */
class RepeatAndMissingNumber {
    public int[] findTwoElement(int[] arr) {
        int n = arr.length;
        long S = 0, S2 = 0;
        for (int i = 0; i < n; i++) {
            S  += (long) arr[i] - (i + 1);
            S2 += (long) arr[i] * arr[i] - (long)(i + 1) * (i + 1);
        }
        long sum = S2 / S;
        int x = (int)((S + sum) / 2);
        int y = (int)((sum - S) / 2);
        return new int[]{x, y};
    }
}
