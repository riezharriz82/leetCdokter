/**
 * https://leetcode.com/problems/greatest-sum-divisible-by-three/
 * <p>
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
 * <p>
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 * <p>
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 * <p>
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 * <p>
 * Constraints:
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 */
public class GreatestSumDivisibleByThree {
    /**
     * Approach: Tricky DP solution, initially I tried to solve using greedy by dividing the elements into multiple buckets of 0,1,2 remainder
     * and then tried to pick up all 0's or pick up 2's and 1's in pair or pick up 3 1's or 3 2's but couldn't solve it
     * Saw the hint and realized it needs to be done using DP and then I was able to solve it.
     * <p>
     * During the state transitions, when current element contributes partially to the mod sum, need to make sure previous element exists
     * e.g if current element mod is 1 and we are trying to update [current][2] need to make sure [current-1][1] is not zero ie. it exists
     * otherwise we will incorrectly update the [current][2] with current which is wrong.
     * <p>
     * Although, It can be also solved using greedy if we find the total sum and then if the remainder is 1, greedily either try to remove the
     * smallest number with mod 1 or try to remove 2 smallest numbers with mod 2. Compare which gives the largest sum. Sorting is required which
     * will increase the time complexity to nlogn
     * <p>
     * {@link HouseRobber} related problem
     */
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][3]; //dp[i][j] represents the greatest sum possible till ith index with mod as j
        for (int i = 0; i < n; i++) {
            if (i == 0) { //base case for first element, update the corresponding mod index
                dp[i][nums[i] % 3] = nums[i];
            } else {
                int rem = nums[i] % 3;
                if (rem == 0) { //current + previous_mod1 = current_mod1, current + pre2 = cur2
                    dp[i][0] = dp[i - 1][0] + nums[i];
                    //for partial mod sum cases, need to check whether previous element exists or not
                    dp[i][1] = dp[i - 1][1] != 0 ? dp[i - 1][1] + nums[i] : 0;
                    dp[i][2] = dp[i - 1][2] != 0 ? dp[i - 1][2] + nums[i] : 0;
                } else if (rem == 1) {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] != 0 ? dp[i - 1][2] + nums[i] : 0); //1+2 == 0
                    dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i]);
                    dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] != 0 ? dp[i - 1][1] + nums[i] : 0); //1+1 == 2
                } else {
                    dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] != 0 ? dp[i - 1][1] + nums[i] : 0); //2+1 == 0
                    dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] != 0 ? dp[i - 1][2] + nums[i] : 0); //2+2 == 1
                    dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] + nums[i]);
                }
            }
        }
        return dp[n - 1][0];
    }
}
