import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 * <p>
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal,
 * where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 * <p>
 * You may assume the array's length is at most 10,000.
 * <p>
 * Input:
 * [1,2,3]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */
public class MinimumMovesToEqualArrayElements2 {
    /**
     * Approach: Median minimizes the sum of absolute differences whereas mean minimizes the sum of squared differences
     * So need to calculate median and return the sum of absolute differences as the no of moves required
     * TimeComplexity O(nlogn)
     * <p>
     * Time complexity can be reduced to O(n) by using quick select (median of medians as a further optimization)
     * <p>
     * {@link BestMeetingPoint} {@link AllocateMailboxes} {@link SlidingWindowMedian} {@link FindMedianFromDataStream}
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int median = nums[n / 2];
        int res = 0;
        for (int num : nums) {
            res += Math.abs(median - num);
        }
        return res;
    }
}
