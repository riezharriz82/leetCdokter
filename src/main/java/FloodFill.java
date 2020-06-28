import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/flood-fill/
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
 * <p>
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
 * <p>
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel
 * of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels
 * (also with the same color as the starting pixel), and so on.
 * Replace the color of all of the aforementioned pixels with the newColor.
 * <p>
 * At the end, return the modified image.
 * <p>
 * Example 1:
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 */
public class FloodFill {
    int[] x_index = new int[]{0, 1, 0, -1};
    int[] y_index = new int[]{1, 0, -1, 0};
    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
//        return floodFillDFS(image, sr, sc, image[sr][sc], newColor);
        return floodFillBFS(image, sr, sc, image[sr][sc], newColor);
    }

    private int[][] floodFillBFS(int[][] image, int sr, int sc, int startingColor, int newColor) {
        if (image[sr][sc] != newColor) {
            queue.add(new Pair<>(sr, sc));
            while (!queue.isEmpty()) {
                Pair<Integer, Integer> head = queue.poll();
                int x = head.getKey();
                int y = head.getValue();

                image[x][y] = newColor;
                for (int i = 0; i < x_index.length; i++) {
                    int new_x = x + x_index[i];
                    int new_y = y + y_index[i];
                    if (new_x >= 0 && new_x < image.length && new_y >= 0 && new_y < image[0].length
                            && image[new_x][new_y] == startingColor && image[new_x][new_y] != newColor) {
                        queue.add(new Pair<>(new_x, new_y));
                    }
                }
            }
        }
        return image;
    }

    private int[][] floodFillDFS(int[][] image, int sr, int sc, int startingColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length) {
            return image;
        } else if (image[sr][sc] == startingColor && image[sr][sc] != newColor) {
            image[sr][sc] = newColor;
            for (int i = 0; i < x_index.length; i++) {
                floodFillDFS(image, sr + x_index[i], sc + y_index[i], startingColor, newColor);
            }
        }
        return image;
    }
}
