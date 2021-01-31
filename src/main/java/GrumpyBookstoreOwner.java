/**
 * https://leetcode.com/problems/grumpy-bookstore-owner/
 * <p>
 * Today, the bookstore owner has a store open for customers.length minutes.
 * Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 * <p>
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.
 * When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.
 * <p>
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 * <p>
 * Return the maximum number of customers that can be satisfied throughout the day.
 * <p>
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * Note:
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 */
public class GrumpyBookstoreOwner {
    /**
     * Approach: Sliding window, need to find the max subarray of size X that starts when owner is grumpy
     * <p>
     * {@link LongestRepeatingCharacterReplacement} {@link MinimumWindowSubstring}
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int currentlySatisfied = 0;
        int[] unSatisfiedCustomer = new int[n]; //create a new array of customers that are unsatisfied because of grumpy owner
        //can also be done in O(1) space
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 1) {
                unSatisfiedCustomer[i] = customers[i];
            } else {
                currentlySatisfied += customers[i]; //customers that are already satisfied
            }
        }
        int maxSubarraySum = 0, curSubarraySum = 0;
        for (int i = 0; i < n; i++) {
            curSubarraySum += unSatisfiedCustomer[i];
            if (i >= X) {
                //remove the number falling outside of window
                curSubarraySum -= unSatisfiedCustomer[i - X];
            }
            //keep track of largest subarray sum of max size X
            maxSubarraySum = Math.max(maxSubarraySum, curSubarraySum);
        }
        return currentlySatisfied + maxSubarraySum;
    }
}
