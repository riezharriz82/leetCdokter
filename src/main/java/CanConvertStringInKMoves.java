/**
 * https://leetcode.com/problems/can-convert-string-in-k-moves/
 * <p>
 * Given two strings s and t, your goal is to convert s into t in k moves or less.
 * <p>
 * During the ith (1 <= i <= k) move you can:
 * <p>
 * Choose any index j (1-indexed) from s, such that 1 <= j <= s.length and j has not been chosen in any previous move, and shift the character at that index i times.
 * Do nothing.
 * Shifting a character means replacing it by the next letter in the alphabet (wrapping around so that 'z' becomes 'a').
 * Shifting a character by i means applying the shift operations i times.
 * <p>
 * Remember that any index j can be picked at most once.
 * <p>
 * Return true if it's possible to convert s into t in no more than k moves, otherwise return false.
 * <p>
 * Input: s = "input", t = "ouput", k = 9
 * Output: true
 * Explanation: In the 6th move, we shift 'i' 6 times to get 'o'. And in the 7th move we shift 'n' to get 'u'.
 */
public class CanConvertStringInKMoves {
    /**
     * Edge cases to handle: za -> ab {1,27}
     * Approach: Kinda similar to {@link MakingFileNamesUnique} by keeping track of the last multiplier used for any value
     * Difference between any two chars can't exceed 26, so keep track of the last multiplier used for the diff
     * If next multiplier is not within bounds, return false
     */
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] multipliers = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int diff = (26 + t.charAt(i) - s.charAt(i)) % 26; //Math tip to handle circular distances between two points
            if (diff > k) {
                return false;
            }
            if (diff != 0) {
                int cur = multipliers[diff];
                //if cur multiplier is 1 and diff is 1, then this shift can happen next by 27 shift
                int nextSlot = 26 * cur + diff;
                if (nextSlot > k) {
                    return false;
                } else {
                    multipliers[diff]++;
                }
            }
        }
        return true;
    }
}
