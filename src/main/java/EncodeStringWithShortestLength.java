import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/encode-string-with-shortest-length/
 * <p>
 * Given a non-empty string, encode the string such that its encoded length is the shortest.
 * <p>
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * <p>
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them.
 * <p>
 * Input: s = "aaa"
 * Output: "aaa"
 * Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 * Example 2:
 * <p>
 * Input: s = "aaaaa"
 * Output: "5[a]"
 * Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 * Example 3:
 * <p>
 * Input: s = "aaaaaaaaaa"
 * Output: "10[a]"
 * Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 * Example 4:
 * <p>
 * Input: s = "aabcaabcd"
 * Output: "2[aabc]d"
 * Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
 * Example 5:
 * <p>
 * Input: s = "abbbabbbcabbbabbbc"
 * Output: "2[2[abbb]c]"
 * Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".
 */
public class EncodeStringWithShortestLength {
    /**
     * Approach: Very tricky problem but top down recursion made it so easier.
     * Tried very hard to do it on my own but was not able to think of a recursive solution.
     * My mind was thinking of solving it via stack or something. But that was the wrong direction.
     * <p>
     * Given a string, try to see whether it's composed of repeated pattern i.e. abcabcabc. If yes then result will be 3 + recur(abc)
     * If no repeated pattern possible, then make cuts at all possible places and see which leads to a shortest encoded string
     * e.g. abcabcd
     * recur(a) + recur(bcabcd) = abcabcd
     * recur(ab) + recur(cabcd) = abcabcd
     * recur(abc) + recur(abcd) = abcabcd
     * recur(abca) + recur(bcd) = abcabcd
     * recur(abcab) + recur(cd) = abcabcd
     * recur(abcabc) + recur(d) = 2[abc]d
     * <p>
     * Related problems {@link RepeatedSubstringPattern} {@link DecodeString}
     */
    public String encode(String s) {
        Map<String, String> memoized = new HashMap<>();
        return recur(s, memoized);
    }

    private String recur(String str, Map<String, String> memoized) {
        if (str.length() <= 4) {
            return str;
        }
        String val = memoized.get(str);
        if (val != null) {
            return val;
        }
        int repeatAtIndex = isStringRepeated(str);
        if (repeatAtIndex != 0) {
            //if the string is composed of repeated pattern, recur for the repeated pattern i.e. recur for {abc} in str = {abcabc}
            int timesRepeated = str.length() / repeatAtIndex;
            String encodeRepeatedPattern = recur(str.substring(0, repeatAtIndex), memoized);
            String candidate = timesRepeated + "[" + encodeRepeatedPattern + "]";
            memoized.put(str, candidate);
            return candidate;
        } else {
            String minCandidate = null;
            for (int i = 1; i < str.length(); i++) { //make cuts at all intermediate places
                String encodedPrefix = recur(str.substring(0, i), memoized);
                String encodedSuffix = recur(str.substring(i), memoized);
                String candidate = encodedPrefix + encodedSuffix;
                if (minCandidate == null) {
                    minCandidate = candidate;
                } else if (minCandidate.length() > candidate.length()) {
                    minCandidate = candidate;
                }
            }
            memoized.put(str, minCandidate);
            return minCandidate;
        }
    }

    private int isStringRepeated(String str) {
        String concat = str + str;
        return concat.substring(1, concat.length() - 1).indexOf(str) + 1; //shift by +1 because we are looking in the trimmed substring from index 1
    }
}
