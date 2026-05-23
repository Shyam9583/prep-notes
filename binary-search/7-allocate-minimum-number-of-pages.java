/*
 * Allocate Minimum Number of Pages
 *
 * Binary search on answer range [max(arr), sum(arr)]. l = max because one
 * student must read at least the largest book. For each mid, greedily count
 * students needed (read + book > allowed -> new student, read = book). If
 * required <= k go left, else go right. Return l.
 * Edge case: k > arr.length -> return -1.
 */

class AllocateMinimumNumberOfPages {
    static int countStudents(int[] arr, int limit) {
        int students = 1, read = 0;
        for (int book : arr) {
            if (read + book > limit) { students++; read = book; }
            else read += book;
        }
        return students;
    }

    static int allocatePages(int[] arr, int k) {
        int n = arr.length;
        if (k > n) return -1;

        int l = 0, r = 0;
        for (int book : arr) { l = Math.max(l, book); r += book; }

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (countStudents(arr, mid) <= k) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(allocatePages(new int[]{12, 34, 67, 90}, 2)); // 113
        System.out.println(allocatePages(new int[]{15, 17, 20}, 5));     // -1
    }
}
