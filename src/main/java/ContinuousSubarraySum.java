import java.util.HashMap;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 * <p>
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous
 * subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 * <p>
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 */
public class ContinuousSubarraySum {
    /**
     * Approach: Keep track of running modsum ie. (running sum % k)
     * If modsum repeats, it means the mod sum between the repeating indices is zero i.e subarray sum is divisible by k
     * <p>
     * {@link SubarraySumsDivisibleByK} for similar problem
     */
    public boolean checkSubarraySumSimplified(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum = (curSum + nums[i]) % k; //nums[i] is always +ve otherwise would have to add +k
            if (map.containsKey(curSum) && i - map.get(curSum) >= 2) { //need to take care of the length
                return true;
            }
            map.put(curSum, i);
        }
        return false;
    }

    //Big oooff edge cases
    public boolean checkSubarraySumWithAllTheCornerCases(int[] nums, int k) {
        if (k == 0) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == 0 && nums[i + 1] == 0) {
                    return true;
                }
            }
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int curSum = 0;
        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            curSum = (curSum + nums[i]) % k;
            if (map.containsKey(curSum) && i - map.get(curSum) >= 2) {
                return true;
            } else if (!map.containsKey(curSum)) {
                map.put(curSum, i);
            }
        }
        if (totalSum == 0) {
            return nums.length >= 2;
        }
        return false;
    }
}
