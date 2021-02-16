import java.util.TreeSet;

/**
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 * <p>
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
 * <p>
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
 * and 2 is the max number no larger than k (k = 2).
 */
public class MaxSumOfRectangleNoLargerThanK {
    /**
     * Approach: My initial solution based on {@link NumberOfSubmatricesThatSumsToTarget}
     * Subarray sum that equals to k is easy and can be calculated using hashmap
     * Finding Subarray sum <= k is a bit non trivial because target subarray can be any subarray between two indices
     * In order to solve it better than n^2, we need to use treeset (No treemap, as we are not interested in count)
     * Before putting current subarray prefix sum in treeset, check if any subarray sum >= current sum - target is present in set or not.
     * If yes, find the difference between that subarray sum and current sum
     * <p>
     * Amazed to see that I was able to think of nlogn solution on my own :D
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int col1 = 0; col1 < matrix[0].length; col1++) { //consider all columns as the initial one
            int[] currentSubmatrixSum = new int[matrix.length];
            for (int col2 = col1; col2 < matrix[0].length; col2++) { //expand the current base
                for (int row = 0; row < matrix.length; row++) {
                    currentSubmatrixSum[row] += matrix[row][col2];
                }
                //process the current submatrix sum
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int prefixSum = 0;
                for (int i = 0; i < currentSubmatrixSum.length; i++) {
                    prefixSum += currentSubmatrixSum[i];
                    Integer ceiling = set.ceiling(prefixSum - k); //essence of the code, upper bound on subarray sum is k
                    //so if current prefix sum is 10 and k is 7, we need to look for prefix sum >= 3, anything less than 3 will increase the subarray sum
                    if (ceiling != null) {
                        maxSum = Math.max(maxSum, prefixSum - ceiling);
                    }
                    set.add(prefixSum);
                }
            }
        }
        return maxSum;
    }
}
