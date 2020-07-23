/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once.
 * <p>
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 */
public class FindTwoSingleNumber {
    /**
     * For input {1,1,2,2,3,5} xor of all the input values will be 3^5 = 6 = {110}
     * i.e input can be partitioned basis on 2nd or 3rd bit
     * <p>
     * 1 - 001
     * 1 - 001
     * 5 - 101
     * <p>
     * 2 - 010
     * 2 - 010
     * 3 - 011
     * <p>
     * now if we xor this two separate groups we will get 3 or 5
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int rightMostSetBit = xor & -xor;
        int groupWithBitSet = 0, groupWithBitUnset = 0;
        for (int num : nums) {
            if ((num & rightMostSetBit) > 0) {
                groupWithBitSet ^= num;
            } else {
                groupWithBitUnset ^= num;
            }
        }
        return new int[]{groupWithBitSet, groupWithBitUnset};
    }
}
