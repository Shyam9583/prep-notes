/*
 * Extended Boyer-Moore with a map: keep at most 2 candidates. When map size
 * exceeds 2, decrement all counts and delete zeroes (cancellation step). Then
 * recount candidates in a second pass to verify they actually exceed n/3.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MajorityElementN3 {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
            if (map.size() > 2) {
                map.replaceAll((k, v) -> v - 1);
                map.values().removeIf(v -> v == 0);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int candidate : map.keySet()) {
            int cnt = 0;
            for (int num : nums) if (num == candidate) cnt++;
            if (cnt > nums.length / 3) result.add(candidate);
        }
        return result;
    }
}
