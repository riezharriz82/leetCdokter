/**
 * https://leetcode.com/problems/minimum-window-substring/
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    /**
     * Approach: Sliding window, keep increasing the window until the current window has all the characters required to make target string.
     * Once such window is found, keep reducing the window until the current window is invalid i.e. current char count becomes < target char count
     * Keep track of min valid window found.
     * <p>
     * In my initial implementation I created two count array one for source and another for target to keep track of whether that character is required or not
     * If that character was required, curCount needs to be increased/decreased accordingly. It complicated the code.
     * <p>
     * Simple thing would have been to only consider character whose count is +ve, then decrement/increment the curCount
     * <p>
     * {@link FindAllAnagramInString} {@link MinimumSizeSubarraySum} for related sliding window problem
     */
    public String minWindow(String s, String t) {
        int[] count = new int[256];
        for (char c : t.toCharArray()) {
            count[c]++;
        }
        int begin = 0, end = 0, curCount = 0;
        int windowStart = 0, windowEnd = Integer.MAX_VALUE;
        while (end < s.length()) {
            if (count[s.charAt(end)] > 0) { // tricky thing, consider only characters with positive count as it denotes a required character
                curCount++;
            }
            count[s.charAt(end)]--; // tricky thing, decrement the count even if it was not a required character
            //count will become -ve, does not matter because we only interested in +ve count
            while (curCount == t.length()) { //once a valid window is reached, start shrinking the window from start in order to get the minimum valid window
                if (end - begin < windowEnd - windowStart) {
                    //track minimum valid window
                    windowStart = begin;
                    windowEnd = end;
                }
                count[s.charAt(begin)]++; //tricky thing, increase the count irrespective of whether that character was required
                if (count[s.charAt(begin)] > 0) { //tricky thing, if the count becomes +ve, this means that the character was required and we removed
                    //this things also takes care of the situations when we have more characters than required
                    //eg {BXBAC}, {ABC} when the window is BXBAC, count if b is -1, when we move past the first B, count becomes 0
                    //since the count is still <= 0 we don't decrement the curCount and the new window {XBAC} is still valid
                    curCount--;
                }
                begin++;
            }
            end++;
        }
        if (windowEnd == Integer.MAX_VALUE) {
            return "";
        } else {
            //return the substring from the min window
            //in my initial implementation I was storing the substring from min window everytime which is redundant
            //we can simply take the
            return s.substring(windowStart, windowEnd + 1);
        }
    }
}
