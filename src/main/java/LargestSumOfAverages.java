/**
 * <pre>
 * https://leetcode.com/problems/largest-sum-of-averages/
 *
 * We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group.
 * What is the largest score we can achieve?
 *
 * Note that our partition must use every number in A, and that scores are not necessarily integers.
 *
 * Input:
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation:
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 *
 * Note:
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * Answers within 10^-6 of the correct answer will be accepted as correct.
 * </pre>
 */
public class LargestSumOfAverages {
    /**
     * Approach: Recursion with Top down memoization. Try cutting at each possible indexes, keeping track of total sum of averages so far.
     * Time Complexity: O(n*n*k) No of states (n*k) * Transition Time (n)
     * <p>
     * Do note that the problem asked for at most k splits and we have coded for exact k splits. This is because higher the splits,
     * higher will be the average, so if k == 3, largest sum of averages would be possible only for 3 splits, not 2 or 1 splits.
     * <p>
     * Initially I thought of applying Binary Search on Doubles but then realized that it could not be applied as the problem statement
     * is asking for largest sum of total averages of all groups. If it had been the largest sum of average of any group, then binary search
     * could have been applied.
     * <p>
     * {@link SplitArrayLargestSum} {@link MinimumDifficultyOfJobSchedule}
     */
    public double largestSumOfAverages(int[] A, int K) {
        double[][] memo = new double[A.length][K + 1];
        return recur(A, 0, K, memo);
    }

    private double recur(int[] a, int index, int k, double[][] memo) {
        if (index == a.length) {
            //if reached last index, check if the array had exact k splits
            return k == 0 ? 0 : Integer.MIN_VALUE;
        } else if (k == 0) {
            //if already array has k splits and we have not yet reached the end, this indicates an invalid split
            return Integer.MIN_VALUE;
        } else if (memo[index][k] != 0) {
            return memo[index][k];
        }
        int curSum = 0, curElements = 0;
        double maxScore = 0;
        for (int i = index; i < a.length; i++) {
            curSum += a[i];
            curElements++;
            double remaining = recur(a, i + 1, k - 1, memo);
            if (remaining != Integer.MIN_VALUE) {
                double curScore = ((double) curSum / curElements) + remaining;
                maxScore = Math.max(maxScore, curScore);
            }
        }
        return memo[index][k] = maxScore;
    }
}
