import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/4sum-ii/
 * <p>
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 * <p>
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * <p>
 * Input:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class FourSum2 {
    /**
     * Approach: Store pair wise sum of each combinations of A and B in a map and store pair wise sum of each combinations
     * of C and D in another map
     * Now you need to find inverse of key of first map in second map and multiply their count
     * <p>
     * Time Complexity: O(n^2)
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> firstPair = new HashMap<>();
        HashMap<Integer, Integer> secondPair = new HashMap<>();
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int firstPairSum = A[i] + B[j];
                int secondPairSum = C[i] + D[j];
                firstPair.put(firstPairSum, firstPair.getOrDefault(firstPairSum, 0) + 1);
                secondPair.put(secondPairSum, secondPair.getOrDefault(secondPairSum, 0) + 1);
            }
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : firstPair.entrySet()) {
            int requiredSum = -entry.getKey();
            int requiredPairs = secondPair.getOrDefault(requiredSum, 0);
            if (requiredPairs > 0) {
                res += entry.getValue() + requiredPairs; //important to multiply ie. if first pair occurred 3 times and second pair 4 times
                //total valid combinations are 12, not 7
                //initially I added them, then got WA :P
            }
        }
        return res;
    }

    /**
     * Approach: Store the numbers of last array in hashmap and generate all combinations of A,B,C and check if the inverse
     * exists in hashmap or not
     * Time Complexity: O(n^3)
     */
    public int fourSumCountTLE(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<>(); //map of values to no of occurrences
        for (int i : D) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < C.length; k++) {
                    int curSum = A[i] + B[j] + C[k];
                    res += map.getOrDefault(-curSum, 0);
                }
            }
        }
        return res;
    }
}
