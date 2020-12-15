import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/largest-divisible-subset/
 * <p>
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 * <p>
 * Si % Sj = 0 or Sj % Si = 0.
 * <p>
 * If there are multiple solutions, return any subset is fine.
 * <p>
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * <p>
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {
    /**
     * Approach: Similar to finding LIS. Only trick is that problem asks for finding valid subsets, not subsequences
     * In subsets, order does not matter. So we can sort the array and can check whether the current number extends any previous index
     * <p>
     * I initially solved the problem without sorting the array, got WA and then went in wrong direction of maintaining two results
     * one from left and another from right
     * <p>
     * Trick to solving finding longest valid subsequences is to always check how can we extend the result of previous subsequence
     * Given a list of values [E, F, G] sorted in ascending order (i.e. E < F < G), and the list itself forms a divisible subset as described in the problem,
     * then we could extend the subset without enumerating all numbers in the subset, in the following two cases:
     * Corollary I: For any value that can be divided by the largest element in the divisible subset, by adding the new value into the subset,
     * one can form another divisible subset, i.e. for all h, if h % G == 0, then [E, F, G, h] forms a new divisible subset.
     * <p>
     * Corollary II: For all value that can divide the smallest element in the subset, by adding the new value into the subset,
     * one can form another divisible subset, i.e. for all d, if E % d == 0, then [d, E, F, G] forms a new divisible subset.
     * <p>
     * {@link RussianDollEnvelopes} related problem
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int[] size = new int[n];
        int[] parent = new int[n]; //required because we need to print the subset, not just return the largest subset
        //it keeps track of the index of the previous element whose result, current index extended
        for (int i = 0; i < n; i++) {
            parent[i] = i; //initially everyone is its own parent
        }
        int maxSizeIndex = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (size[i] < size[j] + 1) { //if found a longer result, update the previous index
                        size[i] = size[j] + 1;
                        parent[i] = j;
                    }
                }
            }
            if (size[i] > size[maxSizeIndex]) {
                maxSizeIndex = i;
            }
        }
        //iterate backwards until we find the last index
        List<Integer> result = new ArrayList<>();
        int index = maxSizeIndex;
        while (index != parent[index]) {
            result.add(nums[index]);
            index = parent[index];
        }
        result.add(nums[index]);
        return result;
    }
}
