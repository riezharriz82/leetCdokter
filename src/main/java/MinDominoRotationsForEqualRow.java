/**
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
 * <p>
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * <p>
 * We may rotate the ith domino, so that A[i] and B[i] swap values.
 * <p>
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * <p>
 * If it cannot be done, return -1.
 * <p>
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 */
public class MinDominoRotationsForEqualRow {
    /**
     * Approach: Very similar to the Google Kickstart question {@link alternate.CombinationLock}
     * Min Rotations is only possible where one of the initial state is chosen as the end state
     * So consider each domino as the end state and see how many rotations are required to make the remaining dominoes similar to the candidate
     * It's an n^2 operation but since the value of each domino can only be 1-6 we can cache the result and reuse it.
     * So time complexity reduces to o(n)
     * <p>
     * Very happy to solve this on my own after yesterday's disastrous performance.
     */
    public int minDominoRotations(int[] A, int[] B) {
        int minRotations = Integer.MAX_VALUE;
        boolean[] a_dp = new boolean[7];
        boolean[] b_dp = new boolean[7];
        for (int i = 0; i < A.length; i++) {
            int candidate = A[i]; //consider a[i] as the end state
            if (a_dp[candidate]) {
                continue;
            }
            int curRotations = 0;
            boolean rotationPossible = true;
            for (int j = 0; j < B.length; j++) {
                if (A[j] != candidate && B[j] != candidate) { //if a[j] is not end state, we need to check if b[j] is the candidate, so that we can rotate
                    rotationPossible = false;
                    break;
                } else if (A[j] != candidate) {
                    curRotations++;
                }
            }
            if (rotationPossible) {
                minRotations = Math.min(curRotations, minRotations);
            }
            a_dp[candidate] = true;
        }
        //repeat the same process but now try to make all rows of b[] similar
        for (int i = 0; i < B.length; i++) {
            int candidate = B[i];
            if (b_dp[candidate]) {
                continue;
            }
            int curRotations = 0;
            boolean rotationPossible = true;
            for (int j = 0; j < A.length; j++) {
                if (B[j] != candidate && A[j] != candidate) {
                    rotationPossible = false;
                    break;
                } else if (B[j] != candidate) {
                    curRotations++;
                }
            }
            if (rotationPossible) {
                minRotations = Math.min(curRotations, minRotations);
            }
            b_dp[candidate] = true;
        }
        return minRotations == Integer.MAX_VALUE ? -1 : minRotations;
    }
}
