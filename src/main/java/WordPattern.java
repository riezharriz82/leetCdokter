import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/word-pattern/
 * <p>
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * <p>
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 */
public class WordPattern {
    /**
     * {@link IsomorphicStrings} is very similar to this problem statement
     * Keep track of the mapping from char in pattern -> string in str & string in str -> char in pattern
     */
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> sourceToTarget = new HashMap<>();
        Map<String, Character> targetToSource = new HashMap<>();
        String[] splits = str.split(" ");
        if (pattern.length() != splits.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char source = pattern.charAt(i);
            String target = splits[i];
            String expectedTarget = sourceToTarget.get(source);
            Character expectedSource = targetToSource.get(target);
            if (expectedTarget == null && expectedSource == null) {
                sourceToTarget.put(source, target);
                targetToSource.put(target, source);
            } else if (!target.equals(expectedTarget) || expectedSource != source) {
                return false;
            }
        }
        return true;
    }
}
