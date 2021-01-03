import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/beautiful-arrangement/
 * <p>
 * Suppose you have n integers from 1 to n. We define a beautiful arrangement as an array that is constructed by these n numbers successfully
 * if one of the following is true for the ith position (1 <= i <= n) in this array:
 * <p>
 * The number at the ith position is divisible by i.
 * i is divisible by the number at the ith position.
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1, 2]:
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 * The second beautiful arrangement is [2, 1]:
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * <p>
 * Input: n = 1
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= n <= 15
 */
public class BeautifulArrangement {
    /**
     * Approach: Backtracking with bitmasking (which acts as memoization). Without bitmasking also we get AC
     * Without bitmasking n=20 took 6 sec, with bitmasking it took 150 ms
     * As learnt earlier, use the pair of <bitmask, current index> as an unique key during traversal
     * <p>
     * {@link ShortestPathVisitingAllNodes} related bitmasking problem
     */
    public int countArrangementBitmasking(int n) {
        Map<Pair<Integer, Integer>, Integer> map = new HashMap<>(); //map of <bitmask, index> to no of ways, important to understand it correctly
        return recur(map, 1, 0, n);
    }

    private int recur(Map<Pair<Integer, Integer>, Integer> map, int index, int bitmask, int n) {
        if (index > n) {
            return 1;
        }
        Pair<Integer, Integer> key = new Pair<>(bitmask, index);
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            int totalWays = 0;
            for (int i = 1; i <= n; i++) {
                if ((bitmask & (1 << i)) == 0 && ((i % index == 0) || (index % i == 0))) { //if current number is not already placed and is beautiful, place it
                    totalWays += recur(map, index + 1, bitmask | (1 << i), n);
                }
            }
            map.put(key, totalWays);
            return totalWays;
        }
    }

    /**
     * Approach: Backtracking
     */
    public int countArrangementBacktracking(int n) {
        boolean[] visited = new boolean[n + 1];
        return recur(1, n, visited);
    }

    private int recur(int index, int n, boolean[] visited) {
        if (index > n) { //if all the indexes from 1 till n has been satisfied, return 1
            return 1;
        } else {
            int totalWays = 0;
            for (int num = 1; num <= n; num++) {
                if (!visited[num] && ((index % num == 0) || (num % index == 0))) { //if num can be placed on the current index
                    visited[num] = true;
                    totalWays += recur(index + 1, n, visited); //move to next index
                    visited[num] = false; //backtrack
                }
            }
            return totalWays;
        }
    }
}
