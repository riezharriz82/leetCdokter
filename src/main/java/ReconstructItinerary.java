import java.util.*;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 * <p>
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * <p>
 * Note:
 * <p>
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * One must use all the tickets once and only once.
 * <p>
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * <p>
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 * But it is larger in lexical order.
 */
public class ReconstructItinerary {
    List<String> result;

    /**
     * Approach: Hierholzer’s algorithm for printing eulerian path
     * This problem requires us to visit each edge exactly once and hence it asks us to find eulerian path
     * <p>
     * This is very similar to doing a postorder traversal of the graph or loosely speaking topological sort the graph
     * <p>
     * {@link SequenceReconstruction} related topological ordering problem
     */
    public List<String> findItineraryEulerian(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            map.computeIfAbsent(ticket.get(0), __ -> new PriorityQueue<>()).add(ticket.get(1));
        }
        ArrayDeque<String> stack = new ArrayDeque<>();
        DFS(map, stack, "JFK");
        ArrayList<String> result = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void DFS(HashMap<String, PriorityQueue<String>> map, ArrayDeque<String> stack, String airport) {
        PriorityQueue<String> neighbours = map.getOrDefault(airport, new PriorityQueue<>());
        while (!neighbours.isEmpty()) {
            DFS(map, stack, neighbours.poll());
        }
        stack.push(airport); //this is the key difference, in backtracking solution, we added the airport to the path before doing DFS
        //and then used to discard the result generated so far, if DFS could not use all tickets
        //here we don't discard the result, instead keep the result generated so far even though we could not use all the tickets
    }

    /**
     * Approach: Backtracking, try to greedily pick up the lexicographically smallest airport from current airport and see
     * whether we can use all the tickets or not, if not, backtrack and try other combination.
     * <p>
     * Gotchas: Parallel edges aka duplicate tickets from one node to another
     * <p>
     * Time complexity: Exponential
     */
    public List<String> findItineraryBacktracking(List<List<String>> tickets) {
        //to avoid the edges map, we could remove the neighbour from the graph during the DFS traversal, always poll the first node until no neighbours left
        HashMap<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> edges = new HashMap<>(); //required because of parallel/duplicate edges
        for (List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.get(0), __ -> new ArrayList<>()).add(ticket.get(1)); //directed graph
            String key = ticket.get(0) + "->" + ticket.get(1);
            edges.put(key, edges.getOrDefault(key, 0) + 1);
        }
        for (List<String> value : graph.values()) {
            //sort to always first pick up the smallest airport first
            Collections.sort(value);
        }
        ArrayList<String> curPath = new ArrayList<>();
        curPath.add("JFK");
        DFS(graph, "JFK", curPath, edges, tickets.size() + 1);
        return result;
    }

    private boolean DFS(HashMap<String, List<String>> map, String airport, List<String> curPath, Map<String, Integer> visited, int targetLength) {
        List<String> neighbours = map.getOrDefault(airport, new ArrayList<>());
        if (curPath.size() == targetLength) { //if all tickets have been consumed, we got a valid path
            result = new ArrayList<>(curPath);
            return true;
        }
        for (String neighbour : neighbours) {
            String key = airport + "->" + neighbour;
            if (visited.getOrDefault(key, 0) > 0) { //if this edge can be used
                visited.put(key, visited.get(key) - 1);
                curPath.add(neighbour); //add this node to the current path
                if (DFS(map, neighbour, curPath, visited, targetLength)) {
                    return true;
                }
                //could not visit all nodes, need to backtrack
                curPath.remove(curPath.size() - 1);
                visited.put(key, visited.get(key) + 1);
            }
        }
        return false;
    }
}
