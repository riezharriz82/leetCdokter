import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/
 * <pre>
 * You are given a string num, representing a large integer, and an integer k.
 *
 * We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num. There can be many wonderful integers.
 * However, we only care about the smallest-valued ones.
 *
 * For example, when num = "5489355142":
 * The 1st smallest wonderful integer is "5489355214".
 * The 2nd smallest wonderful integer is "5489355241".
 * The 3rd smallest wonderful integer is "5489355412".
 * The 4th smallest wonderful integer is "5489355421".
 * Return the minimum number of adjacent digit swaps that needs to be applied to num to reach the kth smallest wonderful integer.
 *
 * The tests are generated in such a way that kth smallest wonderful integer exists.
 *
 * Input: num = "5489355142", k = 4
 * Output: 2
 * Explanation: The 4th smallest wonderful number is "5489355421". To get this number:
 * - Swap index 7 with index 8: "5489355142" -> "5489355412"
 * - Swap index 8 with index 9: "5489355412" -> "5489355421"
 *
 * Input: num = "11112", k = 4
 * Output: 4
 * Explanation: The 4th smallest wonderful number is "21111". To get this number:
 * - Swap index 3 with index 4: "11112" -> "11121"
 * - Swap index 2 with index 3: "11121" -> "11211"
 * - Swap index 1 with index 2: "11211" -> "12111"
 * - Swap index 0 with index 1: "12111" -> "21111"
 *
 * Input: num = "00123", k = 1
 * Output: 1
 * Explanation: The 1st smallest wonderful number is "00132". To get this number:
 * - Swap index 3 with index 4: "00123" -> "00132"
 *
 * Constraints:
 * 2 <= num.length <= 1000
 * 1 <= k <= 1000
 * num only consists of digits.
 * </pre>
 */
public class MinimumAdjacentSwapsToReachKthSmallestNumber {
    /**
     * Approach: First find the kth permutation and then find the no of adjacent swaps required to transform original input to target input
     * by logic similar to insertion sort
     * <p>
     * {@link NextPermutation} {@link LargestNumber} {@link MinimumAdjacentSwapsToArrangeBinaryGrid}
     */
    public int getMinSwaps(String num, int k) {
        int n = num.length();
        int[] kthPermutation = new int[n];
        int[] original = new int[n];
        for (int i = 0; i < n; i++) {
            kthPermutation[i] = num.charAt(i) - '0';
            original[i] = num.charAt(i) - '0';
        }
        for (int i = 0; i < k; i++) {
            nextPermutation(kthPermutation);
        }
        //now need to find swaps required to transform original array to target permutation array, by logic similar to insertion sort
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int target = kthPermutation[i];
            int temp = i; //temp should point to the first index that matches target value
            while (temp < n && original[temp] != target) {
                temp++;
            }
            //now perform adjacent swaps to move original[temp] to original[i]
            //no need to actually perform swaps, just moving it to next index is sufficient similar to insertion sort
            while (temp > i) {
                original[temp] = original[temp - 1];
                temp--;
                swaps++;
            }
            original[i] = target; //now place the target value at ith index
        }
        return swaps;
    }

    //copied from NextPermutation
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
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
