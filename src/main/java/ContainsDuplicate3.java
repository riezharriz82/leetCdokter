import java.util.TreeSet;

/**
 * https://leetcode.com/problems/contains-duplicate-iii/
 * <p>
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
 * absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 * <p>
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 */
public class ContainsDuplicate3 {
    /**
     * Approach: Two things are being asked in the problem
     * 1. Absolute difference between two indices <= k, this indicates sliding window
     * 2. Absolute difference between two numbers in the current window <= t, this indicates treeSet to find a valid number in the current window
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>(); //using long because of stupid test cases involving INT_MIN and INT_MAX
        for (int i = 0; i < nums.length; i++) {
            if (i > k) { //need to remove the element fallen outside of window
                set.remove((long) nums[i - k - 1]);
            }
            //Important part, if you use hashset to iterate over the range of possible values [nums[i]-t, nums[i]+t], you will get TLE
            // instead use TreeSet to find the lowest possible number greater than or equal to nums[i]-t
            // if such number exists, check if the difference is valid as it can also be a greater number
            Long ceiling = set.ceiling((long) nums[i] - t);
            //using nums[i] - t here is key because we are using treeset and it can't have duplicate values whereas the input can
            //using ceiling here is important too.. why cant we use floor because if the current element is 10, and the maxDiff is 3
            //we need to find whether any no >= 7 is in the current window of treeSet
            //ceiling provides >= whereas floor provides <=
            if (ceiling != null && Math.abs(ceiling - nums[i]) <= t) {
                return true;
            }
            set.add((long) nums[i]); // add the element in the window
        }
        return false;
    }
}
