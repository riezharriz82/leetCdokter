import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/path-with-maximum-probability/
 * <p>
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b]
 * is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 * <p>
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 * <p>
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 * <p>
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 */
public class PathWithMaxProbability {

    //you can use djikstra to find path with maximum probability but you can not use it to find longest path
    //finding longest path is NP hard problem, for DAG graph, it can be solved in linear time using topological sort
    //then for each vertices in topological ordering, update the longest path for its adjacent nodes
    public double maxProbabilityUsingDjikstra(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<Pair<Integer, Double>>> nodes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            nodes.get(edges[i][0]).add(new Pair<>(edges[i][1], succProb[i]));
            nodes.get(edges[i][1]).add(new Pair<>(edges[i][0], succProb[i])); //its a undirected graph, add reverse edge as well
        }
        double[] distance = new double[n];
        boolean[] visited = new boolean[n];
        Djikstra(nodes, distance, visited, start, end);
        return distance[end];
    }

    private void Djikstra(List<List<Pair<Integer, Double>>> nodes, double[] distance, boolean[] visited, int start, int end) {
        PriorityQueue<Pair<Integer, Double>> minHeap = new PriorityQueue<>((o1, o2) -> Double.compare(o2.getValue(), o1.getValue()));
        minHeap.add(new Pair<>(start, 1D));
        distance[start] = 1;
        while (!minHeap.isEmpty()) {
            Pair<Integer, Double> head = minHeap.remove();
            visited[head.getKey()] = true;
            if (head.getKey() == end) {
                return; // no need to continue finding max probability for all the nodes
            }
            for (Pair<Integer, Double> adjacentNodes : nodes.get(head.getKey())) {
                if (distance[adjacentNodes.getKey()] < distance[head.getKey()] * adjacentNodes.getValue() && !visited[adjacentNodes.getKey()]) {
                    distance[adjacentNodes.getKey()] = distance[head.getKey()] * adjacentNodes.getValue();
                    minHeap.add(new Pair<>(adjacentNodes.getKey(), distance[adjacentNodes.getKey()]));
                }
            }
        }
    }

    public double maxProbabilityUsingBFS(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<Pair<Integer, Double>>> nodes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            nodes.get(edges[i][0]).add(new Pair<>(edges[i][1], succProb[i]));
            nodes.get(edges[i][1]).add(new Pair<>(edges[i][0], succProb[i])); //its a undirected graph, add reverse edge as well
        }
        double[] distance = new double[n];

        //doing a simple dfs from the start and backtracking, trying all the combination times out
        //need to use bfs and add to queue only if you can find a better probability than the current path
        //not keeping a visited array as a node can be reached from multiple paths

        //When handling probabilities you have to treat the problem as a shortest path problem as with every multiplication you add some error,
        // the more numbers you multiply the bigger the error, until it eventually will underflow, this is a well known problem. That's why DFS won't work,
        // it won't warranty that you reach a certain point using the minimum number of steps so at some point if the input is too big the solution will
        // underflow or you will get a TLE because you are considering too many paths that are useless.
        BFS(nodes, distance, start);
        return distance[end];
    }

    private void BFS(List<List<Pair<Integer, Double>>> nodes, double[] distance, int start) {
        distance[start] = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int head = queue.remove();
            for (Pair<Integer, Double> adjacentNodes : nodes.get(head)) {
                //new path has more probability than the current distance
                if (distance[adjacentNodes.getKey()] < distance[head] * adjacentNodes.getValue()) {
                    distance[adjacentNodes.getKey()] = distance[head] * adjacentNodes.getValue();
                    queue.add(adjacentNodes.getKey());
                }
            }
        }
    }
}
