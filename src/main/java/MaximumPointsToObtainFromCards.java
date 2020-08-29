import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 * <p>
 * There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
 * <p>
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * <p>
 * Your score is the sum of the points of the cards you have taken.
 * <p>
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * <p>
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score.
 * The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 */
public class MaximumPointsToObtainFromCards {
    /**
     * Approach: As the DP problem was unable to give me AC, this implies that a linear solution is required
     * This problem can be reduced to sliding window problem, because the result can be either first k digits,
     * first k - 1 digits, last 1 digit or first k - 2 digits, last 2 digits and so on.
     * So if you find a min subarray of size n - k, the result will be total sum - min subarray sum
     */
    public int maxScore(int[] cardPoints, int k) {
        int totalSum = 0;
        for (int cardPoint : cardPoints) {
            totalSum += cardPoint;
        }
        int result = 0, minWindowSum = 0, windowSize = cardPoints.length - k;
        for (int i = 0; i < cardPoints.length; i++) {
            minWindowSum += cardPoints[i];
            if (i == windowSize - 1) {
                result = Math.max(result, totalSum - minWindowSum);
            }
            if (i >= windowSize) {
                minWindowSum -= cardPoints[i - windowSize]; //take care of elements falling out of the window
                result = Math.max(result, totalSum - minWindowSum);
            }
        }
        return result;
    }

    public int maxScoreWithMemoization(int[] cardPoints, int k) {
        //memory limit exceeded :D
        int[][][] memo = new int[k + 1][cardPoints.length][cardPoints.length];
        for (int[][] ints : memo) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
        return recur(cardPoints, k, 0, cardPoints.length - 1, memo);
    }

    private int recur(int[] cardPoints, int k, int left, int right, int[][][] memo) {
        if (k == 0 || left > cardPoints.length || right < 0) {
            return 0;
        }
        if (memo[k][left][right] != -1) {
            return memo[k][left][right];
        }
        int chooseBeginning = cardPoints[left] + recur(cardPoints, k - 1, left + 1, right, memo);
        int chooseEnd = cardPoints[right] + recur(cardPoints, k - 1, left, right - 1, memo);
        memo[k][left][right] = Math.max(chooseBeginning, chooseEnd);
        return memo[k][left][right];
    }
}
