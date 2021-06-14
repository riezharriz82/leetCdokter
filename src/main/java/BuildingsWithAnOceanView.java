import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * https://leetcode.com/problems/buildings-with-an-ocean-view/ Premium
 *
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
 *
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions.
 * Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 *
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 *
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 *
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 *
 * Input: heights = [2,2,2,2]
 * Output: [3]
 * Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.
 *
 * Constraints:
 * 1 <= heights.length <= 10^5
 * 1 <= heights[i] <= 10^9
 * </pre>
 */
public class BuildingsWithAnOceanView {
    /**
     * Approach: Keep track of the maximum height by iterating from the right. If the max height found so far < cur height, current building can view the ocean
     */
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (heights[i] > max) {
                res.add(i);
            }
            max = Math.max(max, heights[i]);
        }
        //boiler plate code to adjust to output requirement
        Collections.reverse(res);
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}
