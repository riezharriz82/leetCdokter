/**
 * https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/
 * <p>
 * Given an array of integers cost and an integer target. Return the maximum integer you can paint under the following rules:
 * <p>
 * The cost of painting a digit (i+1) is given by cost[i] (0 indexed).
 * The total cost used must be equal to target.
 * Integer does not have digits 0.
 * Since the answer may be too large, return it as string.
 * <p>
 * If there is no way to paint any integer given the condition, return "0".
 * <p>
 * Input: cost = [4,3,2,5,6,7,2,5,5], target = 9
 * Output: "7772"
 * Explanation:  The cost to paint the digit '7' is 2, and the digit '2' is 3. Then cost("7772") = 2*3+ 3*1 = 9. You could also paint "977", but "7772" is the largest number.
 */
public class LargestNumberWithDigitsThatSumUpToTarget {

    String res = "0";

    /**
     * Approach: {@link CombinationSum4} is similar problem
     * The crux to solving the problem is to understand the intermediate steps i.e if cost of using digit 2 is 5 and largest number possible with cost 8 is 982
     * how can i use that to build solution for cost 10? i can append 2 to 982 and get a possible candidate 2982
     */
    public String largestNumber(int[] cost, int target) {
        String[] memoization = new String[target + 1];
        return recur(cost, target, memoization); //recurse down from target to 0
    }

    private String recur(int[] cost, int target, String[] memoization) {
        if (target < 0) {
            return null;
        } else if (target == 0) {
            return "";
        }
        if (memoization[target] != null) {
            return memoization[target];
        }
        String res = "0";
        for (int i = 0; i < 9; i++) {
            String result = recur(cost, target - cost[i], memoization);
            if (result != null && !result.equals("0")) { //if a valid result is reached by picking i+1 digit, we got a possible candidate for current target
                String candidate = (i + 1) + result; //order of concatenation does not really matter
                //because consider target 3, it can be reached from 1 + 2 and 2 + 1
                //we will consider both of them and the max candidate will be stored
                if (candidate.length() == res.length() && candidate.compareTo(res) > 0) { //compare them via compareTo() only if the length of strings match
                    res = candidate;
                } else if (candidate.length() > res.length()) { //if candidate has more digits than current result
                    res = candidate;
                }
            }
        }
        return memoization[target] = res;
    }

    /**
     * Approach: My initial recursion solution, it can't be converted to top down DP because
     * 1. we are starting from 0
     * 2. we are not using the results of the recursion in a memoization friendly maanner
     */
    public String largestNumberRecursion(int[] cost, int target) {
        recur(cost, target, "", 0);
        return res;
    }

    private void recur(int[] cost, int target, String candidate, int currentCost) {
        if (currentCost > target) {
            return;
        }
        if (currentCost == target) {
            if (candidate.length() == res.length() && candidate.compareTo(res) > 0) {
                res = candidate;
            } else if (candidate.length() > res.length()) {
                res = candidate;
            }
        }
        for (int i = 0; i < 9; i++) {
            recur(cost, target, candidate + (i + 1), currentCost + cost[i]);
        }
    }
}
