import java.util.HashMap;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "paper", t = "title"
 * Output: true
 */
public class IsomorphicStrings {
    //Critical part of the problem is that all occurrence of a character must map to the same character
    //In my initial approach, i did not consider that e.g {egge, addf} should return false
    public boolean isIsomorphic(String s, String t) {
        //store the mapping of characters from input to target string
        HashMap<Character, Character> map = new HashMap<>();
        //no two characters may map to the same character, so need to track who has mapped this character
        HashMap<Character, Character> reverseMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char source = s.charAt(i);
            char target = t.charAt(i);
            if (map.containsKey(source) && map.get(source) != target) { // if source is mapped to a different target
                return false;
            } else if (reverseMap.containsKey(target) && reverseMap.get(target) != source) { //if target is mapped to a different source
                return false;
            } else {
                reverseMap.put(target, source);
                map.put(source, target);
            }
        }
        return true;
    }
}
