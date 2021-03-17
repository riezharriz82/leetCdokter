package experience;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApnaCoderByteTest {
    /**
     * Given an array find whether there exists a set of numbers that sum up to the largest number in the array
     * e.g given {4,6,23,10,1,3}, the task is to find a set of numbers that sums to 23.
     * <p>
     * This problem is a variant of {@link PartitionEqualSubsetSum}
     * A lil gotcha during implementation was to remove the largest number from the array before recursion otherwise the answer
     * would always be true because the largest number itself would be part of a set that sums to the largest number.
     * <p>
     * During implementation I panicked a bit on how to ensure the size of the set should be at least 2. I was going on the lines of keeping a
     * argument which keeps track of set size but then realized we could simply remove the largest element.
     * <p>
     * CoderByte platform did not provided input constraints so I was not sure whether I need to memoize the solution or not.
     * Upon memoization 7/10 test cases passed and if I removed the memoization, all test cases passed.
     * The platform does not allow multiple submissions, so I could only execute the code afterwards.
     * <p>
     * Upon debugging the issue in memoization, I realized the problem was the sum can be negative, hence the index in the array would be out of bounds.
     * The platform was catching the exception and was returning a default value of false.
     * I am expecting my test cases that actually passed were <7 but due to the platform's grace, it bumped to 7.
     * <p>
     * The second problem asked was exactly {@link JumpGame2} which I did by n^2 DP.
     * I did not want to take any chances by implementing the O(n) solution as the platform does not allow for resubmission.
     * All 10/10 test cases passed but upon checking the code afterwards, realized that I didn't consider the fact that an index can be unreachable as well.
     * i.e I forgot to add a check of ensuring dp[i] != Integer.MAX_VALUE before updating the steps that are reachable from ith index.
     * Again, saved by the inefficiency of the platform.
     */
    public static boolean ArrayChallenge(int[] arr) {
        // code goes here
        int max = Integer.MIN_VALUE;
        for (int val : arr) {
            max = Math.max(val, max);
        }
        List<Integer> filtered = new ArrayList<>();
        for (int val : arr) {
            if (val != max) {
                filtered.add(val);
            }
        }
        Map<Pair<Integer, Integer>, Boolean> dp = new HashMap<>();
        return recur(filtered, 0, max, dp);
    }

    private static boolean recur(List<Integer> arr, int index, int target, Map<Pair<Integer, Integer>, Boolean> dp) {
        if (index == arr.size()) {
            return target == 0;
        }
        Pair<Integer, Integer> key = new Pair<>(index, target);
        if (target == 0) {
            return true;
        } else if (dp.containsKey(key)) {
            return dp.get(key);
        }
        boolean result = recur(arr, index + 1, target - arr.get(index), dp) || recur(arr, index + 1, target, dp);
        dp.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.print(ArrayChallenge(new int[]{54, 49, 1, 0, 7, 4}));
        System.out.print(ArrayChallenge(new int[]{-2, -3, -4, -1, 100}));
        System.out.print(ArrayChallenge(new int[]{10, 12, 500, 1, -5, 1, 0}));
    }
}
