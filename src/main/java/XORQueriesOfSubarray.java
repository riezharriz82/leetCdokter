/**
 * https://leetcode.com/problems/xor-queries-of-a-subarray/
 * <p>
 * Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri],
 * for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ).
 * Return an array containing the result for the given queries.
 * <p>
 * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * Output: [2,7,14,8]
 * Explanation:
 * The binary representation of the elements in the array are:
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * The XOR values for queries are:
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 */
public class XORQueriesOfSubarray {
    /**
     * Approach: XOR is the inverse function of XOR ie if c = a^b, then a = b^c
     * This problem is similar to {@link SubarraySumEqualsK} except instead of sum, problem talks about subarray xor
     * <p>
     * so if the xor of a[0...i] is X and the xor of a[0 to k] is Y then xor of a[k+1, i] is X ^ Y
     * <p>
     * {@link MaximumXorForEachQuery} related XOR problem
     */
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int currentXOR = 0;
        int[] prefixXOR = new int[n];
        for (int i = 0; i < n; i++) {
            currentXOR ^= arr[i];
            prefixXOR[i] = currentXOR;
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            if (left == 0) {
                res[i] = prefixXOR[right];
            } else {
                res[i] = prefixXOR[right] ^ prefixXOR[left - 1];
            }
        }
        return res;
    }
}
