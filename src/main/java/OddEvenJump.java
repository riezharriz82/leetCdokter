import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/odd-even-jump/
 * <p>
 * You are given an integer array A. From some starting index, you can make a series of jumps.
 * The (1st, 3rd, 5th, ...) jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps.
 * Note that the jumps are numbered, not the indices.
 * <p>
 * You may jump forward from index i to index j (with i < j) in the following way:
 * <p>
 * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.
 * If there are multiple such indices j, you can only jump to the smallest such index j.
 * During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.
 * If there are multiple such indices j, you can only jump to the smallest such index j.
 * It may be the case that for some index i, there are no legal jumps.
 * A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once).
 * <p>
 * Return the number of good starting indices.
 * <p>
 * Input: A = [10,13,12,14,15]
 * Output: 2
 * Explanation:
 * From starting index i = 0, we can make our 1st jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3],
 * A[4] that is greater or equal to A[0]), then we cannot jump any more.
 * From starting index i = 1 and i = 2, we can make our 1st jump to i = 3, then we cannot jump any more.
 * From starting index i = 3, we can make our 1st jump to i = 4, so we have reached the end.
 * From starting index i = 4, we have reached the end already.
 * In total, there are 2 different starting indices i = 3 and i = 4, where we can reach the end with some number of
 * jumps.
 * <p>
 * Input: A = [2,3,1,1,4]
 * Output: 3
 * Explanation:
 * From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:
 * <p>
 * During our 1st jump (odd-numbered), we first jump to i = 1 because A[1] is the smallest value in [A[1], A[2],
 * A[3], A[4]] that is greater than or equal to A[0].
 * <p>
 * During our 2nd jump (even-numbered), we jump from i = 1 to i = 2 because A[2] is the largest value in [A[2], A[3],
 * A[4]] that is less than or equal to A[1]. A[3] is also the largest value, but 2 is a smaller index, so we can
 * only jump to i = 2 and not i = 3
 * <p>
 * During our 3rd jump (odd-numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value in [A[3], A[4]]
 * that is greater than or equal to A[2].
 * <p>
 * We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.
 * <p>
 * In a similar manner, we can deduce that:
 * From starting index i = 1, we jump to i = 4, so we reach the end.
 * From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
 * From starting index i = 3, we jump to i = 4, so we reach the end.
 * From starting index i = 4, we are already at the end.
 * In total, there are 3 different starting indices i = 1, i = 3, and i = 4, where we can reach the end with some
 * number of jumps.
 */
public class OddEvenJump {
    /**
     * Approach: It's very important to traverse from the right as it simplifies everything. It allows us to easily track the
     * smallest number >= current number or the largest number <= current number on the right in logn time using treeMap
     */
    public int oddEvenJumps(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap<>(); //value -> index mapping
        int n = A.length;
        int[] oddJump = new int[n]; //keeps track of the index to be jumped from ith index in odd jumps and even jumps
        int[] evenJump = new int[n];
        for (int i = n - 1; i >= 0; i--) { //very important to traverse from the end, it eases out the condition that number should be on right
            Map.Entry<Integer, Integer> ceilingEntry = map.ceilingEntry(A[i]); // finds A[j] >= A[i], odd jumps
            if (ceilingEntry == null) {
                oddJump[i] = -1;
            } else {
                oddJump[i] = ceilingEntry.getValue();
            }
            Map.Entry<Integer, Integer> floorEntry = map.floorEntry(A[i]); //finds A[j] <= A[i], even jumps
            if (floorEntry == null) {
                evenJump[i] = -1;
            } else {
                evenJump[i] = floorEntry.getValue();
            }
            map.put(A[i], i); //can handle duplicates also, because for duplicates we need to find the leftmost such index
        }
        //instead of map, could have used array memo[n][2]
        Map<Pair<Integer, Boolean>, Boolean> memoized = new HashMap<>(); //<index, isOddJump> -> can reach end
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (DFS(oddJump, evenJump, i, true, memoized)) { //if current index can reach end, it's a valid index
                res++;
            }
        }
        return res;
    }

    private boolean DFS(int[] oddJump, int[] evenJump, int index, boolean isOddJump, Map<Pair<Integer, Boolean>, Boolean> memoized) {
        if (index == oddJump.length - 1) { //reached last index
            return true;
        }
        Pair<Integer, Boolean> key = new Pair<>(index, isOddJump);
        if (memoized.containsKey(key)) {
            return memoized.get(key);
        } else {
            boolean canReachEnd = false;
            if (isOddJump && oddJump[index] != -1) { //if current jump is odd numbered, jump to valid indices
                canReachEnd = DFS(oddJump, evenJump, oddJump[index], false, memoized); //next jump will be even numbered
            }
            if (canReachEnd) {
                memoized.put(key, true);
                return true;
            }
            if (!isOddJump && evenJump[index] != -1) { //if current jump is even numbered, jump to valid indices
                canReachEnd = DFS(oddJump, evenJump, evenJump[index], true, memoized);
            }
            memoized.put(key, canReachEnd);
            return canReachEnd;
        }
    }
}
