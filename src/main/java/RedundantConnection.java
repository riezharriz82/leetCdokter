/**
 * https://leetcode.com/problems/redundant-connection/
 * <p>
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * <p>
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added.
 * The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 * <p>
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 *
 * <pre>
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 *
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * </pre>
 */
public class RedundantConnection {
    /**
     * Approach: Leverage Union find algorithm to find the first edge that is introducing cycle
     * <p>
     * {@link GraphValidTree} related problem
     */
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[1009];
        for (int i = 0; i < 1009; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            if (!union(edge[0], edge[1], parent)) {
                //cycle found
                return edge;
            }
        }
        return new int[]{-1, -1};
    }

    private int find(int idx, int[] parent) {
        if (idx == parent[idx]) {
            return idx;
        } else {
            return parent[idx] = find(parent[idx], parent);
        }
    }

    private boolean union(int idx1, int idx2, int[] parent) {
        int root1 = find(idx1, parent);
        int root2 = find(idx2, parent);
        if (root1 != root2) {
            parent[root1] = root2;
            return true;
        } else {
            return false;
        }
    }
}
