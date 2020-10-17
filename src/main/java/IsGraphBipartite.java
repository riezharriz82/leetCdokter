/**
 * https://leetcode.com/problems/is-graph-bipartite/
 * <p>
 * Given an undirected graph, return true if and only if it is bipartite.
 * <p>
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B
 * such that every edge in the graph has one node in A and another node in B.
 * <p>
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
 * Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 * <p>
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 */
public class IsGraphBipartite {
    /**
     * Approach: Similar to {@link PaintHouses} In case of bipartite graph, no directly connected nodes can have the same color
     * So we try to assign random color to first node, which will then try to assign opposite color to second node, which will assign opposite
     * color to third node and so on...
     * <p>
     * Although I visualized the problem as a coloring problem, it took me a hour to cleanly implement it. Initially it was a 100 line code! :D
     * Nevertheless, I did solve the problem on my own.
     * <p>
     * PS: Unconnected node is also part of the bipartite graph
     */
    public boolean isBipartite(int[][] graph) {
        int[] labels = new int[graph.length]; //-1 and 1 are two colors, 0 is unassigned
        for (int i = 0; i < graph.length; i++) { //need to iterate all the nodes, because the graph isn't fully connected
            if (labels[i] == 0) { //if this node is unassigned, start dfs and assign colors to all the neigbours
                if (!assign(graph, i, labels, -1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean assign(int[][] graph, int index, int[] labels, int assignment) {
        if (labels[index] != 0) {
            //if this index is already assigned, check if it's the same color as provided
            //eg. 0->2, 2->3, 3->0
            return labels[index] == assignment;
        }
        labels[index] = assignment;
        for (int neighbour : graph[index]) {
            if (!assign(graph, neighbour, labels, -assignment)) { //assign the opposite color to neighbour
                return false;
            }
        }
        return true;
    }
}
