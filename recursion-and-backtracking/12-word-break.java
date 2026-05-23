/*
 * Word Break (print all ways)
 *
 * Return list of all sentences from start onward; base case start==n returns
 * [""]. Inner loop end=start+1..n: if s[start:end] is a word, prepend it to
 * each suffix returned by recursion (word + " " + suffix, skip space if
 * suffix is empty). Caller just collects the returned list — no accumulator,
 * no undo.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class WordBreak {
    public static List<String> wordBreak(String s, Set<String> wordDict) {
        return solve(s, wordDict, 0);
    }

    private static List<String> solve(String s, Set<String> dict, int start) {
        List<String> result = new ArrayList<>();
        if (start == s.length()) {
            result.add("");
            return result;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (!dict.contains(word)) continue;
            List<String> suffixes = solve(s, dict, end);
            for (String suffix : suffixes) {
                result.add(suffix.isEmpty() ? word : word + " " + suffix);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Set<String> dict = new java.util.HashSet<>(
                java.util.Arrays.asList("cat", "cats", "and", "sand", "dog"));
        System.out.println(wordBreak("catsanddog", dict));
        // [cats and dog, cat sand dog]
    }
}
