import javafx.util.Pair;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * <p>
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class PartitionEqualSubsetSum {
    /**
     * Approach: The crux of the problem is to find a subset with sum = totalSum/2
     * So every element has two options, either be a part of the left subset or right subset.
     * <p>
     * It can be simplified further by not considering the right sum at all, just consider whether an element is picked, increment the sum and index and recurse
     * or an element is skipped, increment the index and recurse
     * <p>
     * But I find it more logical this way
     */
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % 2 == 1) { //odd sum can't be divided into two
            return false;
        }
        HashMap<Pair<Integer, Integer>, Boolean> map = new HashMap<>();
        return recur(nums, 0, 0, totalSum / 2, 0, map);
    }

    private boolean recur(int[] nums, int leftSum, int rightSum, int targetSum, int index, HashMap<Pair<Integer, Integer>, Boolean> map) {
        if (leftSum > targetSum || rightSum > targetSum) {
            return false;
        } else if (leftSum == targetSum || rightSum == targetSum) {
            return true;
        } else if (map.containsKey(new Pair<>(leftSum, rightSum))) {
            return map.get(new Pair<>(leftSum, rightSum));
        } else if (map.containsKey(new Pair<>(rightSum, leftSum))) { //minor optimization trick
            //if we are able to find two partitions with sum {10,4} we can always flip the labels around to find partitions with sum {4,10}
            return map.get(new Pair<>(rightSum, leftSum));
        } else {
            Pair<Integer, Integer> key1 = new Pair<>(leftSum, rightSum);
            Pair<Integer, Integer> key2 = new Pair<>(rightSum, leftSum); //optimization trick, will get AC without this trick
            if (recur(nums, leftSum + nums[index], rightSum, targetSum, index + 1, map)) {
                map.put(key1, true);
                map.put(key2, true);
                return true;
            }
            if (recur(nums, leftSum, rightSum + nums[index], targetSum, index + 1, map)) {
                map.put(key1, true);
                map.put(key2, true);
                return true;
            }
            map.put(key1, true);
            map.put(key2, true);
            return false;
        }
    }
}
