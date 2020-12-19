/**
 * https://leetcode.com/problems/divide-chocolate/ Premium
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 * <p>
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts, each piece consists of some consecutive chunks.
 * <p>
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 * <p>
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 * <p>
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * <p>
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * <p>
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 */
public class DivideChocolates {
    /**
     * Approach: Similar to {@link SplitArrayLargestSum} but with tighter bounds
     * O(N*K) won't AC
     */
    public int maximizeSweetness(int[] sweetness, int K) {
        int[][] dp = new int[sweetness.length][K + 2];
        return recur(sweetness, K + 1, 0, dp);
    }

    private int recur(int[] sweetness, int k, int index, int[][] memoized) {
        if (k == 0) {
            if (index == sweetness.length) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (index == sweetness.length) {
            return Integer.MIN_VALUE;
        } else if (memoized[index][k] != 0) {
            return memoized[index][k];
        }
        int maximumMinimumSweetness = Integer.MIN_VALUE;
        int currentSweetness = 0;
        for (int i = index; i < sweetness.length; i++) {
            currentSweetness += sweetness[i];
            int remain = recur(sweetness, k - 1, i + 1, memoized);
            int minimumSweetness = Math.min(currentSweetness, remain);
            maximumMinimumSweetness = Math.max(maximumMinimumSweetness, minimumSweetness);
        }
        return memoized[index][k] = maximumMinimumSweetness;
    }

    /**
     * Approach: Greedy + Binary Search
     * Answer lies between the smallest piece available to the largest piece possible
     * Pick a number = mid and try to satisfy as many friends possible by allocating minimum sweetness == mid
     * If we can satisfy less friends than required, we have to reduce the minimum sweetness possible
     * otherwise we can try increasing the minimum sweetness
     */
    public int maximizeSweetnessGreedy(int[] sweetness, int K) {
        int smallestPiece = Integer.MAX_VALUE, largestPiece = 0;
        for (int val : sweetness) {
            largestPiece += val;
            smallestPiece = Math.min(smallestPiece, val);
        }
        if (K == 0) {
            return largestPiece;
        }
        int low = smallestPiece, high = largestPiece, ans = -1;
        int requiredPersons = K + 1; // +1 due to including the current person
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (personsSatisfied(mid, sweetness) < requiredPersons) { //we can't satisfy required person, need to try with a lower value
                high = mid - 1;
            } else { //try a larger value to see if it can satisfy as well
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }

    //task is to see how many persons can be satisfied by trying to give a minimumSweetness to each person
    //this takes care of multiple use cases [1,2,3], 6 -> should return 1
    //[1,2,3,4], 6 should return 1 because last piece 4 can't satisfy another person, this was my mistake during my initial thought process
    private int personsSatisfied(int minimumSweetness, int[] sweetness) {
        int splits = 0, curSweetness = 0;
        for (int val : sweetness) {
            curSweetness += val;
            if (curSweetness >= minimumSweetness) {
                curSweetness = 0;
                splits++;
            }
        }
        return splits;
    }
}
