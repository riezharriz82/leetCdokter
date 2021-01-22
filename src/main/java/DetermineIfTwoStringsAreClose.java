import java.util.Arrays;

/**
 * https://leetcode.com/problems/determine-if-two-strings-are-close/
 * <p>
 * Two strings are considered close if you can attain one from the other using the following operations:
 * <p>
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 * <p>
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 * <p>
 * Input: word1 = "abc", word2 = "bca"
 * Output: true
 * Explanation: You can attain word2 from word1 in 2 operations.
 * Apply Operation 1: "abc" -> "acb"
 * Apply Operation 1: "acb" -> "bca"
 * <p>
 * Input: word1 = "a", word2 = "aa"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
 * <p>
 * Input: word1 = "cabbba", word2 = "abbccc"
 * Output: true
 * Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb"
 * Apply Operation 2: "caabbb" -> "baaccc"
 * Apply Operation 2: "baaccc" -> "abbccc"
 * <p>
 * Input: word1 = "cabbba", word2 = "aabbss"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any amount of operations.
 * <p>
 * Constraints:
 * 1 <= word1.length, word2.length <= 105
 * word1 and word2 contain only lowercase English letters.
 */
public class DetermineIfTwoStringsAreClose {
    /**
     * Approach: Greedy, Operation1 will make two strings similar only if they are anagram of each other
     * Operation2 will make two strings similar only if their frequencies are anagram of each other
     * ie. if freq1 = {a=2,b=3,c=4} and freq2 = {a=3,b=2,c=4}
     * <p>
     * Was able to solve this on my own after solving some examples on paper
     * <p>
     * {@link FindTheMostCompetitiveSubsequence} {@link OneEditDistance} related string problems
     */
    public boolean closeStrings(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (char c : word1.toCharArray()) {
            cnt1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            cnt2[c - 'a']++;
        }
        //Need to ensure that both strings have the same characters ie. if 'a' is present in word1 but absent in word2
        //word1 and word2 can never be similar, missed this during my initial implementation
        for (int i = 0; i < 26; i++) {
            if ((cnt1[i] != 0 && cnt2[i] == 0) || (cnt2[i] != 0 && cnt1[i] == 0)) {
                return false;
            }
        }
        //this step finds whether word1 and word2 are anagram of each other, if yes perform operation1
        boolean isAnagram = true;
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] != cnt2[i]) {
                isAnagram = false;
                break;
            }
        }
        if (isAnagram) {
            return true;
        } else {
            //now check if frequencies are anagram of each other or not, if yes, perform operation2
            //sorting is O(1) because size of array is 26
            Arrays.sort(cnt1);
            Arrays.sort(cnt2);
            return Arrays.equals(cnt1, cnt2);
        }
    }
}
