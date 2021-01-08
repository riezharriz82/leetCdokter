/**
 * https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 * <p>
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 * <p>
 * A string is represented by an array if the array elements concatenated in order forms the string.
 * <p>
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation:
 * word1 represents string "ab" + "c" -> "abc"
 * word2 represents string "a" + "bc" -> "abc"
 * The strings are the same, so return true.
 * <p>
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 * <p>
 * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * Output: true
 * <p>
 * Constraints:
 * 1 <= word1.length, word2.length <= 103
 * 1 <= word1[i].length, word2[i].length <= 103
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
 * word1[i] and word2[i] consist of lowercase letters.
 */
public class CheckIfTwoStringArraysAreEquivalent {
    /**
     * Approach: Two pointer solution
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int w1 = 0, w2 = 0; //outer word index
        int p1 = 0, p2 = 0; //inner character index
        while (w1 < word1.length && w2 < word2.length) {
            char c1 = word1[w1].charAt(p1);
            char c2 = word2[w2].charAt(p2);
            if (c1 != c2) {
                return false;
            }
            if (p1 == word1[w1].length() - 1) {
                w1++; //move to next word, if all characters of current word has been verified
                p1 = 0; //reset the index to 0
            } else {
                p1++;
            }
            if (p2 == word2[w2].length() - 1) {
                w2++;
                p2 = 0;
            } else {
                p2++;
            }
        }
        return w1 == word1.length && w2 == word2.length; //verify whether all the words have been matched or not
    }
}
