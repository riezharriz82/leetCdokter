/**
 * https://leetcode.com/problems/find-the-winner-of-an-array-game/
 * <p>
 * Given an integer array arr of distinct integers and an integer k.
 * <p>
 * A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]). In each round of the game,
 * we compare arr[0] with arr[1], the larger integer wins and remains at position 0 and the smaller integer moves to the end of the array.
 * The game ends when an integer wins k consecutive rounds.
 * <p>
 * Return the integer which will win the game.
 * <p>
 * It is guaranteed that there will be a winner of the game.
 */
public class FindWinnerOfArrayGame {
    /**
     * Approach: Similar to {@link MajorityElement}
     * Keep track of whether the next number is greater than the current winner. If yes, then reset the current win count and update the winner index
     * Otherwise increment the win count
     * If win count equals target win count return the winner
     */
    public int getWinner(int[] arr, int k) {
        int n = arr.length;
        int winnerIndex = 0, curWinCount = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[winnerIndex]) {
                curWinCount = 1;
                winnerIndex = i;
            } else {
                curWinCount++;
            }
            if (curWinCount == k) {
                return arr[winnerIndex];
            }
        }
        //winnerIndex is now greater than all the remaining array elements, so no matter what k is now, result will always be winner index
        //there are only finite number of states, post that state will repeat
        return arr[winnerIndex];
    }
}
