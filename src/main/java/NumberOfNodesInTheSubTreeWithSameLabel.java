import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/
 * <p>
 * Given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1
 * and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a lower-case character
 * given in the string labels (i.e. The node with the number i has the label labels[i]).
 * <p>
 * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.
 * <p>
 * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.
 * <p>
 * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
 * <p>
 * Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
 * Output: [4,2,1,1]
 * Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
 * The sub-tree of node 3 contains only node 3, so the answer is 1.
 * The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
 * The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.
 */
public class NumberOfNodesInTheSubTreeWithSameLabel {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> graph = buildTree(n, edges);
        boolean[] visited = new boolean[n];
        int[] countLabels = new int[n]; //store the final result
        traverse(graph, labels, visited, 0, countLabels);
        return countLabels;
    }

    private int[] traverse(List<List<Integer>> graph, String labels, boolean[] visited, int index, int[] countLabels) {
        visited[index] = true; //mark the node as visited so not to visit it again during DFS
        List<int[]> children = new ArrayList<>(); //stores the char count for each child
        for (int child : graph.get(index)) {
            if (!visited[child]) {
                children.add(traverse(graph, labels, visited, child, countLabels));
            }
        }
        int[] merged = new int[26]; //only lower case characters
        for (int[] child : children) { //iterate result for each child and merge them
            for (int i = 0; i < 26; i++) {
                merged[i] += child[i];
            }
        }
        merged[labels.charAt(index) - 'a']++; //update char count for current index
        countLabels[index] = merged[labels.charAt(index) - 'a']; //update the result for the current index
        return merged;
    }

    private List<List<Integer>> buildTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]); //undirected graph
        }
        return graph;
    }
}
