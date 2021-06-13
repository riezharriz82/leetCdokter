/**
 * <pre>
 * https://leetcode.com/problems/maximum-number-of-removable-characters/
 *
 * You are given two strings s and p where p is a subsequence of s. You are also given a distinct 0-indexed integer array removable containing a subset of indices of s (s is also 0-indexed).
 *
 * You want to choose an integer k (0 <= k <= removable.length) such that, after removing k characters from s using the first k indices in removable, p is still a subsequence of s.
 * More formally, you will mark the character at s[removable[i]] for each 0 <= i < k, then remove all marked characters and check if p is still a subsequence.
 *
 * Return the maximum k you can choose such that p is still a subsequence of s after the removals.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * Input: s = "abcacb", p = "ab", removable = [3,1,0]
 * Output: 2
 * Explanation: After removing the characters at indices 3 and 1, "abcacb" becomes "accb".
 * "ab" is a subsequence of "accb".
 * If we remove the characters at indices 3, 1, and 0, "abcacb" becomes "ccb", and "ab" is no longer a subsequence.
 * Hence, the maximum k is 2.
 *
 * Input: s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
 * Output: 1
 * Explanation: After removing the character at index 3, "abcbddddd" becomes "abcddddd".
 * "abcd" is a subsequence of "abcddddd".
 *
 * Input: s = "abcab", p = "abc", removable = [0,1,2,3,4]
 * Output: 0
 * Explanation: If you remove the first index in the array removable, "abc" is no longer a subsequence.
 * </pre>
 */
public class MaximumNumberOfRemovableCharacters {
    /**
     * Approach: Binary Search, Perform binary search on the result on the target space of [0, removable.length-1]
     * After choosing the mid, delete those characters in source string and then check whether the source is still a subsequence of target string
     * <p>
     * In my original solution, I performed deletion by keeping a set of removal indices and then generating a new string which does not contain chars
     * from those indices. It got AC but time was very high ~500 ms compared to ~70 ms by just setting the chars at removable indices to '?'
     * <p>
     * Still very happy to solve this question in the contest
     * <p>
     * {@link LongestCommonSubarray} {@link IsSubsequence}
     */
    public int maximumRemovals(String s, String p, int[] removable) {
        int low = 0, high = removable.length - 1, ans = 0;
        char[] source = s.toCharArray();
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(mid, s, p, source, removable)) {
                ans = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean isValid(int high, String s, String p, char[] source, int[] remove) {
        for (int i = 0; i <= high; i++) {
            source[remove[i]] = '/'; //simulate deleting the char at removable indexes
        }
        boolean res = isSubsequence(source, p);
        if (!res) {
            //fill back the original characters of the source, as we have to perform binary search again
            for (int i = 0; i <= high; i++) {
                source[remove[i]] = s.charAt(remove[i]);
            }
        }
        return res;
    }

    public boolean isSubsequence(char[] source, String candidate) {
        if (candidate.isEmpty()) {
            return true;
        }
        if (candidate.length() > source.length) {
            return false;
        }
        int candidate_index = 0, source_index = 0;
        while (candidate_index < candidate.length() && source_index < source.length) {
            if (candidate.charAt(candidate_index) == source[source_index]) {
                //if both the characters match, increment the indexes of both the strings
                candidate_index++;
                source_index++;
            } else {
                //keep looking at the next index of the source
                source_index++;
            }
        }
        return (candidate_index == candidate.length()); //check whether all the characters of candidate has been found in the source
    }
}
