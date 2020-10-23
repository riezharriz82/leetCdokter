/**
 * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/ Premium
 * <p>
 * Given an integer array sorted in ascending order, write a function to search target in nums.
 * If target exists, then return its index, otherwise return -1. However, the array size is unknown to you.
 * You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).
 * <p>
 * You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.
 * <p>
 * Input: array = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * <p>
 * Input: array = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
public class SearchInSortedArrayOfUnknownSize {
    /**
     * Approach: The trick is to find the range to do binary search on, since the array is of unknown size, we don't know what can be the bounds
     * So we increment the candidateIndex by 2 every time if the element at candidateIndex is < target
     * We stop either if the candidateIndex is out of bounds or element at candidateIndex is > target
     * <p>
     * Once range is found, we do binary search on it. Template is similar to erichto's binary search template.
     */
    public int search(ArrayReader reader, int target) {
        int power = 0;
        int candidateIndex = (int) Math.pow(2, power);
        //indexes need to be off by 1 because we need to consider 0 as well
        //0, 1, 3, 7, 15, 31, 63, 127....
        while (reader.get(candidateIndex - 1) != Integer.MAX_VALUE && reader.get(candidateIndex - 1) < target) {
            power++;
            candidateIndex = (int) Math.pow(2, power);
        }
        int low = (int) Math.pow(2, power - 1) - 1; //just previous power
        int high = (int) Math.pow(2, power) - 1; //current power
        while (low <= high) {
            int mid = (low + high) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) == Integer.MAX_VALUE) { //mid is too high, reduce the range
                high = mid - 1;
            } else if (reader.get(mid) < target) { //mid is too low, increase the range
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private interface ArrayReader {
        int get(int index);
    }
}
