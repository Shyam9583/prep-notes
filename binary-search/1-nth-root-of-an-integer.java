/*
 * N-th Root of an Integer
 *
 * Binary search on [1, m]. pow(x, n, m) uses long and returns a 3-way signal:
 * 0 = too small, 1 = exact, 2 = too big — short-circuit as soon as product
 * exceeds m to avoid overflow. Branch on the signal; return -1 if no exact
 * integer root.
 */

class NthRootOfAnInteger {
    // Returns 0 (too small), 1 (exact), 2 (too big)
    static int pow(long base, int n, long target) {
        long result = 1;
        for (int i = 0; i < n; i++) {
            result *= base;
            if (result > target) return 2;
        }
        return result == target ? 1 : 0;
    }

    static int nthRoot(int n, int m) {
        int l = 1, r = m;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int sig = pow(mid, n, m);
            if (sig == 1) return mid;
            else if (sig == 0) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(nthRoot(3, 27));  // 3
        System.out.println(nthRoot(4, 69));  // -1
        System.out.println(nthRoot(2, 100)); // 10
    }
}
