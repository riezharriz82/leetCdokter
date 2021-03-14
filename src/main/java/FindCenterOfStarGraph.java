import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-center-of-star-graph/
 * <p>
 * There is an undirected star graph consisting of n nodes labeled from 1 to n.
 * A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
 * <p>
 * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.
 * <p>
 * Input: edges = [[1,2],[2,3],[4,2]]
 * Output: 2
 * Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
 * <p>
 * Input: edges = [[1,2],[5,1],[1,3],[1,4]]
 * Output: 1
 * <p>
 * Constraints:
 * 3 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * The given edges represent a valid star graph.
 */
public class FindCenterOfStarGraph {
    /**
     * Approach: Center node will have exactly n-1 edges. So create the graph and keep track of the no of edges of each node.
     * <p>
     * An alternate O(1) time and O(1) space solution would be to leverage the fact that center node appears in every edge,
     * so we can compare edge[0] and edge[1] and find the node that is common in both of them. That should be our answer.
     * <p>
     * {@link MinHeightTree}
     */
    public int findCenter(int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int n = edges.length + 1;
        for (int i = 1; i <= n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        for (int i = 1; i <= n; i++) {
            if (graph.get(i).size() == n - 1) {
                return i;
            }
        }
        return -1;
    }
}
