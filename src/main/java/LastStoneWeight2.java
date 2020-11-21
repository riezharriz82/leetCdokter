import java.util.Arrays;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/
 * <p>
 * We have a collection of rocks, each rock has a positive integer weight.
 * <p>
 * Each turn, we choose any two rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * <p>
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 * <p>
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We can combine 2 and 4 to get 2 so the array converts to [2,7,1,8,1] then,
 * we can combine 7 and 8 to get 1 so the array converts to [2,1,1,1] then,
 * we can combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we can combine 1 and 1 to get 0 so the array converts to [1] then that's the optimal value.
 * <p>
 * Note:
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 */
public class LastStoneWeight2 {

    /**
     * Approach: This problem reduces to finding the minimum difference between two subsets in array
     * The bounds of input [1, 30] gives an hint towards generating all combinations to find the result
     * <p>
     * In my initial implementation, I solved using greedy ie picked the largest two stones, smashed them and repeated the process
     * Similar to {@link LastStoneWeight} but it gave WA for [31,26,33,21,40] Output: 9 Expected: 5
     * <p>
     * Then I used recursion to generate all possible combinations of stones for smashing and tried to get the min score after trying
     * all the combinations but it resulted in TLE because that solution was not memoizable because I was picking two pairs of indices
     * and then introducing new stones in the list, I could not memoize it
     * <p>
     * Then I looked at the discuss and saw the hint of lee215@ to find the min diff between two subsets in array
     * I was able to code the solution on my own afterwards
     * <p>
     * What is the intuition behind this? Consider three stones a,b,c
     * (a-b)-c = a-(b+c)
     * (a-c)-b = a-(b+c)
     * (b-c)-a = b-(c+a)
     * So you can see that all three combinations can be reduced to finding the difference between two subsets in array
     * <p>
     * {@link TargetSum} {@link CherryPickup} {@link FruitIntoBaskets} for related problem
     */
    public int lastStoneWeightII(int[] stones) {
        int totalSum = 0;
        for (int stone : stones) {
            totalSum += stone;
        }
        int[][] memoized = new int[stones.length][totalSum + 1];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        return recur(stones, 0, totalSum, 0, memoized);
    }

    private int recur(int[] stones, int idx, int totalSum, int curSum, int[][] memoized) {
        if (idx == stones.length) {
            return Math.abs(curSum - (totalSum - curSum)); //return the diff between first set and another set
            //second set sum is totalSum - firstSetSum
        } else if (memoized[idx][curSum] != -1) {
            return memoized[idx][curSum];
        }
        int pickIdx = recur(stones, idx + 1, totalSum, curSum + stones[idx], memoized);
        int skipIdx = recur(stones, idx + 1, totalSum, curSum, memoized);
        return memoized[idx][curSum] = Math.min(pickIdx, skipIdx);
    }
}
