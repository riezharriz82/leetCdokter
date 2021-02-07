import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-score-from-removing-stones/
 * <p>
 * You are playing a solitaire game with three piles of stones of sizes a, b, and c respectively. Each turn you choose two different non-empty piles,
 * take one stone from each, and add 1 point to your score. The game stops when there are fewer than two non-empty piles (meaning there are no more available moves).
 * <p>
 * Given three integers a, b, and c, return the maximum score you can get.
 * <p>
 * Input: a = 2, b = 4, c = 6
 * Output: 6
 * Explanation: The starting state is (2, 4, 6). One optimal set of moves is:
 * - Take from 1st and 3rd piles, state is now (1, 4, 5)
 * - Take from 1st and 3rd piles, state is now (0, 4, 4)
 * - Take from 2nd and 3rd piles, state is now (0, 3, 3)
 * - Take from 2nd and 3rd piles, state is now (0, 2, 2)
 * - Take from 2nd and 3rd piles, state is now (0, 1, 1)
 * - Take from 2nd and 3rd piles, state is now (0, 0, 0)
 * There are fewer than two non-empty piles, so the game ends. Total: 6 points.
 * <p>
 * Input: a = 4, b = 4, c = 6
 * Output: 7
 * Explanation: The starting state is (4, 4, 6). One optimal set of moves is:
 * - Take from 1st and 2nd piles, state is now (3, 3, 6)
 * - Take from 1st and 3rd piles, state is now (2, 3, 5)
 * - Take from 1st and 3rd piles, state is now (1, 3, 4)
 * - Take from 1st and 3rd piles, state is now (0, 3, 3)
 * - Take from 2nd and 3rd piles, state is now (0, 2, 2)
 * - Take from 2nd and 3rd piles, state is now (0, 1, 1)
 * - Take from 2nd and 3rd piles, state is now (0, 0, 0)
 * There are fewer than two non-empty piles, so the game ends. Total: 7 points.
 * <p>
 * Input: a = 1, b = 8, c = 8
 * Output: 8
 * Explanation: One optimal set of moves is to take from the 2nd and 3rd piles for 8 turns until they are empty.
 * After that, there are fewer than two non-empty piles, so the game ends.
 * <p>
 * Constraints:
 * 1 <= a, b, c <= 105
 */
public class MaximumScoreFromRemovingStones {
    /**
     * Approach: Greedy, maximum score will be obtained if the smallest pile is reached at the last step ie. we keep working with
     * largest two piles.
     * <p>
     * Initially I solved this problem using recursion by solving {3,3,3} by hand and then emulating the conditions in code.
     * Also it can be solved in O(1) time using maths
     * <p>
     * {@link LastStoneWeight2} {@link LastStoneWeight}
     */
    public int maximumScore(int a, int b, int c) {
        int[] arr = new int[]{a, b, c};
        int score = 0;
        while (true) {
            Arrays.sort(arr);
            int val1 = arr[1];
            if (val1 == 0) { //if middle element is 0, this means at least two elements are 0 and we can't find two non-zero piles
                break;
            }
            score++;
            arr[1] = arr[1] - 1;
            arr[2] = arr[2] - 1;
        }
        return score;
    }
}
