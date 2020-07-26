/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Input: [3,4,5,1,2]
 * Output: 1
 * <p>
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */
public class FindMinimumInRotatedSortedArray2 {
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

    //https://leetcode.com/problems/find-minimum-in-rotated-sorted-array
    public int findMinSimplified(int[] nums) {
        //Idea is to think of input array as two parts {low, mid} and {mid+1, high}
        //Only one of them can be sorted, check if left half is sorted, then you need to look into right half in next iteration
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid + 1; // need to increment by 1 because second part of array is {mid+1, high}
            }
        }
        return nums[low];
    }

    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (low == high) { // base conditions, single element remaining
                return nums[low];
            } else if (low + 1 == high) { //base condition, two elements remaining
                return Math.min(nums[low], nums[high]);
            } else if (nums[mid] < nums[high]) { //right half is sorted, need to look in left half
                high = mid;
            } else { //right half unsorted, look in right half
                low = mid;
            }
        }
        return -1;
    }
}
