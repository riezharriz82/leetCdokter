/**
 * https://leetcode.com/problems/get-the-maximum-score/
 * <p>
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 * <p>
 * A valid path is defined as follows:
 * <p>
 * Choose array nums1 or nums2 to traverse (from index-0).
 * Traverse the current array from left to right.
 * If you are reading any value that is present in nums1 and nums2 you are allowed to change your path to the other array.
 * (Only one repeated value is considered in the valid path).
 * Score is defined as the sum of uniques values in a valid path.
 * <p>
 * Return the maximum score you can obtain of all possible valid paths.
 * <p>
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * <p>
 * Input: nums1 = [2,4,5,8,10], nums2 = [4,6,8,9]
 * Output: 30
 * Explanation: Valid paths:
 * [2,4,5,8,10], [2,4,5,8,9], [2,4,6,8,9], [2,4,6,8,10],  (starting from nums1)
 * [4,6,8,9], [4,5,8,10], [4,5,8,9], [4,6,8,10]    (starting from nums2)
 * The maximum is obtained with the path in green [2,4,6,8,10].
 */
public class FindMaximumScoreBySwitchingPaths {
    /**
     * Approach: Two pointer algorithm, similar to merging two sorted array.
     * Keep accumulating the sum observed in nums1 (sum1) and nums2 (sum2), until they reach a common point.
     * After reaching the common point, we can check which sum is greater and reset the sum1 and sum2 to the max(sum1, sum2)
     * End result would be the max(sum1, sum2)
     * <p>
     * An alternate and generic way of solving this problem would be to visualize it as a graph problem instead.
     * Create a graph by considering the int values as key and the next levels as value, since there can be multiple values as next level it should be list
     * map<int, list<int>> graph
     * now do a dfs from the first value of nums1 and nums2 and return the max value obtained from both of them
     * Since there are repeated subproblems, we can memoize it for easy gains.
     * https://leetcode.com/problems/get-the-maximum-score/discuss/767922/Java-19-lines-dfs%2Bmemo-with-line-by-line-explanation-easy-to-understand
     */
    public int maxSum(int[] nums1, int[] nums2) {
        int pointer1 = 0, pointer2 = 0, mod = 1000000007;
        long sum1 = 0, sum2 = 0;
        while (pointer1 < nums1.length && pointer2 < nums2.length) {
            if (nums1[pointer1] < nums2[pointer2]) {
                sum1 += nums1[pointer1];
                pointer1++;
            } else if (nums2[pointer2] < nums1[pointer1]) {
                sum2 += nums2[pointer2];
                pointer2++;
            } else {
                long max = Math.max(sum1, sum2) + nums1[pointer1];
                sum1 = max;
                sum2 = max;
                pointer1++;
                pointer2++;
            }
            //DON"T MODULO SUM1 AND SUM2 HERE
            // Modulo shouldn't be used during comparison, because if the sum1 is 1e9+7 and sum2 is 1e9+4, after modulo it will become 0 and 1e9+4
            // and will lead to incorrect results
        }
        //to take care of the longer array, add the remaining elements
        for (int i = pointer2; i < nums2.length; i++) {
            sum2 += nums2[i];
        }
        for (int i = pointer1; i < nums1.length; i++) {
            sum1 += nums1[i];
        }
        return (int) (Math.max(sum1, sum2) % mod);
    }
}
