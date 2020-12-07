/**
 * https://leetcode.com/problems/lonely-pixel-i/
 * <p>
 * Given an m x n picture consisting of black 'B' and white 'W' pixels, return the number of black lonely pixels.
 * <p>
 * A black lonely pixel is a character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
 * <p>
 * Input: picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * <p>
 * Input: picture = [["B","B","B"],["B","B","B"],["B","B","B"]]
 * Output: 0
 */
public class LonelyPixel1 {
    /**
     * Approach: Keep track of the number of black pixels in each row and column.
     */
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length;
        int n = picture[0].length;
        int[] rows = new int[m];
        int[] columns = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') { //increment the count of black pixels in this row and column
                    rows[i]++;
                    columns[j]++;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B' && rows[i] == 1 && columns[j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }
}
