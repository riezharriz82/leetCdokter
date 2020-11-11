import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/the-most-similar-path-in-a-graph/
 * <p>
 * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi.
 * Each city has a name consisting of exactly 3 upper-case English letters given in the string array names.
 * Starting at any city x, you can reach any city y where y != x (i.e. the cities and the roads are forming an undirected connected graph).
 * <p>
 * You will be given a string array targetPath. You should find a path in the graph of the same length and with the minimum edit distance to targetPath.
 * <p>
 * You need to return the order of the nodes in the path with the minimum edit distance, The path should be of the same length of targetPath
 * and should be valid (i.e. there should be a direct road between ans[i] and ans[i + 1]). If there are multiple answers return any one of them.
 * <p>
 * The edit distance is defined as follows:
 * <p>
 * Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
 * Output: [0,2,4,2]
 * Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
 * [0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
 * [0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
 * [0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.
 * <p>
 * Input: n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 * Output: [3,4,5,4,3,2,1]
 * Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
 * It's equivalent to ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 * <p>
 * Constraints:
 * 2 <= n <= 100
 * The graph is guaranteed to be connected and each pair of nodes may have at most one direct road.
 */
public class MostSimilarPathInAGraph {
    /**
     * Approach: Generate all the paths of specified length considering each city as the starting point and calculate the edit distance
     * <p>
     * Recursive solution was easy to implement but modifying it to be top-down compatible required a bit of extra efforts.
     * In recursive solution, when the curPath.size() == targetPath.length, calculate the edit distance and update the global minima along with resultant path
     * <p>
     * However in topDown solution, you have to rely on the result of the remaining path, so you have to get the edit distance of the remaining path.
     * Also we have to return the resultant path, so we need a way of generating the result path.
     * we do it by creating a 2d path array where we store the index of the next node
     * Consider the following path array
     * <pre>
     *     [0][1][2][3][4]
     * [0]  4  3  5  6  -
     * </pre>
     * This means that if we consider 0th node as the starting point, next node will be 4
     * If we consider 0th node at the 1st index of the result path, next node will be 3
     * If we consider 0th node at the 3rd index of the result path, next node will be 6
     * <p>
     * It's a very clever way of storing the result path, without actually returning the result path from the recursion.
     */
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<List<Integer>> graph = buildGraph(roads, names.length);
        int[][] path = new int[n][targetPath.length];
        int[][] memoizedEditDistance = new int[n][targetPath.length]; //memo
        for (int[] ints : memoizedEditDistance) {
            Arrays.fill(ints, -1);
        }
        int minEditDistance = Integer.MAX_VALUE;
        int startIndex = -1;
        for (int i = 0; i < names.length; i++) { //consider each node as the starting point
            int editDistance = DFS(graph, names, path, i, targetPath, 0, memoizedEditDistance);
            if (editDistance < minEditDistance) {
                minEditDistance = editDistance;
                startIndex = i;
            }
        }
        List<Integer> resultPath = new ArrayList<>();
        int idx = startIndex;
        resultPath.add(idx);
        for (int i = 0; i < targetPath.length - 1; i++) { //generate the result path by walking the path array, take care of the indices
            idx = path[idx][i];
            resultPath.add(idx);
        }
        return resultPath;
    }

    private int DFS(List<List<Integer>> graph, String[] names, int[][] path, int graphIndex, String[] targetPath, int pathIndex, int[][] memoizedEditDistance) {
        if (pathIndex == targetPath.length) {
            return 0;
        } else if (memoizedEditDistance[graphIndex][pathIndex] != -1) {
            return memoizedEditDistance[graphIndex][pathIndex];
        } else {
            int curEditDistance = 0, minRemainingEditDistance = Integer.MAX_VALUE;
            if (!names[graphIndex].equals(targetPath[pathIndex])) {
                curEditDistance++;
            }
            for (int neighbor : graph.get(graphIndex)) { //consider all neighbour nodes and find which node gives the minimum edit distance
                int remainEditDistance = DFS(graph, names, path, neighbor, targetPath, pathIndex + 1, memoizedEditDistance);
                if (remainEditDistance < minRemainingEditDistance) {
                    minRemainingEditDistance = remainEditDistance;
                    path[graphIndex][pathIndex] = neighbor; //tricky logic to store the neighbour as the next chain in the result
                }
            }
            return memoizedEditDistance[graphIndex][pathIndex] = curEditDistance + minRemainingEditDistance;
        }
    }

    private List<List<Integer>> buildGraph(int[][] roads, int n) {
        ArrayList<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        return graph;
    }
}
