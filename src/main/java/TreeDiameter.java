import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/tree-diameter/
 * <p>
 * Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
 * <p>
 * The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.
 * <p>
 * Input: edges = [[0,1],[0,2]]
 * Output: 2
 * Explanation:
 * A longest path of the tree is the path 1 - 0 - 2.
 * <p>
 * Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
 * Output: 4
 * Explanation:
 * A longest path of the tree is the path 3 - 2 - 1 - 4 - 5.
 * <p>
 * Constraints:
 * 0 <= edges.length < 10^4
 * edges[i][0] != edges[i][1]
 * 0 <= edges[i][j] <= edges.length
 * The given edges form an undirected tree.
 */
public class TreeDiameter {
    int maxDiameter;

    /**
     * Approach: Use DFS to keep track of longest depth of each subtree.
     * Since it's a n-ary tree and root is not provided, we can perform DFS from any node and keep updating the max diameter found
     * Max diameter = Sum of two longest depths
     * <p>
     * A bit tricky thing was to keep track of two largest values in an array using two variables O(n) time instead of sorting
     * <p>
     * {@link DiameterOfBinaryTree} {@link MinHeightTree}
     */
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        //since it's a tree, there are no cycles, visited[] can be avoided if we keep track of parent node and skip parent node
        DFS(graph, 0, visited);
        return maxDiameter;
    }

    private int DFS(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        List<Integer> children = new ArrayList<>();
        for (int neighbour : graph.get(node)) {
            if (!visited[neighbour]) {
                children.add(DFS(graph, node, visited));
            }
        }
        int longest = 0, secondLongest = 0;
        for (int val : children) {
            if (val > longest) { //if found a value greater than largest value, current largest value will become second largest
                secondLongest = longest;
                longest = val;
            } else if (val > secondLongest) { //if found a value greater than second largest value, it will become second largest value
                secondLongest = val;
            }
        }
        maxDiameter = Math.max(maxDiameter, longest + secondLongest);
        return 1 + longest;
    }
}
