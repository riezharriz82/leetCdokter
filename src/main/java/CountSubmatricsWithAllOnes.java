/**
 * https://leetcode.com/problems/count-submatrices-with-all-ones/
 * <p>
 * Given a rows * columns matrix mat of ones and zeros, return how many submatrices have all ones.
 * <p>
 * Input: mat = [[1,0,1],
 * [1,1,0],
 * [1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2.
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 */
public class CountSubmatricsWithAllOnes {
    /**
     * Approach: Similar to finding the largest rectangle in a matrix {@link MaximalRectangle}
     * Instead of finding the largest rectangle, we need to find count of all the rectangles with all 1's
     * Time Complexity: n^2
     * <p>
     * It's a bit difficult to visualize why computing the area leads to the count but if you solve it on paper you will understand that
     * it works
     * do for [1,1][1,1]
     */
    public int numSubmatOptimized(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] histogram = new int[n];
        int res = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (mat[row][col] == 1) {
                    histogram[col] += 1;
                } else {
                    histogram[col] = 0;
                }
            }
            res += countSubarrays(histogram);
        }
        return res;
    }

    private int countSubarrays(int[] histogram) {
        //TODO I don't understand this at the moment, will have to revisit this again
        //Already spent couple of hours on this
        return 0;
    }

    /**
     * Approach: 2D compression algorithm similar to other submatrices related problem
     * Time Complexity: n^3
     * Was able to implement it on my own in first attempt
     * <p>
     * {@link NumberOfSubmatricesThatSumsToTarget} {@link CountSquareSubmatricesWithAllOnes} {@link MaxSumOfRectangleNoLargerThanK}
     */
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int res = 0;
        for (int col1 = 0; col1 < n; col1++) {
            int[] arr = new int[m];
            //init the base separately because we need to increment arr[row] only if the current matrix value is 1 and all the previous rows
            //were all 1's ie. arr[row] != 0, this is required to not consider submatrices with partial 0's and partial 1's
            for (int row = 0; row < m; row++) {
                arr[row] = mat[row][col1];
            }
            res += countSubarraysWithAllOnes(arr);
            for (int col2 = col1 + 1; col2 < n; col2++) {
                for (int row = 0; row < m; row++) {
                    if (mat[row][col2] == 0) {
                        arr[row] = 0;
                    } else if (mat[row][col2] == 1 && arr[row] != 0) { //tricky logic to handle submatrices with no zeroes
                        arr[row]++;
                    }
                }
                res += countSubarraysWithAllOnes(arr);
            }
        }
        return res;
    }

    /**
     * Count the no of subarrays with all ones in 1D array
     * e.g. 1110011 => 1 continues for 3 times and then again 2 times
     * So 111 can result in total n*(n+1)/2 subarrays = 6 subarrays
     * and 11 can result in 3 subarrays
     */
    private int countSubarraysWithAllOnes(int[] arr) {
        int index = 0, res = 0;
        while (index < arr.length) {
            if (arr[index] != 0) {
                int cnt = 0; //count the length of non zero elements
                while (index < arr.length && arr[index] != 0) {
                    cnt++;
                    index++;
                }
                //update the total no of valid subarrays
                res += ((cnt * (cnt + 1)) / 2);
            } else {
                index++;
            }
        }
        return res;
    }
}
