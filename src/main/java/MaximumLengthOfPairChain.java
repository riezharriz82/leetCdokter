import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-length-of-pair-chain/
 * <p>
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 * <p>
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 * <p>
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 * <p>
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * <p>
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 */
public class MaximumLengthOfPairChain {
    /**
     * Approach: Similar to finding longest increasing subsequence LIS, only difference is that pairs can be selected in any order.
     * So answer should not be restricted to a subsequence, so we should sort the array to try to find even better answers by placing
     * smallest pair first. Time Complexity: O(n^2) + O(nlogn)
     * <p>
     * This can be also done by greedy in O(n) + O(nlogn) by visualizing it as a scheduling problem and task is to pick maximum non overlapping intervals
     * <p>
     * {@link RussianDollEnvelopes} {@link LargestDivisibleSubset} {@link EraseNonOverlappingIntervals} {@link MinNumberOfTapsToOpenToWaterGarden}
     */
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (o1, o2) -> Integer.compare(o1[1], o2[1])); //sorting is required because any pairs can be selected
        int[] length = new int[n];
        Arrays.fill(length, 1);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    length[i] = Math.max(length[i], length[j] + 1);
                }
            }
            ans = Math.max(length[i], ans);
        }
        return ans;
    }
}
