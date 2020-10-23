/**
 * https://leetcode.com/problems/search-insert-position/
 * <p>
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * <p>
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * <p>
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 */
public class SearchInsertPosition {
    /**
     * Approach: Binary search
     * Transform the range into prefix/suffix of True/False
     * Here our condition is whether an element >= target
     * so input array would look something like [F,F,F,F,T,T,T,T]
     * Need to return the first T
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int ans = nums.length;
        //as errichto mentioned that this should be set to the value that needs to be returned in case any value with true condition isn't found
        //[1,3,5,6] target = 7
        //[F,F,F,F]
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) { //Mapped to true, need to go left to find the lowest index with true condition
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
