package alternate;

import java.util.HashSet;
import java.util.Set;

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
     * Keep all unique words in a set and for each candidate check if it's a rotation of a word present in the set.
     * If not we found a new rotation group.
     */
    public int solve(String[] words) {
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            if (uniqueWords.isEmpty()) {
                uniqueWords.add(word);
            } else {
                boolean isRotation = false;
                String candidate = word + word;
                for (String uniqueWord : uniqueWords) { //check every unique rotation group
                    if (uniqueWord.length() == word.length() && candidate.contains(uniqueWord)) {
                        //part of existing rotation group
                        isRotation = true;
                        break;
                    }
                }
                if (!isRotation) {
                    //new rotation group found
                    uniqueWords.add(word);
                }
            }
        }
        return uniqueWords.size();
    }
}
