import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/critical-connections-in-a-network/
 * <p>
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network
 * where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
 * <p>
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * <p>
 * Return all critical connections in the network in any order.
 * <p>
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * <p>
 * Constraints:
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 */
public class CriticalConnectionsInNetwork {
    /**
     * Approach: Tarzan algo to detect back edges, ie. check if it's possible to reach current node from its neighbour via another path
     * If yes, this path to neighbour is not a critical connection.
     * e.g A---B if B can reach A by following some other path, this edge isn't critical
     * <p>
     * How to know that? Mark the time at which a node was visited, if you can reach it's neighbour at a greater time, this means this path
     * is critical, because it signifies there is no other path to visit the neighbour
     * <p>
     * {@link OptimizeWaterDistributionInAVillage} another tricky problem
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> connection : connections) {
            graph.get(connection.get(0)).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }
        int[] time = new int[n]; //keeps track of the smallest time a node was visited
        List<List<Integer>> result = new ArrayList<>();
        recur(result, 0, time, 1, graph, -1); //since the entire graph is fully connected, we can start DFS from any node
        return result;
    }

    private int recur(List<List<Integer>> result, int index, int[] time, int curTime, List<List<Integer>> graph, int parentIndex) {
        if (time[index] != 0) { //if this node is already visited, return the time at which it was visited
            return time[index];
        }
        time[index] = curTime;
        for (int neighbour : graph.get(index)) {
            if (neighbour == parentIndex) { //skip the parent otherwise will end in cycle
                continue;
            }
            int neighborTime = recur(result, neighbour, time, curTime + 1, graph, index); //increment the current time and pass to neighbour
            time[index] = Math.min(time[index], neighborTime); //this is tricky, if neighbour is connected to another path, which reduces the time to visit
            //the neighbour, since current node is linked to neighbour, we can reach current node also in lower time, if any
            if (neighborTime > curTime) {
                //critical connection found, if neighbour can be reached in a time greater than the time this node was first visited
                //do notice that we are not comparing neighbourTime with time[index] because that keeps on changing because of neighbourTime, so it might be
                //possible that neighbourTime > time[index] but neighbourTime < curTime
                result.add(Arrays.asList(index, neighbour));
            }
        }
        return time[index];
    }
}
