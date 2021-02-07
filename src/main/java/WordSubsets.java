import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-subsets/
 * <p>
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 * <p>
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 * <p>
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 * <p>
 * Return a list of all universal words in A.  You can return the words in any order.
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 * Output: ["apple","google","leetcode"]
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * Output: ["facebook","google"]
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * Output: ["google","leetcode"]
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 * <p>
 * Note:
 * 1 <= A.length, B.length <= 10000
 * 1 <= A[i].length, B[i].length <= 10
 * A[i] and B[i] consist only of lowercase letters.
 * All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 */
public class WordSubsets {
    /**
     * Approach: Reduce all the words of B to a single word by keeping track of max character required
     * If a word in A[] has at least the required frequency of each character, it can be part of universal set
     * <p>
     * {@link MinimumDeletionsToMakeStringBalanced} {@link MinimumLengthOfStringAfterDeletingSimilarEnds}
     */
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] required = new int[26];
        for (String b : B) {
            int[] cur = new int[26];
            for (char c : b.toCharArray()) {
                cur[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                required[i] = Math.max(required[i], cur[i]); //keep track of max frequency required for each character
            }
        }
        List<String> valid = new ArrayList<>();
        for (String a : A) {
            int[] cur = new int[26];
            for (char c : a.toCharArray()) {
                cur[c - 'a']++;
            }
            boolean isInvalid = false;
            for (int i = 0; i < 26; i++) {
                if (cur[i] < required[i]) { //if current word has less characters than required, it's an invalid word
                    isInvalid = true;
                    break;
                }
            }
            if (!isInvalid) {
                valid.add(a);
            }
        }
        return valid;
    }
}
