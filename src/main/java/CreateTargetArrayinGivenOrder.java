import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/create-target-array-in-the-given-order/
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
