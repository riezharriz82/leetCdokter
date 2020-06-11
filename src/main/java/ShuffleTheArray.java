/**
 * https://leetcode.com/problems/shuffle-the-array/
 */
public class ShuffleTheArray {
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[nums.length];
        if (n == 1) {
            return new int[]{nums[0], nums[1]};
        }
        for (int i = 0; i < n; i++) {
            result[2 * i] = nums[i];
            result[2 * i + 1] = nums[i + n];
        }
        return result;
    }
}
