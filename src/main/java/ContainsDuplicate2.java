import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/
 * <p>
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 * <p>
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 */
public class ContainsDuplicate2 {
    /**
     * Approach: Since the problem statement restricts the max difference between two indices, sliding window fits perfectly.
     * Keep expanding the window until size == k, after that pop one element from begin and add one element at the end.
     * Keep a hashSet to quickly find any repeating element in the window.
     * <p>
     * HashSet works here because we care only about the first duplicate element, hence hashMap isn't required.
     */
    public boolean containsNearbyDuplicateUsingSlidingWindow(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) { //need to remove the element fallen outside of window
                set.remove(nums[i - k - 1]);
            }
            if (set.contains(nums[i])) { //check if duplicate element present in the window
                return true;
            }
            set.add(nums[i]); // add the element in the window
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(nums[i]) && Math.abs(hm.get(nums[i]) - i) <= k) {
                return true;
            }
            hm.put(nums[i], i);
        }
        return false;
    }
}
