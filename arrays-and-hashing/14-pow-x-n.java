/*
 * Binary exponentiation: iterate over bits of `|n|`; if bit is set, multiply
 * result by current `mult`; square `mult` each iteration. O(log n). Negative n
 * → return `1/result`. Watch out: `math.MinInt` overflow when negating —
 * handle separately.
 */
class PowXN {
    public double myPow(double x, int n) {
        long exp = Math.abs((long) n);
        double result = 1.0, mult = x;
        while (exp > 0) {
            if ((exp & 1) == 1) result *= mult;
            mult *= mult;
            exp >>= 1;
        }
        return n < 0 ? 1.0 / result : result;
    }
}
