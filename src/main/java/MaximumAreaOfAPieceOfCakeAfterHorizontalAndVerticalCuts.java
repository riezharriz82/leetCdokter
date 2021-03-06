import java.util.Arrays;

/**
 * <pre>
 * https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
 *
 * Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i] is the distance
 * from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 *
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts.
 * Since the answer can be a huge number, return this modulo 10^9 + 7.
 *
 * Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 * Output: 4
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
 *
 * Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 * Output: 6
 * Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
 *
 * Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 * Output: 9
 *
 * Constraints:
 * 2 <= h, w <= 10^9
 * 1 <= horizontalCuts.length < min(h, 10^5)
 * 1 <= verticalCuts.length < min(w, 10^5)
 * 1 <= horizontalCuts[i] < h
 * 1 <= verticalCuts[i] < w
 * It is guaranteed that all elements in horizontalCuts are distinct.
 * It is guaranteed that all elements in verticalCuts are distinct.
 * </pre>
 */
public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {
    /**
     * Approach: Greedy, Try to solve 2D problems by first solving for 1D ie. consider the problem when there are cuts only in one dimension (vertical)
     * Largest line would be the longest cut between two points.
     * Now repeat this process for the other dimension ie. height. Result would be the multiplication of that.
     * <p>
     * Why this works?
     * Because if we consider the largest cut in one dimension, its length would be >= length of other cuts. Now if we further cut this area by cutting in
     * other dimension, this would lead to a rectangle with area >= area of any other rectangle generated by further cutting any other smaller cuts.
     * <p>
     * Was not able to solve this question on my own :(
     * {@link GenerateRandomPointInACircle} {@link RectangleOverlap}
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        //sorting is required because the input does not guarantee cuts in sorted order
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int maxHeight = horizontalCuts[0], maxWidth = verticalCuts[0];
        for (int i = 1; i < horizontalCuts.length; i++) {
            int curHeight = horizontalCuts[i] - horizontalCuts[i - 1];
            maxHeight = Math.max(maxHeight, curHeight);
        }
        maxHeight = Math.max(maxHeight, h - horizontalCuts[horizontalCuts.length - 1]); //consider the distance between last cut and border of cake
        for (int i = 1; i < verticalCuts.length; i++) {
            int curWidth = verticalCuts[i] - verticalCuts[i - 1];
            maxWidth = Math.max(maxWidth, curWidth);
        }
        maxWidth = Math.max(maxWidth, w - verticalCuts[verticalCuts.length - 1]);
        long maxArea = (long) maxHeight * maxWidth; //because of overflow
        return (int) (maxArea % 1_000_000_007);
    }
}
