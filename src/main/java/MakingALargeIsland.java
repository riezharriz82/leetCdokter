import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/making-a-large-island/
 * <p>
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 * <p>
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 * <p>
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * <p>
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * <p>
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 */
public class MakingALargeIsland {
    int[] x_offset = new int[]{0, 1, 0, -1};
    int[] y_offset = new int[]{1, 0, -1, 0};

    /**
     * Approach: Leverage Union find algorithm to keep track of sizes of island.
     * Take care to avoid double counting the adjacent islands belonging to the same parent.
     * <p>
     * Another simpler approach would be to directly modify the grid by uniquely identifying an island and keep mapping of island id to area of island
     * [1,1,1] => [2,2,2]
     * [1,0,0] => [2,0,0]
     * [0,1,1] => [0,3,3]
     * <p>
     * Island identifier starts from 2
     * Whenever we encounter 1, perform DFS on it's neighbour and modify the grid to it's index ie. island id
     * Keep track of sizes [2 id has 4 size, 3 id has 2 size]
     * <p>
     * When we are at 0, check it's neighbour and get their sizes. Avoid double counting
     * <p>
     * {@link FindGroupWithSizeM} {@link GraphValidTree} {@link NumberOfConnectedComponentsInUndirectedGraph}
     */
    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] parent = new int[m * n];
        int[] sizes = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    make_set(parent, i * n + j, sizes); //make each 1 node it's own parent
                }
            }
        }
        //perform union on adjacent 1's
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int new_i = i + x_offset[k];
                        int new_j = j + y_offset[k];
                        if (new_i >= 0 && new_i < m && new_j >= 0 && new_j < n && grid[new_i][new_j] == 1) {
                            merge_set(parent, sizes, i * n + j, new_i * n + new_j);
                        }
                    }
                }
            }
        }
        int result = largestIsland(sizes); //init with the largest island found so far, to take care of base case
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //for each 0, check the sizes of it's adjacent 1's, keeping track of sum of adjacent islands
                if (grid[i][j] == 0) {
                    Set<Pair<Integer, Integer>> set = new HashSet<>();
                    //critical to avoid double counting ie. if two adjacent nodes have the same parent, count them only once
                    for (int k = 0; k < 4; k++) {
                        int new_i = i + x_offset[k];
                        int new_j = j + y_offset[k];
                        if (new_i >= 0 && new_i < m && new_j >= 0 && new_j < n && grid[new_i][new_j] == 1) {
                            int parentIdx = find_set(parent, new_i * n + new_j);
                            set.add(new Pair<>(parentIdx, sizes[parentIdx]));
                        }
                    }
                    int sizeOfAdjacentIslands = 0;
                    for (Pair<Integer, Integer> pair : set) { //set prevents double counting
                        sizeOfAdjacentIslands += pair.getValue();
                    }
                    result = Math.max(result, 1 + sizeOfAdjacentIslands);
                }
            }
        }
        return result;
    }

    private int largestIsland(int[] sizes) {
        int max = 0;
        for (int size : sizes) {
            max = Math.max(size, max);
        }
        return max;
    }

    //union by size ie. update the parent of the smaller size set
    private void merge_set(int[] parent, int[] sizes, int idx1, int idx2) {
        int parent1 = find_set(parent, idx1);
        int parent2 = find_set(parent, idx2);
        if (parent1 != parent2) {
            if (sizes[parent1] < sizes[parent2]) { //if set1 is smaller than set2, update parent of set1
                parent[parent1] = parent2;
                sizes[parent2] += sizes[parent1];
            } else {
                parent[parent2] = parent1;
                sizes[parent1] += sizes[parent2];
            }
        }
    }

    //path compression
    private int find_set(int[] parent, int idx) {
        if (parent[idx] == idx) {
            return idx;
        }
        return parent[idx] = find_set(parent, parent[idx]);
    }

    //make each node as it's own parent
    private void make_set(int[] parent, int index, int[] sizes) {
        parent[index] = index;
        sizes[index] = 1;
    }
}
