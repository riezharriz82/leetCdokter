import java.util.Random;

/**
 * https://leetcode.com/problems/shuffle-an-array/
 * <p>
 * Shuffle a set of numbers without duplicates.
 * <p>
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * <p>
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * <p>
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 */
public class ShuffleArray {
    int[] nums;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int[] shuffled = nums.clone();
        //fisher yates algorithm
        //pick a random index between 0 and last index, swap the value at chosen index with the value at last index
        //last index is now done, repeat this process between 0 and second last index
        for (int i = 0; i < shuffled.length - 1; i++) {
            int randomIndex = new Random().nextInt(shuffled.length - i);
            int temp = shuffled[randomIndex];
            shuffled[randomIndex] = shuffled[shuffled.length - i - 1];
            shuffled[shuffled.length - i - 1] = temp;
        }
        return shuffled;
    }
}
