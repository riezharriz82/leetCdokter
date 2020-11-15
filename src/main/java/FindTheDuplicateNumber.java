/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,4,2,2]
 * Output: 2
 */
public class FindTheDuplicateNumber {
    /**
     * Approach: You must not modify the array (assume the array is read only)
     * Use warshall floyd slow/fast pointer technique to detect duplicates
     * <p>
     * Do note that we do not simply move the slow pointer to next index, but we move them to nums[x], where x is the current index
     */
    public int floydsSlowAndFastPointerAlgorithm(int[] nums) {
        int slow = nums[0], fast = nums[0]; //PS. Do note that slow is not set to 0 but to nums[0]
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    /**
     * Approach: Can't use xor to find duplicates, because problem statement says nothing about the number of times the duplicate occurs
     * If it occurs once, then xor could have used
     * <p>
     * Hence have to rely on using the numbers index as a flag for detecting duplicates
     */
    public int findDuplicateUsingModifications(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (nums[Math.abs(val)] > 0) {
                nums[Math.abs(val)] *= -1;
            } else {
                //duplicate found
                return Math.abs(val);
            }
        }
        return -1;
    }
}
