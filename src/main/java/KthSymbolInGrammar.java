/**
 * https://leetcode.com/problems/k-th-symbol-in-grammar/
 * On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * <p>
 * Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
 * <p>
 * Input: N = 4, K = 5
 * Output: 1
 * <p>
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 */
public class KthSymbolInGrammar {
    /**
     * Similar to {@link FindKthBitInNthBinaryString}
     * Use binary search to recursively find the value
     */
    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        int low = 0, high = (1 << (N - 1)) - 1;
        int mid = low + (high - low) / 2;
        if (K - 1 <= mid) {
            return kthGrammar(N - 1, K);
        } else {
            return kthGrammar(N - 1, K - mid - 1) == 0 ? 1 : 0;
        }
    }
}
