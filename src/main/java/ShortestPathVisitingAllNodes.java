import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/shortest-path-visiting-all-nodes/
 * <p>
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 * <p>
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 * <p>
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 * <p>
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * <p>
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 */
public class ShortestPathVisitingAllNodes {
    /**
     * Approach: Since the problem talks about finding the shortest path, BFS is required as all edge weights are equal
     * Normal BFS won't work here as it will stop traversal as soon as you reach a node that is already visited,
     * but here we can visit a node multiple times, so instead key here is {set of nodes already visited, current node}
     * Current node is required to disambiguate between path shapes i.e path that cover the same nodes but differs in the shape
     * Relevant discussion here https://leetcode.com/problems/path-with-maximum-gold/discuss/399124/c-plain-dfs-vs-memorized-dfs-detailed-explanation/790152
     * <p>
     * To keep track of set of nodes already visited we use bitmasking by setting the bit of the current index in the mask
     * ie if we are visiting 3rd node, we set the 3rd bit (0 based indexing) in the mask using 1 << 3
     * <p>
     * The first path where all the bits are set i.e all nodes have been visited is the path with the least cost, BFS ensures it
     * <p>
     * {@link PathWithMaximumGold} related problem in which we could have applied this trick if input was bigger
     */
    public int shortestPathLength(int[][] graph) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>(); //pair of mask, current node
        for (int i = 0; i < graph.length; i++) {
            queue.add(new Node(1 << i, i, 0));
            visited.add(new Pair<>(1 << i, i));
        }
        while (!queue.isEmpty()) {
            Node head = queue.remove();
            if (Integer.bitCount(head.mask) == graph.length) { //all nodes have been visited
                return head.cost;
            }
            for (int neighbour : graph[head.currentVertex]) {
                int newMask = head.mask | (1 << neighbour); //set the bit of the neighbour
                Pair<Integer, Integer> key = new Pair<>(newMask, neighbour);
                if (!visited.contains(key)) {
                    visited.add(key);
                    queue.add(new Node(newMask, neighbour, head.cost + 1)); //increment the cost
                }
            }
        }
        return -1;
    }

    private static class Node {
        int mask;
        int currentVertex;
        int cost; //cost here is no of nodes visited, can be avoided if we use level of the bfs as the cost

        public Node(int mask, int currentVertex, int cost) {
            this.mask = mask;
            this.currentVertex = currentVertex;
            this.cost = cost;
        }
    }
}
