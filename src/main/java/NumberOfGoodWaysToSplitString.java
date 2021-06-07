import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
 * <p>
 * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q
 * where its concatenation is equal to s and the number of distinct letters in p and q are the same.
 * <p>
 * Return the number of good splits you can make in s.
 * <p>
 * Input: s = "aacaba"
 * Output: 2
 * <p>
 * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 * <p>
 * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 * <p>
 * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * <p>
 * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * <p>
 * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 */
public class NumberOfGoodWaysToSplitString {
    /**
     * Approach: Two pass algorithm by maintaining a left/right array. Solve it similar to solving non-overlapping problems by
     * maintaining two arrays tracking count of distinct characters found at each index from left and right direction.
     * <p>
     * {@link NumberOfWaysToSplitAString} {@link SplitAStringIntoMaxNumberOfUniqueSubstrings} {@link MaximumNumberOfNonOverlappingSubarraysWithTargetSum}
     */
    public int numSplits(String s) {
        int size = s.length();
        int[] leftDistinct = new int[size]; //for each index, find number of distinct characters found till index i
        int[] rightDistinct = new int[size]; //for each index, find number of distinct characters starting from index i

        Set<Character> set = new HashSet<>(); //can also use array of 26 size
        for (int i = 0; i < size; i++) {
            set.add(s.charAt(i));
            leftDistinct[i] = set.size();
        }
        set.clear();
        for (int i = size - 1; i >= 0; i--) {
            set.add(s.charAt(i));
            rightDistinct[i] = set.size();
        }
        int res = 0;
        for (int i = 0; i < size - 1; i++) {
            //if number of distinct characters ending at i is equal to number of distinct character starting from i + 1, we have found a good split
            if (leftDistinct[i] == rightDistinct[i + 1]) {
                res++;
            }
        }
        return res;
    }
}
