import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-closest/
 * <p>
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    /**
     * Approach: Reduce the problem to finding tuples such that their sum is closest to target. This can be done using two pointers algorithm.
     * At each point keep track of the current sum and update the result by taking absolute diff from the target
     * <p>
     * Use this as a subproblem by iteratively picking each element and reducing the target sum
     * <p>
     * {@link ThreeSum} {@link ThreeSumSmaller} related problems
     */
    public int threeSumClosest(int[] nums, int target) {
        int result = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int targetSum = target - nums[i];
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int candidateSum = nums[low] + nums[high];
                if (result == Integer.MAX_VALUE) {
                    result = candidateSum + nums[i];
                } else if (Math.abs(target - result) > Math.abs(target - candidateSum - nums[i])) {
                    result = candidateSum + nums[i];
                }
                if (candidateSum < targetSum) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return result;
    }
}
