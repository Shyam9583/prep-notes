/*
 * Assign Cookies
 *
 * Sort both greed factors and cookie sizes descending. Two pointers: if largest
 * cookie satisfies greediest child, assign it (i++, j++, result++); else the
 * child can't be satisfied by any remaining cookie (i++). Greedy works because
 * matching the biggest available cookie to the most demanding satisfiable child
 * wastes nothing.
 */

import java.util.Arrays;

class AssignCookies {
    static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = g.length - 1, j = s.length - 1;
        int result = 0;
        while (i >= 0 && j >= 0) {
            if (s[j] >= g[i]) {
                result++;
                j--;
            }
            i--;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));       // 1
        System.out.println(findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));       // 2
    }
}
