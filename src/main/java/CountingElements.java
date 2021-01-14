import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/counting-elements/ Premium
 * <p>
 * Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr.
 * <p>
 * If there are duplicates in arr, count them separately.
 * <p>
 * Input: arr = [1,2,3]
 * Output: 2
 * Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
 * <p>
 * Input: arr = [1,1,3,3,5,5,7,7]
 * Output: 0
 * Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
 * <p>
 * Input: arr = [1,3,2,3,5,0]
 * Output: 3
 * Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
 * <p>
 * Input: arr = [1,1,2,2]
 * Output: 2
 * Explanation: Two 1s are counted cause 2 is in arr.
 * <p>
 * Input: arr = [1,1,2]
 * Output: 2
 * Explanation: Both 1s are counted because 2 is in the array.
 * <p>
 * Constraints:
 * 1 <= arr.length <= 1000
 * 0 <= arr[i] <= 1000
 */
public class CountingElements {
    /**
     * Approach: Keep track of frequency of elements using hashmap and then iterate the map to see whether val + 1 exists or not
     * <p>
     * Remember that whenever we are asked to find something, use hashmap or binary search
     * <p>
     * {@link LongestConsecutiveSequence}
     */
    public int countElements(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int curValue = entry.getKey();
            int curCount = entry.getValue();
            if (map.containsKey(curValue + 1)) {
                //if curCount is 2 and (curValue+1) occurs 1 times, we can still pair all the curValue with curValue+1
                //e.g. 1,1,2 -> {1,2} & {1,2}
                //e.g. 1,2,2 -> {1,2}
                res += curCount;
            }
        }
        return res;
    }
}
