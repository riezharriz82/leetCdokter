import java.util.Arrays;

/**
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 * <p>
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same,
 * where in each step you can delete one character in either string.
 * <p>
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 */
public class DeleteOperationForTwoString {
    /**
     * Approach: Really happy to solve this question using top down memoization on my own.
     * Leveraged prior learnings from solving similar question {@link LongestCommonSubsequence} {@link LongestPalindromicSubsequence}
     * <p>
     * 3 cases can be formulated:
     * 1. If characters at idx1 and idx2 mismatch, then we have two options, either delete one character at idx1 or idx2
     * or delete both of the characters at idx1 or idx2
     * <p>
     * 2. If characters at idx1 and idx2 matches, do nothing and skip to idx1-1, idx2-1
     * <p>
     * 3. In case we are done with word1 (idx1<0) but word2 isn't done (idx2>0), only option is to delete all the characters remaining in idx2.
     * <p>
     * Another way of solving this problem would be to compute LongestCommonSubsequence of word1 and word2. Answer would be m + n - 2 * lcs
     */
    public int minDistance(String word1, String word2) {
        int[][] memoized = new int[word1.length()][word2.length()];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        return recur(word1, word2, word1.length() - 1, word2.length() - 1, memoized);
    }

    private int recur(String word1, String word2, int idx1, int idx2, int[][] memoized) {
        if (idx1 < 0 && idx2 >= 0) { //word1 is done but word2 isn't
            return idx2 + 1;
        } else if (idx2 < 0 && idx1 >= 0) { //word2 is done but word1 isn't
            return idx1 + 1;
        } else if (idx1 < 0 && idx2 < 0) { //both words are done
            return 0;
        } else if (memoized[idx1][idx2] != 0) {
            return memoized[idx1][idx2];
        } else if (word1.charAt(idx1) == word2.charAt(idx2)) { //characters match, do nothing and skip to next character
            return memoized[idx1][idx2] = recur(word1, word2, idx1 - 1, idx2 - 1, memoized);
        } else {
            int deleteOneChar = 1 + Math.min(recur(word1, word2, idx1 - 1, idx2, memoized), recur(word1, word2, idx1, idx2 - 1, memoized));
            int deleteTwoChars = 2 + recur(word1, word2, idx1 - 1, idx2 - 1, memoized);
            return memoized[idx1][idx2] = Math.min(deleteOneChar, deleteTwoChars);
        }
    }
}
