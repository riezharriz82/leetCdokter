import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
 * <p>
 * In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray of length K
 * and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.
 * <p>
 * Return the minimum number of K-bit flips required so that there is no 0 in the array.  If it is not possible, return -1.
 * <p>
 * Input: A = [0,1,0], K = 1
 * Output: 2
 * Explanation: Flip A[0], then flip A[2].
 * <p>
 * Input: A = [1,1,0], K = 2
 * Output: -1
 * Explanation: No matter how we flip subarrays of size 2, we can't make the array become [1,1,1].
 * <p>
 * Input: A = [0,0,0,1,0,1,1,0], K = 3
 * Output: 3
 * Explanation:
 * Flip A[0],A[1],A[2]: A becomes [1,1,1,1,0,1,1,0]
 * Flip A[4],A[5],A[6]: A becomes [1,1,1,1,1,0,0,0]
 * Flip A[5],A[6],A[7]: A becomes [1,1,1,1,1,1,1,1]
 * <p>
 * Note:
 * 1 <= A.length <= 30000
 * 1 <= K <= A.length
 */
public class MinimumNumberOfKConsecutiveBitFlips {
    /**
     * Approach: Greedy; Brute force would be to flip k consecutive bits whenever we see 0, TimeComplexity: O(nk)
     * <p>
     * If we could avoid actually flipping the bits and somehow could track the no of times a bit was flipped, then we could
     * deduce the current state of the bit i.e. if A[i] == 1 and we have performed even flips, so A[i] is still 1
     * We do that by tracking the indexes waiting to be flipped in a queue. Size of queue will tell us the no of flips performed
     * <p>
     * {@link BulbSwitcher4}
     */
    public int minKBitFlips(int[] A, int K) {
        ArrayDeque<Integer> queue = new ArrayDeque<>(); //maintains sliding window of indices till which we have flipped
        int n = A.length, flips = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] == 0) {
                if (queue.size() % 2 == 0) { //0 or even no of flips performed will turn 0 to 0, need extra flip
                    if (i + K - 1 >= n) {
                        return -1;
                    }
                    queue.addLast(i + K - 1); //push the index till we have performed virtual flip
                    flips++;
                }
                //else odd no of flips would have automatically turned 0 into 1, no extra flip required
            } else {
                if (queue.size() % 2 == 1) { //odd no of flips would have turned 1 into 0, need extra flip
                    if (i + K - 1 >= n) {
                        return -1;
                    }
                    queue.addLast(i + K - 1);
                    flips++;
                }
            }
            if (queue.getFirst() == i) { //if the index matches the head of the queue, pop it
                queue.removeFirst();
            }
        }
        return flips;
    }

    public int minKBitFlipsBruteForce(int[] A, int K) {
        int flips = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                if (i + K - 1 >= A.length) { //if unable to flip k consecutive bits, return -1
                    return -1;
                }
                flips++;
                for (int j = i; j <= i + K - 1; j++) { //flip all the k consecutive bits
                    A[j] = (A[j] == 1) ? 0 : 1;
                }
            }
        }
        return flips;
    }
}
