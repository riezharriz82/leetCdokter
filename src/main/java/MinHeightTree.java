import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-height-trees/
 * <p>
 * For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree.
 * Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.
 * <p>
 * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).
 * <p>
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 * <p>
 * Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 * <pre>
 *      0  1  2
 *       \ | /
 *         3
 *         |
 *         4
 *         |
 *         5
 * </pre>
 * Output: [3, 4]
 */
public class MinHeightTree {

    /**
     * 0) Pick arbitrary starting node
     * <p>
     * 1) Do a BFS/DFS search (it does not matter as trees have unique paths), keeping
     * track of the farthest node (any will make the trick, as there could be several
     * with same distance from starting node)
     * <p>
     * 2) Repeat 1) but starting from the farthest node found. The path from this new
     * starting point ands its new farthest node will be the diameter of the tree.
     * <p>
     * 3) There is a theorem saying that center lies at the middle of the diameter,
     * seen as a path. If such path has odd length, there are two centers.
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> graph = buildGraph(edges, n);
        boolean[] visited = new boolean[n];
        int[] distanceFromInitialNode = new int[n];

        findFarthestNode(graph, 0, visited, distanceFromInitialNode, 0);
        int maxDistance = Integer.MIN_VALUE;
        int farthestNode = 0;
        for (int i = 0; i < n; i++) {
            if (distanceFromInitialNode[i] > maxDistance) {
                maxDistance = distanceFromInitialNode[i];
                farthestNode = i;
            }
        }

        int[] distanceFromFarthestNode = new int[n];
        Arrays.fill(visited, false);
        findFarthestNode(graph, farthestNode, visited, distanceFromFarthestNode, 0);
        maxDistance = Integer.MIN_VALUE;
        int anotherFarthestNode = 0;
        for (int i = 0; i < n; i++) {
            if (distanceFromFarthestNode[i] > maxDistance) {
                maxDistance = distanceFromFarthestNode[i];
                anotherFarthestNode = i;
            }
        }

        Arrays.fill(visited, false);
        int[] path = new int[n];
        path[farthestNode] = -1;
        getPath(graph, farthestNode, anotherFarthestNode, visited, path);

        //recreate the path by walking the parent node and storing the result in another array
        int[] recreatePath = new int[n];
        int index = 0, curNode = anotherFarthestNode;
        while (curNode != -1) {
            recreatePath[index++] = curNode;
            curNode = path[curNode];
        }
        index--; //index has shifted extra by one
        if (index % 2 == 0) { //index starts from 0 so if it's even, then the length of the path is odd, only one center
            return Arrays.asList(recreatePath[index / 2]);
        } else { //two centers
            return Arrays.asList(recreatePath[index / 2], recreatePath[(index / 2) + 1]);
        }
    }

    //find the parent node of each node present in the path from start node to end node
    private void getPath(List<List<Integer>> graph, int start, int end, boolean[] visited, int[] path) {
        visited[start] = true;
        if (start == end) {
            return;
        }
        for (Integer adjacentNode : graph.get(start)) {
            if (!visited[adjacentNode]) {
                path[adjacentNode] = start; //mark the parent node of the adjacent node
                getPath(graph, adjacentNode, end, visited, path);
            }
        }
    }

    //find distance of each node from index
    private void findFarthestNode(List<List<Integer>> graph, int index, boolean[] visited, int[] distanceFromRandomNode, int distance) {
        visited[index] = true;
        if (distanceFromRandomNode[index] < distance) {
            distanceFromRandomNode[index] = distance;
        }
        for (Integer adjacentNode : graph.get(index)) {
            if (!visited[adjacentNode]) {
                findFarthestNode(graph, adjacentNode, visited, distanceFromRandomNode, distance + 1);
            }
        }
    }

    private List<List<Integer>> buildGraph(int[][] edges, int n) {
        List<List<Integer>> lst = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            lst.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            lst.get(edge[0]).add(edge[1]);
            lst.get(edge[1]).add(edge[0]); //undirected
        }
        return lst;
    }
}
