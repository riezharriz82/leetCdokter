import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-common-characters/
 * <p>
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).
 * For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
 * <p>
 * You may return the answer in any order.
 * <p>
 * Input: ["bella","label","roller"]
 * Output: ["e","l","l"]
 * <p>
 * Input: ["cool","lock","cook"]
 * Output: ["c","o"]
 * <p>
 * Note:
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] is a lowercase letter
 */
public class FindCommonCharacters {
    /**
     * Approach: Need to find the intersection of frequency of characters of each string. No implicit way of performing map intersection
     * in java, hence kept track of minimum freq of characters of each string.
     * <p>
     * In my initial implementation, I used a map<character, TreeSet<Integer>> and used the min value of each character by using first()
     * It was an overkill
     */
    public List<String> commonChars(String[] A) {
        int[] freq = new int[26];
        Arrays.fill(freq, Integer.MAX_VALUE);
        for (String str : A) {
            int[] temp = new int[26];
            for (char c : str.toCharArray()) {
                temp[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                freq[i] = Math.min(freq[i], temp[i]); //critical step that ensures we keep track of the lowest common frequency
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < freq[i]; j++) { //in the end we will be left with the lowest frequency of each character, just repeat it
                res.add(Character.toString((char) ('a' + i))); //instead of "" + ('a'+i)
            }
        }
        return res;
    }
}