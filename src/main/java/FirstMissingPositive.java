/**
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * Given an unsorted integer array, find the smallest missing positive integer.
 * <p>
 * Input: [1,2,0]
 * Output: 3
 * <p>
 * Input: [3,4,-1,1]
 * Output: 2
 */
public class FirstMissingPositive {
    /**
     * Approach: Tricky, Swap the numbers num to their correct index num-1
     * It's wise to swap them to num-1 instead of num to avoid lots of edge cases, in my initial submission I didn't and have to write spaghetti to fix them
     */
    public int firstMissingPositiveUsingSwap(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] < n && nums[i] != nums[nums[i] - 1]) { //nums[i] != nums[nums[i]-1] to avoid infinte loop while swapping same numbers :D
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp; //avoid nums[nums[i]-1] because nums[i] will change in the previous step
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * Approach: The trickiest thing is to eliminate already negative and 0 numbers by increasing them to a big value.
     * Now reiterate and flag the intended place of the number ie. if the number is 10, set the flag at index 9.
     * Remember to set the flag only once. Avoid double negation.
     * <p>
     * Reiterate and the first index with +ve value i.e no flag set will be the answer.
     */
    public int firstMissingPositiveByNegation(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 1;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) { //most important bit, exclude the negative numbers earlier by setting it to a big positive value, which we will ignore
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]);
            if (index > 0 && index <= n && nums[index - 1] > 0) { //consider only values in range and avoid double negation
                nums[index - 1] *= -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) { //find the first index with the positive value
                return i + 1;
            }
        }
        return n + 1;
    }
}
