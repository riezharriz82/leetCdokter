/**
 * https://leetcode.com/problems/minimum-operations-to-make-array-equal/
 * <p>
 * You have an array arr of length n where arr[i] = (2 * i) + 1 for all valid values of i (i.e. 0 <= i < n).
 * <p>
 * In one operation, you can select two indices x and y where 0 <= x, y < n and subtract 1 from arr[x] and add 1 to arr[y] (i.e. perform arr[x] -=1 and arr[y] += 1).
 * The goal is to make all the elements of the array equal. It is guaranteed that all the elements of the array can be made equal using some operations.
 * <p>
 * Given an integer n, the length of the array. Return the minimum number of operations needed to make all the elements of arr equal.
 * <p>
 * Input: n = 3
 * Output: 2
 * Explanation: arr = [1, 3, 5]
 * First operation choose x = 2 and y = 0, this leads arr to be [2, 3, 4]
 * In the second operation choose x = 2 and y = 0 again, thus arr = [3, 3, 3].
 * <p>
 * Input: n = 6
 * Output: 9
 * <p>
 * Constraints:
 * 1 <= n <= 10^4
 */
public class MinimumOperationsToMakeArrayEqual {
    /**
     * Approach: All elements need to be made equal and you have to perform pairwise operations. In case there was no restrictions on pairwise operations,
     * we have to transform each number to the median of the array, ie. similar to {@link MinimumMovesToEqualArrayElements2}
     * <p>
     * But now the target state would be the (sum of elements/n), so we can just consider incrementing the smaller numbers to target state.
     * <p>
     * Can also be solved in O(1) time using the power of maths.
     * <p>
     * {@link GlobalAndLocalInversions} {@link MinimumNumberOfOperationsToReachTargetArray} {@link MinimumNumberOfOperationsToMoveAllBallsToEachBox}
     */
    public int minOperations(int n) {
        int[] inp = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            inp[i] = 2 * i + 1;
            sum += inp[i];
        }
        int target = sum / n, res = 0;
        for (int i = 0; i < n; i++) {
            if (inp[i] >= target) { //consider only the first half ie. smaller numbers
                break;
            }
            res += (target - inp[i]);
        }
        return res;
    }
}
