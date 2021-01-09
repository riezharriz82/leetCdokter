import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * <p>
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {
    /**
     * Approach: Similar to LongestIncreasingSubsequence.
     * Assume that we already have the results computed so far, if given a new number x, how can we compute the result quickly?
     * if we know the answer for (x-1), we can reuse the result and return it quickly.
     * <p>
     * Key thing is we go only in one direction (up or down) and reuse the results previously computed.
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(); //acts like a dp
        //key is the number and value is the length of the longest consecutive path ending at current key
        int maxVal = 0;
        for (int num : nums) {
            map.put(num, 0); //need not worry about duplicates as they can't contribute anything extra
        }
        for (int num : nums) {
            maxVal = Math.max(maxVal, DFS(num, map));
        }
        return maxVal;
    }

    private int DFS(int num, Map<Integer, Integer> map) {
        if (map.containsKey(num)) {
            int val = map.get(num);
            if (val == 0) { //if this key has not been visited
                int newLength = 1 + DFS(num - 1, map); //look down to find a longer path
                map.put(num, newLength); //update the length of the longest path ending at num
                return newLength;
            } else {
                //key already visited, return the longest path found earlier
                return val;
            }
        } else {
            //key is not present
            return 0;
        }
    }
}
