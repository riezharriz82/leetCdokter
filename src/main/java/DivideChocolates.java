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
     * For detailed explanation see {@link SplitArrayLargestSum}
     */
    public int maximizeSweetnessGreedy(int[] sweetness, int K) {
        int largestBiggestPiece = 0, smallestSinglePiece = Integer.MAX_VALUE;
        for (int sweet : sweetness) {
            largestBiggestPiece += sweet;
            smallestSinglePiece = Math.min(smallestSinglePiece, sweet);
        }
        if (K == 0) {
            return largestBiggestPiece;
        }
        int low = smallestSinglePiece, high = largestBiggestPiece;
        int ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int currentSplits = chunksSplittedInto(sweetness, mid);
            if (currentSplits <= K + 1) {
                high = mid - 1;
                ans = mid;
            } else if (currentSplits > K + 1) {
                low = mid + 1;
            }
        }
        return ans;
    }

    //find how many chunks can be formed from the input array given each friend must receive a minimum amount of sweetness
    //i.e. subarray sum should be minimum equal to target sum
    private int chunksSplittedInto(int[] sweetness, int minimumSweetness) {
        int chunks = 1, curSum = 0;
        for (int sweet : sweetness) {
            curSum += sweet;
            if (curSum > minimumSweetness) { //not >= {1,2,3} {target = 6} should give chunk = 1 not 2
                chunks++;
                curSum = 0; //the only thing different from SplitArrayLargestSum
                //this is because we need to assign num to the previous chunk because each chunk should have minimum amount of sweetness
            }
        }
        return chunks;
    }
}
