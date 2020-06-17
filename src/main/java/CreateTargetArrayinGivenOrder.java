import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/create-target-array-in-the-given-order/
 * Given two arrays of integers nums and index. Your task is to create target array under the following rules:
 * <p>
 * Initially target array is empty.
 * From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
 * Repeat the previous step until there are no elements to read in nums and index.
 * Return the target array.
 * <p>
 * It is guaranteed that the insertion operations will be valid.
 */
public class CreateTargetArrayinGivenOrder {

    /**
     * https://leetcode.com/problems/create-target-array-in-the-given-order/discuss/549583/O(nlogn)-based-on-%22smaller-elements-after-self%22.
     * Unable to figure out why +count
     */

    //Second Solution
    public int[] solveUsingArrayList(int[] nums, int[] index) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            res.add(index[i], nums[i]);
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    //Third solution
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //shift the result array right by 1
            for (int j = nums.length - 2; j >= index[i]; j--) {
                res[j + 1] = res[j];
            }
            res[index[i]] = nums[i];
        }
        return res;
    }
}
