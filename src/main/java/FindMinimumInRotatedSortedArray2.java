/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 * <p>
 * Input: [3,4,5,1,2]
 * Output: 1
 * <p>
 * Input: [2,2,2,0,1]
 * Output: 0
 */
public class FindMinimumInRotatedSortedArray2 {
    /**
     * Worst case is O(n), Errichto's method does not work here, consider {3,3,1,3}
     */
    public int findMinDuplicatesAllowed(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (low == high) { // base conditions, single element remaining
                return nums[low];
            } else if (low + 1 == high) { //base condition, two elements remaining
                return Math.min(nums[low], nums[high]);
            } else if (nums[mid] > nums[high]) { //second half not sorted
                low = mid;
            } else if (nums[low] > nums[mid]) { //first half not sorted
                high = mid;
            } else { //both halves are sorted
                //tricky inputs are {1,2,3}, {3,0,1,1,2,3,3,3,3,3,3}, {3,3,3,3,3,3,3,0,1,2,3}
                //from the mid you need to find smaller indices in the left or right part
                //whichever direction you find smaller value, need to continue in that part in next iteration
                int rightTemp = mid;
                while (rightTemp < high) {
                    if (nums[rightTemp] != nums[mid]) {
                        break;
                    }
                    rightTemp++;
                }
                int leftTemp = mid;
                while (leftTemp > low) {
                    if (nums[leftTemp] != nums[mid]) {
                        break;
                    }
                    leftTemp--;
                }
                if (nums[leftTemp] < nums[rightTemp]) {
                    high = leftTemp; //need to go in left part
                } else {
                    low = rightTemp;
                }
            }
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
     * It's not sufficient to just compare adjacent elements with the mid, need to compare with the last element (comparison with first element also works)
     * [3,4,5,1,2] -> [F,F,F,T,T]
     * [1,2,3,4,5] -> [T,T,T,T,T]
     * [5,4,3,2,1] -> [F,F,F,F,T]
     * <p>
     * Need to find the first true index
     */
    public int findMinErrichto(int[] nums) {
        int low = 0, high = nums.length - 1, ans = -1;
        int last_index = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= nums[last_index]) { //True found, update the ans, and recur left to find a even smaller True
                ans = mid;
                high = mid - 1;
            } else { //False found, recur right to find True
                low = mid + 1;
            }
        }
        return nums[ans];
    }
}
