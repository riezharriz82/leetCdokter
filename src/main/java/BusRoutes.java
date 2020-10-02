import java.util.*;

/**
 * https://leetcode.com/problems/bus-routes/
 * <p>
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7],
 * this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 * <p>
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T.
 * Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 * <p>
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 */
public class BusRoutes {
    /**
     * Approach: Very tricky BFS question, generally in BFS question, we are asked to minimize the path length.
     * We do that by enumerating the next state ie. states that are one hop away from the current state and pushing it to a queue.
     * <p>
     * Here the main trick is to identify what is the state? Since the problem asks to minimize the no of buses, that should be the state.
     * We start the traversal by asking what will be the initial state ? It will be the no of buses passing through that stop.
     * <p>
     * Next state would be the list of buses that can be boarded from the current state.
     * Use a visited array to avoid repeated states.
     * <p>
     * It's a bit tricky because it's very easy to do a traditional BFS on the path length but that will probably time out.
     * I tried to implement it but got WA.
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        Map<Integer, List<Integer>> map = buildGraph(routes); //map of stopID to list of buses going through that stop
        ArrayDeque<Integer> queue = new ArrayDeque<>(); //queue of stopID
        Set<Integer> visitedBuses = new HashSet<>(); //set of visited buses
        Set<Integer> visitedStops = new HashSet<>();
        queue.add(S);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int head = queue.remove();
                if (head == T) {
                    return level - 1;
                } else {
                    for (int busID : map.get(head)) { //get the list of busID's that are reachable from current stop
                        if (!visitedBuses.contains(busID)) {
                            visitedBuses.add(busID);
                            for (int stopID : routes[busID]) {
                                if (!visitedStops.contains(stopID)) {
                                    visitedStops.add(stopID);
                                    queue.add(stopID);
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    //key is StopId, value is the list of busId's that pass through that stop
    private Map<Integer, List<Integer>> buildGraph(int[][] routes) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                map.computeIfAbsent(routes[i][j], __ -> new ArrayList<>()).add(i);
            }
        }
        return map;
    }
}
