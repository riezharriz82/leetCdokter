import java.util.Arrays;

/**
 * https://leetcode.com/problems/smallest-range-ii/
 * <p>
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).
 * <p>
 * After this process, we have some array B.
 * <p>
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 * <p>
 * Input: A = [1], K = 0
 * Output: 0
 * Explanation: B = [1]
 * <p>
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 * <p>
 * Input: A = [1,3,6], K = 3
 * Output: 3
 * Explanation: B = [4,6,3]
 */
public class SmallestRange2 {
    /**
     * Approach: Greedy, given the input length of 10^4, it's sure that recursion won't work here, so greedy comes to the rescue
     * Let D = Max - Min. D is now the baseline answer and we determine whether we can do better than D.
     * If K >= D, the optimal is to apply the same change to every number, and hence the answer is still D. We can easily prove this by contradiction.
     * Else, there must be a "split" somewhere, i.e., the first part of the array is increased by K, and the remainder part decreased by K.
     * We simply loop through the array to find the best split! That's it!
     * <p>
     * In my initial solution, I considered median as the benchmark and decided to do +k for num <= median and -k for num > median
     * So that everything gets balanced, higher becomes lower and lower becomes higher but it didn't work, gave WA :P
     * <p>
     * {@link MaximumSumCircularSubarray} another out of the world problem
     */
    public int smallestRangeII(int[] A, int k) {
        Arrays.sort(A);
        int n = A.length;
        int ans = A[n - 1] - A[0];
        for (int i = 0; i < n - 1; i++) {
            //consider i as the split, [0..i] is increased by +k, [i+1..n] is decreased by k
            //since array is sorted, a[i] would be the largest number and a[i+1] would be the smallest number
            //current min is a[0] + k and current max is a[n-1] - k
            //so this acts like a sliding window of size 1,2...n-1
            //the reason we are keeping track of max and min is we have to return the smallest difference between max and min of transformed input
            //when we perform +k on A[i], it's not necessary that it will become the largest number in the array
            //candidate largest number is A[n-1]-k e.g. [2,2,7]
            //similar logic for tracking min, candidate smallest number is a[0]+k
            //this logic is very subtle, took me a lot of time to understand
            int max = Math.max(A[i] + k, A[n - 1] - k);
            int min = Math.min(A[i + 1] - k, A[0] + k);
            ans = Math.min(ans, max - min);
        }
        return ans;
    }
}