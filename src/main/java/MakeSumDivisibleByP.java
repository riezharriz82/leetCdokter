import java.util.HashMap;

/**
 * https://leetcode.com/problems/make-sum-divisible-by-p/
 * <p>
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the
 * remaining elements is divisible by p. It is not allowed to remove the whole array.
 * <p>
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 * <p>
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6.
 * We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
 */
public class MakeSumDivisibleByP {
    /**
     * Approach: Really good problem dealing with subarray sum and modulo arithmetic
     * The thing to understand is that to make an array divisible by p either the (total sum % p == 0) or
     * we remove a subarray whose sum is (total sum % p)
     * <p>
     * Now the problem is reduced to finding a subarray whose sum equals a target sum {@link SubarraySumEqualsK}
     * I solved this problem without considering modulo arithmetic and got WA in contest.
     * <p>
     * e.g. If the target is 15 and p is 17, the required subarray sum can be
     * 17*0 + 15 = 15 = 15%17 = 15
     * 17*1 + 15 = 32 = 32%17 = 15
     * 17*2 + 15 = 49 = 49%17 = 15
     * <p>
     * This is the critical thing to understand, so we keep track of the (runningSum % p) instead of just runningSum as we are only interested in the mod
     * Now things become tricky again, because modulo
     * if current prefix sum is 16, normally we look for a previous prefix sum of (16-15) = 1
     * but what if the prefix sum is 15? if we look for (15-16) = -1 we won't find anything because of stupid wrapping over due to mod.
     * <p>
     * e.g input [26,19,11,14,18,4,7,1,30,23,19,8,10,6,26,3], 26 target is 17
     * running modsum is {0, 19, 4, 18, 10, 14......}
     * <pre>                  |_________| </pre>
     * the highlighted subarray sum is 43 % 26 = 17
     * if we lookup for (10 - 17) we wont find anything
     * so we need to lookup for (10-17+26) = 19
     * <p>
     * {@link SubarraySumsDivisibleByK} for related subarray sum + divisibility problem
     */
    public int minSubarray(int[] nums, int p) {
        int target = 0;
        for (int num : nums) {
            target = (target + num) % p; //perform modulo because totalSum may overflow
        }
        if (target == 0) {
            return 0;
        } else {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int curSum = 0, minLength = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                curSum = (curSum + nums[i]) % p; //curSum can only be positive because input array contains only +ve numbers
                //if it had contained negative numbers, need to make it positive by doing (curSum + p)
                if (map.containsKey((curSum - target + p) % p)) { //When comparing modulo's negative number should be made +ve
                    //this is similar to the way we handled mod sum in SubarraySumsDivisibleByK
                    minLength = Math.min(minLength, i - map.get((curSum - target + p) % p));
                }
                map.put(curSum, i);
            }
            return minLength < nums.length ? minLength : -1;
        }
    }
}
