import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/network-delay-time/
 * <p>
 * There are N network nodes, labelled 1 to N.
 * <p>
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node,
 * and w is the time it takes for a signal to travel from source to target.
 * <p>
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 * <p>
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 * <p>
 * Note:
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 */
public class NetworkDelayTime {
    /**
     * Approach: Use Djikstra algo to find the shortest distance from single source to all remaining nodes
     * <p>
     * {@link PathWithMaxProbability} {@link CheapestFlightWithinKStop} {@link PathWithMinimumEffort} related djikstra problems
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>(); //pair of node, time
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new Pair<>(time[1], time[2]));
        }
        //min heap based on picking nodes with shortest time first
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.getValue(), o2.getValue())));
        pq.add(new Pair<>(K, 0));
        distance[K] = 0;
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> head = pq.poll();
            int curDistance = head.getValue();
            int curNode = head.getKey();
            for (Pair<Integer, Integer> neighbour : graph.get(curNode)) {
                if (distance[neighbour.getKey()] > curDistance + neighbour.getValue()) {
                    //only add to pq if visiting this edge will reduce the time taken to reach neighbour node
                    distance[neighbour.getKey()] = curDistance + neighbour.getValue();
                    pq.add(new Pair<>(neighbour.getKey(), curDistance + neighbour.getValue()));
                }
            }
        }
        int maxDistance = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxDistance = Math.max(distance[i], maxDistance); //keep track of the max time to reach it's neighbours
        }
        return maxDistance == Integer.MIN_VALUE ? -1 : maxDistance;
    }
}
