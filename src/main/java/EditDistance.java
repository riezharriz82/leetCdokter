import java.util.Arrays;

/**
 * https://leetcode.com/problems/edit-distance/
 * <p>
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 */
public class EditDistance {
    /**
     * Classical DP problem, I am really proud of myself solving it in top down approach on my own, without coding up the mugged up bottom up approach
     * {@link DeleteOperationForTwoString} for similar problem
     */
    public int minDistance(String word1, String word2) {
        int[][] memoized = new int[word1.length()][word2.length()];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        return recur(word1, word2, word1.length() - 1, word2.length() - 1, memoized);
    }

    private int recur(String word1, String word2, int idx1, int idx2, int[][] memoized) {
        if (idx1 < 0 && idx2 < 0) { //both word1 and word2 are done
            return 0;
        } else if (idx1 < 0 && idx2 >= 0) { //if word1 is done and word2 is left
            return idx2 + 1;
        } else if (idx2 < 0 && idx1 >= 0) { //if word2 is done and word1 is left
            return idx1 + 1;
        } else if (memoized[idx1][idx2] != -1) {
            return memoized[idx1][idx2];
        } else if (word1.charAt(idx1) == word2.charAt(idx2)) { //characters match, skip this index
            return memoized[idx1][idx2] = recur(word1, word2, idx1 - 1, idx2 - 1, memoized);
        } else {
            //either delete character at idx1 or at idx2
            int deleteChar = 1 + Math.min(recur(word1, word2, idx1 - 1, idx2, memoized), recur(word1, word2, idx1, idx2 - 1, memoized));
            //replace char at idx1 to char at idx2 and vice versa
            int replaceChar = 1 + recur(word1, word2, idx1 - 1, idx2 - 1, memoized);
            //insert character at idx1 or at idx2
            int insertChar = 1 + Math.min(recur(word1, word2, idx1, idx2 - 1, memoized), recur(word1, word2, idx1 - 1, idx2, memoized));
            return memoized[idx1][idx2] = Math.min(deleteChar, Math.min(replaceChar, insertChar));
        }
    }
}
