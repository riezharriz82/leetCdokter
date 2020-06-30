/**
 * https://leetcode.com/problems/container-with-most-water/
 * <p>
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * <p>
 * Note: You may not slant the container and n is at least 2.
 */
public class ContainerWithMostWater {
    /**
     * Idea / Proof:
     * <p>
     * The widest container (using first and last line) is a good candidate, because of its width.
     * Its water level is the height of the smaller one of first and last line.
     * <p>
     * All other containers are less wide and thus would need a higher water level in order to hold more water.
     * <p>
     * The smaller one of first and last line doesn't support a higher water level and can thus be safely removed from further consideration.
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int low = 0, high = height.length - 1;
        while (low < high) {
            int curArea = Math.min(height[low], height[high]) * (high - low); //shortest among them multiplied by the width
            maxArea = Math.max(curArea, maxArea);
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxArea;
    }
}
