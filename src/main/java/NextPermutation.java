import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-permutation/
 * <p>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place and use only constant extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * <pre>
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * </pre>
 */
public class NextPermutation {
    /**
     * Refer to {@link NextGreaterElement3} for explanation
     * {@link MinimumAdjacentSwapsToReachKthSmallestNumber} for related problem
     */
    public void nextPermutation(int[] nums) {
        boolean mismatchFound = false;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                mismatchFound = true;
                //find the highest index whose value is greater than nums[i-1]
                // in other words, find the value which is just larger than nums[i-1]
                int justGreaterIndex = highestIndexGreaterThanValue(nums, nums[i - 1], i);
                //swap, no default library method present to swap elements in array
                int temp = nums[i - 1];
                nums[i - 1] = nums[justGreaterIndex];
                nums[justGreaterIndex] = temp;
                Arrays.sort(nums, i, nums.length);
                break;
            }
        }
        if (!mismatchFound) {
            //need to wrap around if the current number is already the largest possible
            Arrays.sort(nums);
        }
    }

    private int highestIndexGreaterThanValue(int[] nums, int value, int index) {
        int resultIndex = index;
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > value) {
                resultIndex = i;
            }
        }
        return resultIndex;
    }
}
