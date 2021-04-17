/**
 * https://leetcode.com/problems/maximum-xor-for-each-query/
 * <p>
 * You are given a sorted array nums of n non-negative integers and an integer maximumBit. You want to perform the following query n times:
 * <p>
 * Find a non-negative integer k < 2^maximumBit such that nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k is maximized. k is the answer to the ith query.
 * Remove the last element from the current array nums.
 * Return an array answer, where answer[i] is the answer to the ith query.
 * <p>
 * Input: nums = [0,1,1,3], maximumBit = 2
 * Output: [0,3,2,3]
 * Explanation: The queries are answered as follows:
 * 1st query: nums = [0,1,1,3], k = 0 since 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3.
 * 2nd query: nums = [0,1,1], k = 3 since 0 XOR 1 XOR 1 XOR 3 = 3.
 * 3rd query: nums = [0,1], k = 2 since 0 XOR 1 XOR 2 = 3.
 * 4th query: nums = [0], k = 3 since 0 XOR 3 = 3.
 * <p>
 * Input: nums = [2,3,4,7], maximumBit = 3
 * Output: [5,2,6,5]
 * Explanation: The queries are answered as follows:
 * 1st query: nums = [2,3,4,7], k = 5 since 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7.
 * 2nd query: nums = [2,3,4], k = 2 since 2 XOR 3 XOR 4 XOR 2 = 7.
 * 3rd query: nums = [2,3], k = 6 since 2 XOR 3 XOR 6 = 7.
 * 4th query: nums = [2], k = 5 since 2 XOR 5 = 7.
 * <p>
 * Input: nums = [0,1,2,2,5,7], maximumBit = 3
 * Output: [4,3,6,4,6,7]
 * <p>
 * Constraints:
 * nums.length == n
 * 1 <= n <= 105
 * 1 <= maximumBit <= 20
 * 0 <= nums[i] < 2maximumBit
 * nums is sorted in ascending order.
 */
public class MaximumXorForEachQuery {
    /**
     * Approach: Bitwise manipulation, trick to solve this problem is to realize that max xor can be = 2^k - 1 ie. a number with all the bits set
     * If current number in bits is 100101 we can convert it to 111111 by xor'ing it with it's complement 011010
     * <p>
     * Now the question reduces to find the complement and to solve that, remember that xor is complement of itself
     * ie. a^x = b => x = b^a
     * Here b = (2^k - 1) and a is the running xor, we can find a for each prefix and reverse the array in the end to format it.
     * <p>
     * Happy to solve it in a timely manner in the contest
     * <p>
     * {@link XORQueriesOfSubarray} {@link MaximumProductOfWordLengths}
     */
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int target = (1 << maximumBit) - 1, n = nums.length;
        int[] result = new int[n];
        int prefixXOR = 0;
        for (int i = 0; i < n; i++) {
            prefixXOR ^= nums[i];
            result[i] = (prefixXOR ^ target); //compute the complement for each index
        }
        reverseArray(result);
        return result;
    }

    private void reverseArray(int[] result) {
        int left = 0, right = result.length - 1;
        while (left < right) {
            int temp = result[left];
            result[left] = result[right];
            result[right] = temp;
            left++;
            right--;
        }
    }
}
