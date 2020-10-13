import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-window-subsequence/
 * <p>
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the left-most starting index.
 * <p>
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 */
public class MinimumWindowSubsequence {
    /**
     * Approach: Initially I tried to implement it similar to {@link MinimumWindowSubstring}
     * Find the max window with all characters of target and check if target is a subsequence of window, if yes update the result
     * otherwise keep decrementing the window until the window is invalid.
     * That approach caused TLE for larger inputs because we were first finding a substring window and then checking whether the window is subsequence or not.
     * <p>
     * The correct solution would be to directly find a valid window ie. a window in S that has T as a subsequence.
     * Now to find the shortest valid window in it, iterate from the reverse to find the shortest valid window
     */
    public String minSubsequenceUsingSlidingWindow(String S, String T) {
        int end = 0, start;
        int minLength = Integer.MAX_VALUE;
        int resultStart = -1;
        while (end < S.length()) {
            int t_index = 0;
            //find the largest window with T as a subsequence
            while (end < S.length() && t_index < T.length()) {
                if (S.charAt(end) == T.charAt(t_index)) {
                    t_index++;
                }
                end++;
            }
            if (end == S.length() && t_index != T.length()) {
                //if we couldn't find a valid window
                break;
            }
            end--; //because we overshot by 1
            start = end; //now iterate from the reverse to find the first window (shortest) with T as a subsequence
            //eg. bbbade, bde -> initially we will get bbbade as the larget window
            //iterating from the reverse we will get bade
            t_index = T.length() - 1;
            while (start >= 0 && t_index >= 0) {
                if (S.charAt(start) == T.charAt(t_index)) {
                    t_index--;
                }
                start--;
            }
            start++; //because we overshot by 1
            if (end - start + 1 < minLength) {
                minLength = end - start + 1;
                resultStart = start;
            }
            end = start + 1; //tricky next lookup should start from start+1 not from end+1, so that we don't skip partial results in current window
            //e.g. acxxxahcxxh, ach
        }
        return resultStart == -1 ? "" : S.substring(resultStart, resultStart + minLength);
    }

    /**
     * Approach: Keep track of all indices in which a specific character occurred.
     * e.g. abcdebdde
     * b = 1,5
     * d = 3,6,7
     * e = 4,8
     * Now to find a valid subsequence, we would need to find 3 indices such that i < j < k i.e one valid example would be 1,3,4
     * So we have to find an index > prev_index, we can use binary search to do that since indices are sorted
     * <p>
     * I thought of this solution during my initial thought process but couldn't implement how to find such indices. I was thinking of
     * a priority queue solution similar to merge k sorted lists but then thought it would be an overkill.
     * This recursive solution is not fast but pretty cool.
     */
    public String minSubsequenceByStoringIndices(String S, String T) {
        List<List<Integer>> lst = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            lst.add(new ArrayList<>());
        }
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            lst.get(c - 'a').add(i);
        }
        int minLength = Integer.MAX_VALUE, resultStart = -1;
        for (Integer curIndex : lst.get(T.charAt(0) - 'a')) { //start index that has the first character of T
            int end = recur(lst, curIndex, 1, T);
            //find a valid end index that has the last character of T, such that all intermediate indices are strictly increasing
            if (end != -1 && end - curIndex + 1 < minLength) {
                minLength = end - curIndex + 1;
                resultStart = curIndex;
            }
        }
        return resultStart == -1 ? "" : S.substring(resultStart, resultStart + minLength);
    }

    private int recur(List<List<Integer>> lst, Integer prevIndex, int target_index, String T) {
        if (target_index == T.length()) {
            //a valid end index has been found, return it
            return prevIndex;
        }
        List<Integer> currentIndices = lst.get(T.charAt(target_index) - 'a');
        int greaterIndex = Collections.binarySearch(currentIndices, prevIndex + 1);
        //we are interested in indices greater than prev index
        //there is no adhoc greater api, hence did a +1 on prevIndex
        if (greaterIndex < 0) {
            greaterIndex *= -1;
            greaterIndex--;
        }
        if (greaterIndex == currentIndices.size()) { //in case prevIndex was the largest value and we could not find any index greater than prevIndex
            return -1;
        }
        return recur(lst, currentIndices.get(greaterIndex), target_index + 1, T);
    }
}
