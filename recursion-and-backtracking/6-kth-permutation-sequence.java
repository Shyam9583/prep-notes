/*
 * K-th Permutation Sequence
 *
 * Factorial number system: build nums=[1..n], decrement k for 0-based
 * indexing, then repeatedly pick digit at pos = k / (n-1)!, remove it from
 * nums, and reduce k %= fact, fact /= remaining.
 */

import java.util.ArrayList;
import java.util.List;

class KthPermutationSequence {
    public static String getPermutation(int n, int k) {
        // build factorial table and digit list
        int fact = 1;
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            fact *= i;
            nums.add(i);
        }

        k--;  // convert to 0-based index
        StringBuilder sb = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            fact /= i;              // fact = (i-1)!
            int pos = k / fact;     // which digit to pick
            sb.append(nums.get(pos));
            nums.remove(pos);
            k %= fact;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(3, 3)); // "213"
        System.out.println(getPermutation(4, 9)); // "2314"
    }
}
