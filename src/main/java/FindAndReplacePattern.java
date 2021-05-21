import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * https://leetcode.com/problems/find-and-replace-pattern/
 *
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.
 *
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
 *
 * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.
 *
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
 *
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 *
 * Constraints:
 * 1 <= pattern.length <= 20
 * 1 <= words.length <= 50
 * words[i].length == pattern.length
 * pattern and words[i] are lowercase English letters.
 * </pre>
 */
public class FindAndReplacePattern {
    /**
     * Approach: Keep track of mapping of characters in source string to target string and from target string to source string as no two letters can map to same letter
     * In case the mapping is already assigned and different than expected, the string does not matches the provided pattern.
     * <p>
     * {@link WordPattern} {@link IsomorphicStrings} {@link FindAndReplaceInString}
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();
        for (String source : words) {
            if (pattern.length() == source.length()) {
                int[] sourceToTargetMapping = new int[26];
                int[] targetToSourceMapping = new int[26];
                Arrays.fill(sourceToTargetMapping, -1);
                Arrays.fill(targetToSourceMapping, -1);
                boolean isInvalid = false;
                for (int i = 0; i < pattern.length(); i++) {
                    int src = source.charAt(i) - 'a';
                    int target = pattern.charAt(i) - 'a';
                    if (sourceToTargetMapping[src] == -1 && targetToSourceMapping[target] == -1) { //character not yet mapped
                        sourceToTargetMapping[src] = target;
                        targetToSourceMapping[target] = src;
                    } else if (sourceToTargetMapping[src] != target || targetToSourceMapping[target] != src) {
                        isInvalid = true;
                        break;
                    }
                }
                if (!isInvalid) {
                    result.add(source);
                }
            }
        }
        return result;
    }
}
