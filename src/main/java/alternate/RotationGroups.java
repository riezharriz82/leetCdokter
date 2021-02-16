package alternate;

import java.util.*;

/**
 * https://binarysearch.io/problems/Rotation-Groups
 * <p>
 * A rotation group for a string contains all of its unique rotations. For example, "123" can be rotated to "231" and "312" and they are all in the same rotation group.
 * <p>
 * Given a list of strings words, group each word by their rotation group, and return the total number of groups.
 * Input words = ["abc", "xy", "yx", "z", "bca"]
 * Output 3
 * <p>
 * There are three rotation groups:
 * ["abc", "bca"]
 * ["xy", "yx"]
 * ["z"]
 */
public class RotationGroups {
    /**
     * This problem is similar to {@see GroupAnagrams}
     * A string s1 is a rotation of another string s2 if (s1 + s1).contains(s2)
     * and s1.length == s2.length
     * Maintain mapping of sortedWord to actual word, for each word, check if the sorted word exists in the map or not
     * If no, we definitely found a new rotation group
     * If yes, check for each actual word associated with the sorted key, and check if the current word is a rotation of that word or not
     * <p>
     * Pattern for solving "group" string problems is to find the unique identifier for each string and try to maintain a
     * mapping for group key -> list of candidate strings
     */
    public int solve(String[] words) {
        Map<String, Set<String>> sortedKeys = new HashMap<>();
        int groups = 0;
        for (String word : words) {
            char[] c = word.toCharArray();
            Arrays.sort(c);
            String sortedKey = new String(c);
            if (!sortedKeys.containsKey(sortedKey)) {
                groups++;
                sortedKeys.computeIfAbsent(sortedKey, __ -> new HashSet<>()).add(word);
            } else {
                Set<String> candidates = sortedKeys.get(sortedKey);
                boolean isRotation = false;
                for (String candidate : candidates) { //for each word associated with the sorted key, check if the current word is a rotation of candidate word
                    if (candidate.length() == word.length() && (candidate + candidate).contains(word)) {
                        isRotation = true;
                        break;
                    }
                }
                if (!isRotation) {
                    groups++;
                    candidates.add(word);
                }
            }
        }
        return groups;
    }
}
