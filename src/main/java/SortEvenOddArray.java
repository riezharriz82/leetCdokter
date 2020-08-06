/**
 * https://leetcode.com/problems/sort-array-by-parity/
 * <p>
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
 * <p>
 * You may return any answer array that satisfies this condition.
 * <p>
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 */
public class SortEvenOddArray {

    /**
     * Two pointer approach, start pointer should always point to an even number, end pointer to an odd number
     * If start is odd, swap with value present at end, decrement end pointer because we know for a fact that the value swapped is odd
     * If start is even, just increment start pointer
     * Every step we shorten the range, so loop will terminate
     */
    public int[] sortArrayByParity(int[] A) {
        int begin = 0, end = A.length - 1;
        while (begin < end) {
            if (A[begin] % 2 == 1) {
                int temp = A[begin];
                A[begin] = A[end];
                A[end] = temp;
                end--;
            } else {
                begin++;
            }
        }
        return A;
    }
}
