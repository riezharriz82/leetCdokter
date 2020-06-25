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
    //You must not modify the array (assume the array is read only).
    public int floydsSlowAndFastPointerAlgorithm(int[] nums) {
        int slow = nums[0], fast = nums[0];
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

    public int findDuplicateUsingModifications(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (nums[Math.abs(val)] > 0) {
                nums[Math.abs(val)] *= -1;
            } else {
                return Math.abs(val);
            }
        }
        return -1;
    }
}
