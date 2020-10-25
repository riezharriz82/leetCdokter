import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/constrained-subsequence-sum/
 * <p>
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for
 * every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 * <p>
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array,
 * leaving the remaining elements in their original order.
 * <p>
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 */
public class ConstrainedSubsequenceSum {
    /**
     * Approach: Extension of treeMap solution. Instead of using treeMap, used a monotonic increasing queue to keep track of sliding maximum
     * TimeComplexity: O(n)
     *
     */
    public int constrainedSubsetSumUsingDeque(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + (queue.isEmpty() ? 0 : queue.peekFirst()));
            while (!queue.isEmpty() && queue.peekLast() < dp[i]) {
                //remove all elements smaller than dp[i]
                queue.pollLast();
            }
            queue.add(dp[i]);
            if (i >= k && dp[i - k] == queue.peekFirst()) {
                //very critical to understand why queue.peekFirst() == dp[i-k] can handle duplicates, because we push into the queue only if current number >= queue.peekLast()
                //so we push duplicates into the queue.
                //need to remove the prefix sum falling out of the window, also pollFirst() only if the value equals the prefix sum falling outside the window
                // because there is a chance that it has already been polled
                queue.pollFirst();
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * Approach: Extension of brute force approach, instead of iterating over the past k indices to find the max,
     * store the result of the past k indices in a treeMap and use lastKey() to find the max value
     * TimeComplexity: O(nlogk)
     */
    public int constrainedSubsetSumUsingTreeMap(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            //max to handle negative lastKey()
            dp[i] = Math.max(nums[i], nums[i] + (map.isEmpty() ? 0 : map.lastKey()));
            map.put(dp[i], map.getOrDefault(dp[i], 0) + 1);
            if (i >= k) {
                //need to remove the prefix sum falling out of the window
                int key = dp[i - k];
                int val = map.get(key);
                if (val == 1) {
                    map.remove(key);
                } else {
                    map.put(key, val - 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     * Approach: For every index i, check the result of the previous i-k indexes and update the result at dp[i]
     * Basically need to find the maximum for the previous k indexes in order to compute the result for dp[i]
     * Time Complexity: O(n*k)
     */
    public int constrainedSubsetSumBruteForceTLE(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            int maxOffset = i - k;
            for (int j = i - 1; j >= 0 && j >= maxOffset; j--) {
                dp[i] = Math.max(dp[i], nums[i] + dp[j]);
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
