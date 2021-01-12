/**
 * https://leetcode.com/problems/largest-number/
 * <p>
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * Input: [10,2]
 * Output: "210"
 * <p>
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 */
public class LargestNumber {
    /**
     * Approach: To make the largest number, we can't simply sort the input in descending order and concatenate i.e. {34,30,9,5,3}
     * As ericto mentioned, consider the state of problem at ith index, if we are given the largest possible number before ith index, how can we extend the result?
     * <p>
     * I initially thought that we should concatenate the ith string to the largest number so far at the end or at the beginning and compare them
     * but this won't give correct results for all the cases.
     * <p>
     * The correct way would be to insert the ith string at all the possible places, and compare the largest string found so far.
     * <p>
     * I then got lost while trying to think about the ways to cleanly implement the solution. Then I realized that we need not actually insert the ith string
     * and compare, we can just simulate the comparison by "bubble sorting" i.e during the comparison stage, don't compare the numbers numerically
     * compare them by creating strings and finding whether concatenating it at the beginning leads a greater result or concatenating it at the end.
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return "";
        }
        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.toString(nums[i]);
        }
        for (int i = 1; i < n; i++) { //bubble sort
            for (int j = 0; j < i; j++) { //this order is important to understand, as we want to place a[i] first at the highest place possible
                //hence we iterate from 0th index, after swapping, a[i] will be switched to some other value, so we need to continue the check to
                //place new a[i] at the next greater index e.g [3,30,34]
                String pre = input[i] + input[j];
                String post = input[j] + input[i];
                if (pre.compareTo(post) > 0) { //ith index needs to be swapped with jth index
                    String temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (String num : input) {
            res.append(num);
        }
        String candidate = res.toString();
        for (int i = 0; i < candidate.length(); i++) { //tricky, remove all the leading zeroes
            if (candidate.charAt(i) != '0') {
                return candidate.substring(i);
            }
        }
        return "0";
    }
}
