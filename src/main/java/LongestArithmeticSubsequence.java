import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-arithmetic-subsequence/
 * <p>
 * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
 * <p>
 * Recall that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
 * <p>
 * Input: A = [3,6,9,12]
 * Output: 4
 * Explanation:
 * The whole array is an arithmetic sequence with steps of length = 3.
 * <p>
 * Input: A = [9,4,7,2,10]
 * Output: 3
 * Explanation:
 * The longest arithmetic subsequence is [4,7,10].
 * <p>
 * Input: A = [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:
 * The longest arithmetic subsequence is [20,15,10,5].
 */
public class LongestArithmeticSubsequence {
    /**
     * Approach: N^2 algorithm Similar to finding longest increasing subsequence
     * [9,4,7,2,10]
     * At 9 = {0=1}
     * At 4 = {-5=2}
     * At 7 = {-2=2, 3=2}
     * At 2 = {-7=2, -2=2, -5=2}
     * At 10 = {1=2, 6=2, 3=3, 8=2} Diff of 3 with longest chain of 3 is the answer
     */
    public int longestArithSeqLength(int[] A) {
        //can use a int[][] if the bounds of diff are known; as an optimization as maps are costly
        List<Map<Integer, Integer>> list = new ArrayList<>(); //diff is the key and length of chain is the value
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        list.add(map);
        int ans = 1;
        for (int i = 1; i < A.length; i++) {
            list.add(new HashMap<>());
            for (int j = 0; j < i; j++) {
                int curDiff = A[i] - A[j]; //current diff
                Map<Integer, Integer> diffs = list.get(j);
                int curLength = Math.max(2, diffs.getOrDefault(curDiff, 0) + 1); //check if there exists a previous subsequence of curDiff at j
                ans = Math.max(ans, curLength); //keep track of the max length
                Map<Integer, Integer> curMap = list.get(i);
                curMap.put(curDiff, Math.max(curLength, curMap.getOrDefault(curDiff, 0))); //try to put the curDiff in the current index
            }
        }
        return ans;
    }
}
