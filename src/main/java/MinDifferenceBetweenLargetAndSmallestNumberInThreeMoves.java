import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 * <p>
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 * <p>
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 * Example 2:
 * <p>
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1].
 * The difference between the maximum and minimum is 1-0 = 1.
 */
public class MinDifferenceBetweenLargetAndSmallestNumberInThreeMoves {
    /**
     * Intuition
     * If we can do 0 move, return max(A) - min(A)
     * If we can do 1 move, return min(the second max(A) - min(A), the max(A) - second min(A))
     * and so on.
     * <p>
     * Explanation
     * We have 4 plans:
     * <p>
     * kill 3 biggest elements
     * kill 2 biggest elements + 1 smallest elements
     * kill 1 biggest elements + 2 smallest elements
     * kill 3 smallest elements
     */
    public int minDifference(int[] nums) {
        Arrays.sort(nums);
        if (nums.length < 5) {
            return 0;
        }
        int n = nums.length;
        //if input is {0,1,5,10,14,15}, opt1 would be {0,1,5,5,5,5}
        int opt1 = nums[n - 4] - nums[0];
        //opt2 would be {1,1,5,10,10,10}
        int opt2 = nums[n - 3] - nums[1];
        //opt3 would be {5,5,5,10,14,14}
        int opt3 = nums[n - 2] - nums[2];
        //opt4 would be {10,10,10,10,14,15}
        int opt4 = nums[n - 1] - nums[3];
        return Math.min(opt1, Math.min(opt2, Math.min(opt3, opt4)));
    }
}
