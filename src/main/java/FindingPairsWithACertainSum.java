import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * https://leetcode.com/problems/finding-pairs-with-a-certain-sum/
 *
 * You are given two integer arrays nums1 and nums2. You are tasked to implement a data structure that supports queries of two types:
 *
 * Add a positive integer to an element of a given index in the array nums2.
 * Count the number of pairs (i, j) such that nums1[i] + nums2[j] equals a given value (0 <= i < nums1.length and 0 <= j < nums2.length).
 * Implement the FindSumPairs class:
 *
 * FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
 * void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
 * int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 *
 * Input
 * ["FindSumPairs", "count", "add", "count", "count", "add", "add", "count"]
 * [[[1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]], [7], [3, 2], [8], [4], [0, 1], [1, 1], [7]]
 * Output
 * [null, 8, null, 2, 1, null, null, 11]
 *
 * Explanation
 * FindSumPairs findSumPairs = new FindSumPairs([1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]);
 * findSumPairs.count(7);  // return 8; pairs (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) make 2 + 5 and pairs (5,1), (5,5) make 3 + 4
 * findSumPairs.add(3, 2); // now nums2 = [1,4,5,4,5,4]
 * findSumPairs.count(8);  // return 2; pairs (5,2), (5,4) make 3 + 5
 * findSumPairs.count(4);  // return 1; pair (5,0) makes 3 + 1
 * findSumPairs.add(0, 1); // now nums2 = [2,4,5,4,5,4]
 * findSumPairs.add(1, 1); // now nums2 = [2,5,5,4,5,4]
 * findSumPairs.count(7);  // return 11; pairs (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) make 2 + 5 and pairs (5,3), (5,5) make 3 + 4
 *
 * Constraints:
 * 1 <= nums1.length <= 1000
 * 1 <= nums2.length <= 10^5
 * 1 <= nums1[i] <= 10^9
 * 1 <= nums2[i] <= 10^5
 * 0 <= index < nums2.length
 * 1 <= val <= 10^5
 * 1 <= tot <= 10^9
 * At most 1000 calls are made to add and count each.
 * </pre>
 */
public class FindingPairsWithACertainSum {
    Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>(); //value -> frequency
    int[] nums1, nums2;

    /**
     * Approach: Variance of two sum problem, maintain a hashmap of value to frequency for both the arrays.
     * When asked to count pairs with total sum x, iterate over the smaller size hashmap (nums1 as it's length is 1000) and find the no of elements with value = (x - curKey)
     * in other map.
     * <p>
     * {@link TwoSum3}
     */
    public FindingPairsWithACertainSum(int[] nums1, int[] nums2) {
        for (int val : nums1) {
            map1.put(val, map1.getOrDefault(val, 0) + 1);
        }
        for (int val : nums2) {
            map2.put(val, map2.getOrDefault(val, 0) + 1);
        }
        this.nums1 = nums1;
        this.nums2 = nums2;
    }

    public void add(int index, int val) {
        map2.put(nums2[index], map2.get(nums2[index]) - 1);//find current value at the index and decrement it's frequency
        map2.remove(nums2[index], 0); //remove key from map if the new frequency is 0
        nums2[index] += val;
        map2.put(nums2[index], map2.getOrDefault(nums2[index], 0) + 1);//increment frequency of the new value at the index
    }

    public int count(int tot) {
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) { //loop over the smaller map, could have chosen it dynamically
            int key = entry.getKey(), freq = entry.getValue();
            if (map2.containsKey(tot - key)) {
                res += (freq * map2.get(tot - key));
            }
        }
        return res;
    }
}
