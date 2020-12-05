import java.util.Arrays;

/**
 * https://leetcode.com/problems/burst-balloons/
 * <p>
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * <pre>
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * </pre>
 */
public class BurstBalloons {
    /**
     * Approach: Backtracking solution is not memoizable, so lets think about divide and conquer
     * <p>
     * If we start with all balloons unpopped and if we pop, ith balloon, then the left subproblem will be (left, i-1) and right will be (i+1, right)
     * We want to calculate the subproblems independently but can we?
     * Since ith balloon is popped, result of i-1 will need i+1 balloon which is part of other subproblem, so they are not independent at all
     * <p>
     * If we proceed from the reverse i.e the state when all balloons are popped and we are trying to add the balloons
     * For ex. in case of sample input, we first try adding first balloon at 3,1,5,8. At 8, cost will be 1*8*1
     * Then after fixing 8, we recur left and right. So when we recur left, we know that 1 and 8 are only balloons left.
     * So whatever we choose inside, we know the left and right bounds, this leads to a memoizable solution
     * <p>
     * Time complexity: n^3 = n * n^2. After fixing an element, every order of picking elements in left and right subpart is tested
     */
    public int maxCoinsDivideAndConquer(int[] nums) {
        int n = nums.length;
        int[] input = new int[n + 2];
        input[0] = 1;
        input[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            input[i + 1] = nums[i];
        }
        int[][] memoized = new int[n + 2][n + 2];
        for (int[] ints : memoized) {
            Arrays.fill(ints, -1);
        }
        return recur(input, 0, n + 1, memoized);
    }

    private int recur(int[] input, int left, int right, int[][] memoized) {
        if (memoized[left][right] != -1) {
            return memoized[left][right];
        }
        int maxTotalCost = 0;
        for (int i = left + 1; i < right; i++) {
            int curCost = input[i] * input[left] * input[right];
            int remain = recur(input, left, i, memoized) + recur(input, i, right, memoized);
            maxTotalCost = Math.max(maxTotalCost, curCost + remain);
        }
        return memoized[left][right] = maxTotalCost;
    }

    /**
     * Approach: Try all the combinations and get the max result.
     * Time complexity: n!, and as we learned earlier, n! only works when n <= 10
     * Here n <= 500, so obviously it times out, but it gives us a good base to optimize
     */
    public int maxCoinsBacktracking(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        return recur(nums, visited);
    }

    private int recur(int[] nums, boolean[] visited) {
        int maxCostSoFar = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) { //if this balloon is not popped, try popping it
                visited[i] = true;
                int temp = i;
                int cost = nums[i];
                while (temp >= 0 && visited[temp]) { //need to find the first un popped balloon on the left
                    temp--;
                }
                if (temp != -1) {
                    cost *= nums[temp];
                }
                temp = i;
                while (temp < nums.length && visited[temp]) { //find the first un popped balloon on the right
                    temp++;
                }
                if (temp != nums.length) {
                    cost *= nums[temp];
                }
                int remain = recur(nums, visited);
                maxCostSoFar = Math.max(remain + cost, maxCostSoFar); //recur for remaining balloons
                visited[i] = false;
            }
        }
        return maxCostSoFar;
    }
}
