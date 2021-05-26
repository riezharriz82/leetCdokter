/**
 * <pre>
 * https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
 *
 * A decimal number is called deci-binary if each of its digits is either 0 or 1 without any leading zeros. For example, 101 and 1100 are deci-binary, while 112 and 3001 are not.
 *
 * Given a string n that represents a positive decimal integer, return the minimum number of positive deci-binary numbers needed so that they sum up to n.
 *
 * Input: n = "32"
 * Output: 3
 * Explanation: 10 + 11 + 11 = 32
 *
 * Input: n = "82734"
 * Output: 8
 * Example 3:
 *
 * Input: n = "27346209830709182346"
 * Output: 9
 *
 * Constraints:
 * 1 <= n.length <= 10^5
 * n consists of only digits.
 * n does not contain any leading zeros and represents a positive integer.
 *
 * </pre>
 */
public class PartitioningIntoMinimumNumberOfDeciBinaryNumbers {
    /**
     * Approach: Tricky Greedy, keep track of max digit encountered in the string.
     * <p>
     * Notice that if input was 8 we would need 8 numbers, if input was 345 we would need 3 ones for first digit, 4 ones for second digit and 5 ones for third digit
     * Since each number can contribute at most 1 for a specific digit, we need to have at least 5 such numbers.
     * For the other digits we can place 0 to lower the sum required.
     * <p>
     * Build the answer digit by digit.
     * <p>
     * Took me ~20 minutes to find the pattern but still happy to solve this on my own.
     * <p>
     * {@link MaximumScoreFromRemovingSubstrings} {@link SmallestRange2} {@link SplitArrayIntoConsecutiveSubsequences} {@link MinimumNumberOfKConsecutiveBitFlips}
     * {@link FindPermutation} {@link CarFleet} related tricky greedy problems
     */
    public int minPartitions(String n) {
        char max = '0';
        for (char c : n.toCharArray()) {
            max = (char) Math.max(max, c);
        }
        return max - '0';
    }
}
