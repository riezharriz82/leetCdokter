import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 * https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/
 *
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 *
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string.
 * However, you must split the substrings such that all of them are unique.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 *
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 *
 * Input: s = "aa"
 * Output: 1
 * Explanation: It is impossible to split the string any further.
 *
 * Constraints:
 * 1 <= s.length <= 16
 * s contains only lower case English letters.
 * </pre>
 */
public class SplitAStringIntoMaxNumberOfUniqueSubstrings {
    /**
     * Approach: Backtracking. Got the hint from input constraints :D
     * Maintain a set of current substrings and try cutting at each index if the current cut yields a distinct substring.
     * Remove the current cut and make a cut at another index, keeping track of max cuts formed so far.
     * <p>
     * Alternatively we can leverage bit magic to generate all permutations from 1 to 2^n-1
     * https://federico-feresini.medium.com/split-a-string-into-the-max-number-of-unique-substrings-algorithms-visualizations-6116561b7fc6
     * <p>
     * {@link Subsets} {@link SplitAStringInBalancedStrings} {@link NumberOfWaysToSplitAString} {@link NumberOfGoodWaysToSplitString}
     */
    public int maxUniqueSplit(String s) {
        return recur(s, 0, new HashSet<>());
    }

    private int recur(String s, int index, Set<String> set) {
        if (index == s.length()) {
            return 0;
        }
        String cur = "";
        int maxSplits = 0;
        for (int i = index; i < s.length(); i++) {
            cur += s.charAt(i);
            if (!set.contains(cur)) {
                set.add(cur);
                int remainingSplits = recur(s, i + 1, set);
                maxSplits = Math.max(maxSplits, 1 + remainingSplits);
                set.remove(cur);
            }
        }
        return maxSplits;
    }
}
