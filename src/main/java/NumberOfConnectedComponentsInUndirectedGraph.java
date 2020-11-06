/**
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * <p>
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 * <pre>
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 *
 *      0          3
 *      |          |
 *      1 --- 2    4
 *
 * Output: 2
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 *
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 * Output:  1
 * </pre>
 */
public class NumberOfConnectedComponentsInUndirectedGraph {
    /**
     * Approach: Use union find algorithm, can also be solved by using DFS, wanted to thread water using union find
     * {@link NumberOfIslands}
     */
    int distinctComponents;

    public int countComponents(int n, int[][] edges) {
        distinctComponents = n; //initially every component is unique
        int[] parent = new int[n];
        make_set(parent, n);
        for (int[] edge : edges) {
            union_set(edge[0], edge[1], parent);
        }
        return distinctComponents;
    }

    private void union_set(int vertex1, int vertex2, int[] parent) {
        int root1 = find_set(vertex1, parent);
        int root2 = find_set(vertex2, parent);
        if (root1 != root2) {
            parent[root1] = root2;
            distinctComponents--; //each merge reduces the no of unique components
        }
    }

    private int find_set(int vertex, int[] parent) {
        if (parent[vertex] == vertex) {
            return vertex;
        }
        return parent[vertex] = find_set(parent[vertex], parent);
    }

    private void make_set(int[] parent, int n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
}
