import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/graph-valid-tree/ Premium
 * <p>
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 * <p>
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * <p>
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */
public class GraphValidTree {
    /**
     * Approach: Graph questions can be solved using union find as well
     * Here I am using path compression to directly connect the descendants to its parent
     * Another optimization to do is in union_set() method, where instead of arbitrarily updating the parent, you should update
     * the parent of smaller set, to find the size of the set under a node, create another array of rank[]
     * <p>
     * Look in the commented code for well explained solution for path compression as well as rank
     * {@link FindGroupWithSizeM}
     */
    public boolean validTreeUnionFind(int n, int[][] edges) {
        if (edges.length != n - 1) { //tree contains exactly n-1 edges
            return false;
        }
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            make_set(parent, i);
        }
        for (int[] edge : edges) {
            int root1 = find_set(parent, edge[0]);
            int root2 = find_set(parent, edge[1]);
            if (root1 == root2) { //loop found
                return false;
            }
            parent[root1] = root2; //update the parent of root1 to point to root2
            //visualize it as connecting the parent of set1 to parent of set2
        }
        return true;
    }

    private void make_set(int[] parent, int index) {
        parent[index] = index;
    }

    private int find_set(int[] parent, int index) {
        if (parent[index] == index) {
            return index;
        }
        return parent[index] = find_set(parent, parent[index]);
    }

    /**
     * Approach: Tree does not have any cycles, so do a DFS from first node and see whether you find any cycle
     * Since the graph is undirected, care must be taken to exclude parent node during cycle check
     * <p>
     * Also a tree is fully connected, so all nodes should be visited after the DFS
     * <p>
     * An optimization to that would be to validate total no of edges first i.e a tree of n elements contains exactly n-1 edges
     * If less than n-1, tree isn't fully connected
     * If greater than n-1, tree has cycles
     */
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> graph = buildGraph(n, edges);
        boolean[] visited = new boolean[n];
        if (!DFS(graph, visited, 0, -1)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean DFS(List<List<Integer>> graph, boolean[] visited, int index, int parent) {
        visited[index] = true;
        for (int neighbor : graph.get(index)) {
            if (visited[neighbor] && neighbor != parent) {
                return false;
            } else if (!visited[neighbor]) {
                if (!DFS(graph, visited, neighbor, index)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        ArrayList<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    /*
     * class UnionFind {
     *
     *     private int[] parent;
     *     private int[] size; // We use this to keep track of the size of each set.
     *
     *     // For efficiency, we aren't using makeset, but instead initialising
     *     // all the sets at the same time in the constructor.
     *     public UnionFind(int n) {
     *         parent = new int[n];
     *         size = new int[n];
     *         for (int node = 0; node < n; node++) {
     *             parent[node] = node;
     *             size[node] = 1;
     *         }
     *     }
     *
     *     // The find method, with path compression. There are ways of implementing
     *     // this elegantly with recursion, but the iterative version is easier for
     *     // most people to understand!
     *     public int find(int A) {
     *         // Step 1: Find the root.
     *         int root = A;
     *         while (parent[root] != root) {
     *             root = parent[root];
     *         }
     *         // Step 2: Do a second traversal, this time setting each node to point
     *         // directly at A as we go.
     *         while (A != root) {
     *             int oldRoot = parent[A];
     *             parent[A] = root;
     *             A = oldRoot;
     *         }
     *         return root;
     *     }
     *
     *     // The union method, with optimization union by size. It returns True if a
     *     // merge happened, False if otherwise.
     *     public boolean union(int A, int B) {
     *         // Find the roots for A and B.
     *         int rootA = find(A);
     *         int rootB = find(B);
     *         // Check if A and B are already in the same set.
     *         if (rootA == rootB) {
     *             return false;
     *         }
     *         // We want to ensure the larger set remains the root.
     *         if (size[rootA] < size[rootB]) {
     *             // Make rootB the overall root.
     *             parent[rootA] = rootB;
     *             // The size of the set rooted at B is the sum of the 2.
     *             size[rootB] += size[rootA];
     *         }
     *         else {
     *             // Make rootA the overall root.
     *             parent[rootB] = rootA;
     *             // The size of the set rooted at A is the sum of the 2.
     *             size[rootA] += size[rootB];
     *         }
     *         return true;
     *     }
     * }
     *
     * class Solution {
     *
     *     public boolean validTree(int n, int[][] edges) {
     *
     *         // Condition 1: The graph must contain n - 1 edges.
     *         if (edges.length != n - 1) return false;
     *
     *         // Condition 2: The graph must contain a single connected component.
     *         // Create a new UnionFind object with n nodes.
     *         UnionFind unionFind = new UnionFind(n);
     *         // Add each edge. Check if a merge happened, because if it
     *         // didn't, there must be a cycle.
     *         for (int[] edge : edges) {
     *             int A = edge[0];
     *             int B = edge[1];
     *             if (!unionFind.union(A, B)) {
     *                 return false;
     *             }
     *         }
     *
     *         // If we got this far, there's no cycles!
     *         return true;
     *     }
     *
     * }
     */
}
