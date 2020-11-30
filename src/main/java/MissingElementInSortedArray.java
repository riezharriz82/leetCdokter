/**
 * https://leetcode.com/problems/missing-element-in-sorted-array/ Premium
 * <p>
 * Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.
 * <p>
 * Input: A = [4,7,9,10], K = 1
 * Output: 5
 * Explanation:
 * The first missing number is 5.
 * <p>
 * Input: A = [4,7,9,10], K = 3
 * Output: 8
 * Explanation:
 * The missing numbers are [5,6,8,...], hence the third missing number is 8.
 * <p>
 * Input: A = [1,2,4], K = 3
 * Output: 6
 * Explanation:
 * The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 */
public class MissingElementInSortedArray {

    /**
     * Approach: Binary search using errichtos template
     * Input :  [4,7,9,10]
     * Missing: [0,2,3,7]
     * If k = 3 [T,T,F,F]
     * We need last index where count of missing numbers < k
     * Once that index is found, we reduce k by elements missing till that index and return nums[ans] + k
     * <p>
     * {@link MaximiseDistanceToClosestPerson} {@link SplitArrayIntoConsecutiveSubsequences} {@link ExamRoom} related tricky problems
     */
    public int missingElementBinarySearch(int[] nums, int k) {
        int low = 0, high = nums.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (missingNumbers(mid, nums) < k) { //T found, try to find a higher T
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        //return the delta from the nums[ans]
        return k - missingNumbers(ans, nums) + nums[ans];
    }

    private int missingNumbers(int mid, int[] nums) {
        return nums[mid] - nums[0] - mid;
    }

    /**
     * Approach: Do a linear scan, and find the total missing numbers between two adjacent numbers e.g [5,10,12]
     * There are 4 missing numbers between 5 and 10, so if k <= 4, we can directly return 5 + k
     * <p>
     * Find the appropriate bucket and return bucket[start] + k
     * <p>
     * This is insanely easy to think of, was so busy trying to think of binary search solution, couldn't even think that
     * a linear scan is possible
     */
    public int missingElementLinear(int[] nums, int k) {
        for (int i = 1; i < nums.length; i++) {
            int numsMissing = nums[i] - nums[i - 1] - 1;
            if (k <= numsMissing) { //if k lies in this bucket
                return nums[i - 1] + k;
            }
            k -= numsMissing; //reduce k by numsMissing of the current bucket
        }
        return nums[nums.length - 1] + k;
    }
}
