/**
 * https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 * <p>
 * You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
 * <p>
 * In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1.
 * Note that after doing so, there may be more than one ball in some boxes.
 * <p>
 * Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
 * <p>
 * Each answer[i] is calculated considering the initial state of the boxes.
 * <p>
 * Input: boxes = "110"
 * Output: [1,1,3]
 * Explanation: The answer for each box is as follows:
 * 1) First box: you will have to move one ball from the second box to the first box in one operation.
 * 2) Second box: you will have to move one ball from the first box to the second box in one operation.
 * 3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
 * <p>
 * Input: boxes = "001011"
 * Output: [11,8,5,4,3,4]
 * <p>
 * Constraints:
 * n == boxes.length
 * 1 <= n <= 2000
 * boxes[i] is either '0' or '1'.
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {
    /**
     * Approach: Tricky DP, cost of moving balls to a specific index can be broken down into two parts, first move all balls on the left, then on the right
     * If we know the cost of moving balls to (i-1)th index, then dp[i] = dp[i-1] + (cost of moving x balls from i-1 to i index)
     * Repeat the sweep for left and right direction
     * <p>
     * {@link MinCostToMoveChipsToTheSamePosition} {@link MinDominoRotationsForEqualRow} {@link MinimumNumberOfRemovalsToMakeMountainArray}
     * {@link FindTwoNonOverlappingSubarrayWithTargetSum} {@link BestTimeToBuyAndSellStock}
     */
    public int[] minOperationsDP(String boxes) {
        int n = boxes.length();
        int[] res = new int[n], left = new int[n + 1], right = new int[n + 1];
        int balls = boxes.charAt(0) == '1' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + balls;
            //if there are x balls till i-1 index, cost of moving them would be 1*x
            if (boxes.charAt(i) == '1') {
                balls++; //increment the count of balls if current index has a ball
            }
        }
        //repeat the same process but from right to left direction
        balls = boxes.charAt(n - 1) == '1' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + balls;
            if (boxes.charAt(i) == '1') {
                balls++;
            }
        }
        for (int i = 0; i < n; i++) {
            res[i] = left[i] + right[i];
        }
        return res;
    }

    /**
     * Approach: Brute force, consider each ball as the target and try to move all the remaining balls to the target index
     */
    public int[] minOperationsBruteForce(String boxes) {
        int n = boxes.length();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int moves = 0; //cost of moving all other balls to ith index
            for (int j = 0; j < n; j++) {
                if (boxes.charAt(j) == '1') {
                    moves += Math.abs(j - i);
                }
            }
            result[i] = moves;
        }
        return result;
    }
}
