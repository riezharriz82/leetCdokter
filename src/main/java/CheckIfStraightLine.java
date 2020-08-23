/**
 * https://leetcode.com/problems/check-if-it-is-a-straight-line/
 * <p>
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
 * Check if these points make a straight line in the XY plane.
 * <p>
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 */
public class CheckIfStraightLine {
    /**
     * <pre>
     * y - y1     y2 - y1
     * -----   =  -------
     * x - x1     x2 - x1
     * </pre>
     * <p>
     * RHS is the slope. Convert the equation into multiplication to avoid divide by 0.
     * <p>
     * diffX * (y - y1) == diffY * (x - x1)
     */
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length < 2) {
            return false;
        }
        boolean mismatch = false;
        int diffX = coordinates[1][0] - coordinates[0][0], diffY = coordinates[1][1] - coordinates[0][1];
        int x1 = coordinates[0][0], y1 = coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            int y = coordinates[i][1], x = coordinates[i][0];
            if (diffX * (y - y1) != diffY * (x - x1)) {
                mismatch = true;
                break;
            }
        }
        return !mismatch;
    }
}
