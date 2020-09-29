import javafx.util.Pair;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 * <p>
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 * <p>
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 * <p>
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 */
public class MinCostToConnectAllPoints {
    /**
     * Approach: Create a Minimum Spanning Tree using Prim's algorithm
     * <p>
     * Prim's algorithm is a greedy algorithm.
     * Repeat the process until all nodes are visited
     * --> Pick the node with the smallest distance and mark it visited
     * --> Update the distances of the adjacent nodes that are not visited
     * <p>
     * Each time one node is fixed
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue()));
        pq.add(new Pair<>(0, 0)); //can start with any node
        int totalCost = 0;
        while (visited.size() < n) {
            Pair<Integer, Integer> head = pq.remove();
            if (!visited.contains(head.getKey())) { //this is also required because it's not always required that the current node that is present at the top is unvisited
                visited.add(head.getKey());
                totalCost += head.getValue();
                for (int i = 0; i < n; i++) {
                    if (!visited.contains(i)) { //optimization, consider only those nodes that are unvisited otherwise we would fill the pq with redundant nodes
                        //e.g 2 --- 3  Consider case where  2 is already visited and has the lowest cost edge with 3.
                        //if 3 has lowest cost edge with 2 as well, if the visited check is not present, we will add back 2 again to the PQ
                        //which will be skipped next pop() because 2 is already visited
                        pq.add(new Pair<>(i, Math.abs(points[i][0] - points[head.getKey()][0]) + Math.abs(points[i][1] - points[head.getKey()][1])));
                    }
                }
            }
        }
        return totalCost;
    }
}
