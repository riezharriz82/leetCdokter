import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-duplicate-substring/
 * <p>
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
 * <p>
 * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".
 * <p>
 * Input: s = "banana"
 * Output: "ana"
 * <p>
 * Input: s = "abcd"
 * Output: ""
 * <p>
 * Constraints:
 * 2 <= s.length <= 3 * 104
 * s consists of lowercase English letters.
 */
public class LongestDuplicateSubstring {
    /**
     * Approach: Binary search + Rabin Karp Rolling Hash + Tricky modular arithmetic
     * <p>
     * If we directly put all the substrings of a specific length in a hashset, we would get MemoryLimitExceeded
     * During my thought process of implementing rolling hash, I thought in case of conflicts, I would have to put the original string
     * in the hash as well, that would also give MLE, but I was wrong. We could simply keep the index of the original string
     * and reduce our memory footprint.
     * <p>
     * We could have also applied {@link LongestCommonSubarray} to this problem, but it would be n^2 and would time out
     * <p>
     * Similar problem with relaxed bounds. Got AC without using rabin karp rolling hash https://leetcode.com/problems/longest-repeating-substring/
     * <p>
     * {@link KokoEatingBananas} {@link DivideChocolates} related binary search problems
     * {@link RepeatedDNASequence} related rolling hash problem
     * {@link MakeSumDivisibleByP} related modular arithmetic problem
     */
    public String longestDupSubstring(String s) {
        int low = 1, high = s.length() - 1;
        String ans = null;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            String currentDuplicateSubstring = inputContainsDuplicateSubstringOfLength(mid, s);
            if (currentDuplicateSubstring != null) { //try a even higher substring length to get a possibly better result
                ans = currentDuplicateSubstring;
                low = mid + 1;
            } else { //try a lower substring length to get a valid result
                high = mid - 1;
            }
        }
        return ans == null ? "" : ans;
    }

    private String inputContainsDuplicateSubstringOfLength(int targetLength, String input) {
        Map<Long, List<Integer>> map = new HashMap<>(); //map of hash to list of indices
        long hash = 0;
        int mod = Integer.MAX_VALUE;
        for (int i = 0; i < targetLength; i++) {
            hash = (hash * 26 + (input.charAt(i) - 'a')) % mod;
        }
        map.computeIfAbsent(hash, __ -> new ArrayList<>()).add(0);
        long offset = 1L; //needs to be long to handle overflow and avoid negative offsets
        for (int i = 0; i < targetLength; i++) {
            offset = (26 * offset) % mod;
        }
        for (int i = targetLength; i < input.length(); i++) {
            int begin = i - targetLength;
            //remove the contribution of character that's sliding out and add the contribution of character that's sliding in
            hash = (hash * 26 - (input.charAt(begin) - 'a') * offset + (input.charAt(i) - 'a')) % mod;
            hash = (hash + mod) % mod; //very tricky part to handle negative mods
            if (map.containsKey(hash)) {
                //in case of conflicts, iterate through the list of indices to verify whether duplicate actually exists
                String candidate = input.substring(begin + 1, i + 1);
                for (int index : map.get(hash)) {
                    String key = input.substring(index, index + targetLength);
                    if (key.equals(candidate)) {
                        return candidate;
                    }
                }
            }
            map.computeIfAbsent(hash, __ -> new ArrayList<>()).add(i - targetLength + 1);
        }
        return null;
    }
}