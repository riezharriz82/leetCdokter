import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/di-string-match/
 * <p>
 * Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
 * <p>
 * Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:
 * <p>
 * If S[i] == "I", then A[i] < A[i+1]
 * If S[i] == "D", then A[i] > A[i+1]
 * <p>
 * Input: "IDID"
 * Output: [0,4,1,3,2]
 * <p>
 * Input: "III"
 * Output: [0,1,2,3]
 */
public class ConvertIncreaseDecreaseStringToNumber {
    /**
     * Approach: Greedy algorithm, given a string of IDID, we have to generate output by using {0,1,2,3}
     * If we see 'I' first, the safest bet would be to place the current smallest number ie. 0
     * If we see 'D' instead, the safest bet would be to place the current highest number ie. 3
     */
    public int[] diStringMatch(String S) {
        int low = 0, high = S.length();
        int[] res = new int[S.length() + 1];
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'I') {
                res[i] = low++;
            } else {
                res[i] = high--;
            }
        }
        if (S.charAt(S.length() - 1) == 'I') {
            res[res.length - 1] = low;
        } else {
            res[res.length - 1] = high;
        }
        return res;
    }

    /**
     * This will return the minimum possible pattern
     */
    public int[] diStringMatchMinimum(String S) {
        int[] res = new int[S.length() + 1];
        int resIndex = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= S.length(); i++) {
            stack.push(i);
            if (i == S.length() || S.charAt(i) == 'I') {
                while (!stack.isEmpty()) {
                    res[resIndex++] = stack.pop();
                }
            }
        }
        return res;
    }
}
