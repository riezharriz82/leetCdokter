/**
 * https://leetcode.com/problems/regions-cut-by-slashes/
 * <p>
 * In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.
 * <p>
 * (Note that backslash characters are escaped, so a \ is represented as "\\".)
 * <p>
 * Return the number of regions.
 * <pre>
 * Input:
 * [
 *   " /",
 *   "/ "
 * ]
 * Output: 2
 *
 * Input:
 * [
 *   " /",
 *   "  "
 * ]
 * Output: 1
 *
 * Input:
 * [
 *   "\\/",
 *   "/\\"
 * ]
 * Output: 4
 * Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
 *
 * Input:
 * [
 *   "/\\",
 *   "\\/"
 * ]
 * Output: 5
 *
 * Input:
 * [
 *   "//",
 *   "/ "
 * ]
 * Output: 3
 * </pre>
 */
public class RegionsCutBySlashes {
    int[] x_offsets = new int[]{0, 1, 0, -1};
    int[] y_offsets = new int[]{1, 0, -1, 0};

    /**
     * Approach: Consider the grid as an image and if you can increase the resolution of images, you can better identify the
     * no of components, so increase the resolution by upscaling the image to 3*3. Upscaling to 2*2 does not work for cases
     * like "//" and will need DFS in 8 directions to find the connected components
     * <p>
     * / will be represented in the upscaled grid as below, 1 acts as a border
     * [001]
     * [010]
     * [100]
     * {@link SentenceSimilarity2} {@link NumberOfIslands} related component finding problem
     */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[][] upscaled = new int[3 * n][3 * n];
        //create an upscale version of the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '/') { //i,j are upscaled 3 times, so the root becomes 3*i,3*j
                    upscaled[3 * i][3 * j + 2] = 1;
                    upscaled[3 * i + 1][3 * j + 1] = 1;
                    upscaled[3 * i + 2][3 * j] = 1;
                } else if (grid[i].charAt(j) == '\\') {
                    upscaled[3 * i][3 * j] = 1;
                    upscaled[3 * i + 1][3 * j + 1] = 1;
                    upscaled[3 * i + 2][3 * j + 2] = 1;
                }
            }
        }
        int components = 0; //keep track of unique components of 0
        for (int i = 0; i < 3 * n; i++) {
            for (int j = 0; j < 3 * n; j++) {
                if (upscaled[i][j] == 0) {
                    components++;
                    DFS(upscaled, i, j);
                }
            }
        }
        return components;
    }

    private void DFS(int[][] upscaled, int i, int j) {
        upscaled[i][j] = -1; //acts as a visited array
        int n = upscaled.length;
        for (int k = 0; k < 4; k++) {
            int new_i = i + x_offsets[k];
            int new_j = j + y_offsets[k];
            if (new_i >= 0 && new_i < n && new_j >= 0 && new_j < n && upscaled[new_i][new_j] == 0) {
                DFS(upscaled, new_i, new_j);
            }
        }
    }
}
