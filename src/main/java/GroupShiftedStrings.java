import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/group-shifted-strings/
 * <p>
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
 * <p>
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 * <p>
 * Example:
 * <p>
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Output:
 * [
 * ["abc","bcd","xyz"],
 * ["az","ba"],
 * ["acef"],
 * ["a","z"]
 * ]
 */
public class GroupShiftedStrings {
    /**
     * Approach: Google interview question. Initially I did it in n^2, by comparing the diff between the first characters of two strings.
     * <p>
     * It can be optimized if we generate the key of the current string, and append the current string to the results already present for the current key
     * <p>
     * There are multiple ways of generating keys, take the difference of each character with the first character char[1] - char[0], char[2] - char[0]
     * or take the difference between character at adjacent indices char[1] - char[0], char[2] - char[1], char[3] - char[2]
     */
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<List<Integer>, List<String>> map = new HashMap<>();
        for (String string : strings) {
            List<Integer> currentKey = new ArrayList<>();
            for (int j = 0; j < string.length(); j++) {
                int offset = string.charAt(j) - string.charAt(0);
                if (offset < 0) { //modulo is a bitch, take care of negative diff az -> za
                    offset += 26;
                }
                currentKey.add(offset);
            }
            map.computeIfAbsent(currentKey, __ -> new ArrayList<>()).add(string);
        }
        map.forEach((k, v) -> res.add(v));
        return res;
    }
}
