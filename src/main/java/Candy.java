import java.util.Arrays;

/**
 * https://leetcode.com/problems/candy/
 * <p>
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 * <p>
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * <p>
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 * <p>
 * Constraints:
 * n == ratings.length
 * 1 <= n <= 2 * 10^4
 * 0 <= ratings[i] <= 2 * 10^4
 */
public class Candy {
    /**
     * Approach: Greedy, Leveraged prior learning of solving mountain related problem in two passes
     * <pre>
     *    /\
     *   /  \
     *  /    \
     * /      \
     *         \
     *          \
     * If the ratings form a graph like this, the root element must get 7 candies
     * So make a pass from left to right and assign candies greedily based on whether current rating is greater than previous neighbour
     * Similarly make another pass from right to left
     * In the end, return the maximum candy a person should receive based on either left or right pass.
     *
     * Happy to solve this question on my own in ~40 minutes.
     * </pre>
     * <p>
     * {@link MinimumNumberOfRemovalsToMakeMountainArray} {@link LongestMountainInArray} {@link FindPermutation}
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int candies = 0;
        for (int i = 0; i < n; i++) {
            candies += Math.max(left[i], right[i]);
        }
        return candies;
    }
}
