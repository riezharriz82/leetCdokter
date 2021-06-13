import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 * <p>
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
 * <p>
 * Input:
 * [1,2,3]
 * <p>
 * Output:
 * 3
 * <p>
 * Explanation:
 * Only three moves are needed (remember each move increments two elements):
 * <p>
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
public class MinimumMovesToEqualArrayElements {
    /**
     * Approach: Greedy, incrementing n-1 elements by 1 and decrementing 1 element by 1 keeps the relative distance between elements same
     * [1,2,3,4] -> incrementing n-1 elements by 3 -> [4,5,6,4] -> relative distance between first and last element -> [0,1,2]
     * [1,2,3,4] -> decrementing 1 element by 3 -> [1,2,3,1] -> [0,1,2]
     * <p>
     * Can also be solved mathematically
     * <p>
     * {@link SmallestRange2} {@link MinimumMovesToEqualArrayElements2} {@link BulbSwitcher4} {@link BulbSwitcher} {@link ConstructTargetArrayWithMultipleSums}
     */
    public int minMoves(int[] nums) {
        int steps = 0, min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        for (int num : nums) {
            steps += (num - min);
        }
        return steps;
    }

    /**
     * Approach: Brute Force, Gives TLE
     */
    public int minMovesBruteForce(int[] nums) {
        int n = nums.length, steps = 0;
        while (true) {
            Arrays.sort(nums);
            if (nums[0] == nums[n - 1]) { //if smallest and largest numbers are same, all elements are equals
                return steps;
            }
            steps++;
            for (int i = 0; i < n - 1; i++) { //increment the smallest n-1 number by +1
                nums[i]++;
            }
        }
    }
}
