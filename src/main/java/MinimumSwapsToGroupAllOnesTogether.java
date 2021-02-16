/**
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together/
 * <p>
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 * <p>
 * Input: data = [1,0,1,0,1]
 * Output: 1
 * Explanation:
 * There are 3 ways to group all 1's together:
 * [1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0] using 2 swaps.
 * [0,0,1,1,1] using 1 swap.
 * The minimum is 1.
 * <p>
 * Input: data = [0,0,0,1,0]
 * Output: 0
 * Explanation:
 * Since there is only one 1 in the array, no swaps needed.
 * <p>
 * Input: data = [1,0,1,0,1,0,0,1,1,0,1]
 * Output: 3
 * Explanation:
 * One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
 * <p>
 * Input: data = [1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1]
 * Output: 8
 * <p>
 * Constraints:
 * 1 <= data.length <= 105
 * data[i] is 0 or 1.
 */
public class MinimumSwapsToGroupAllOnesTogether {
    /**
     * Approach: Keep track of number of ones in a sliding window of fixed size, number of zeroes present would be the no of swaps required
     * <p>
     * We can optimize the space complexity by not precomputing the no of 1's present in a subarray but rather use sliding window
     * to calculate no of 1's. Maintain a sliding window of fixed size and increment/decrement the count while moving the window
     * <p>
     * {@link FindGroupWithSizeM} {@link MinimumWindowSubstring} {@link SubarrayProductLessThanK} related problem
     */
    public int minSwaps(int[] data) {
        int targetNumOfOnes = 0;
        for (int val : data) {
            if (val == 1) {
                targetNumOfOnes++;
            }
        }
        if (targetNumOfOnes == 0) {
            return 0;
        } else {
            int n = data.length;
            int[] countOfOnes = new int[n + 1]; //use prefix sum to calculate no of ones present in any subarray in O(1) time
            //notice the size of array, its n + 1 to avoid edge cases
            for (int i = 0; i < n; i++) {
                if (data[i] == 1) {
                    countOfOnes[i + 1] = 1 + countOfOnes[i];
                } else {
                    countOfOnes[i + 1] = countOfOnes[i];
                }
            }
            int minSwaps = Integer.MAX_VALUE, end = targetNumOfOnes - 1;
            while (end < n) {
                int begin = end - targetNumOfOnes + 1;
                int onesInWindow = countOfOnes[end + 1] - countOfOnes[begin]; //find the no of ones in current window
                //normally it should be a[end] - a[begin - 1] but since our countOfOnes[] array indexes are +1 based, we have to do +1 on index
                int zeroesInWindow = targetNumOfOnes - onesInWindow;
                minSwaps = Math.min(minSwaps, zeroesInWindow);
                end++;
            }
            return minSwaps;
        }
    }
}
