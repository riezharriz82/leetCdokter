/**
 * https://leetcode.com/problems/counting-bits/
 * <p>
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's
 * in their binary representation and return them as an array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 * <p>
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
public class CountingBits {
    public int[] countBitsUsingDP(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            //a number can be visualized as having two parts e.g 10001101 can be visualized as {1000110} and {1}
            // {1000110} is num >> 1 and it's set bit count has already been processed
            // {1} depends on whether the num is even or odd, (i & 1) is equivalent to i%2
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }
}
