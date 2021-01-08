import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/all-paths-from-source-lead-to-destination/
 * <p>
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi,
 * and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:
 * <p>
 * 1. At least one path exists from the source node to the destination node
 * 2. If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * 3. The number of possible paths from source to destination is a finite number.
 * <p>
 * Return true if and only if all roads from source lead to destination.
 * <p>
 * Look at leetcode url for diagram for various example input
 */
public class AllPathsFromSourceLeadToDestination {
    /**
     * Approach: Since all paths from source should lead to destination, DFS should be performed and cycles should be detected in the graph.
     * <p>
     * Cycle detection logic is similar to the one performed during topological sort {@link CourseSchedule}
     */
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = buildGraph(n, edges);
        STATE[] states = new STATE[n];
        Arrays.fill(states, STATE.UNKNOWN);
        return DFS(graph, source, destination, states);
    }

    private boolean DFS(List<List<Integer>> graph, int source, int destination, STATE[] states) {
        if (states[source] != STATE.UNKNOWN) {
            //if this node is already visited (in case of common path e.g. LCA) return true
            //we don't need to worry about returning false because if during previous traversal that path did not led to destination, then
            //it would have already returned false and would have short circuited
            return states[source] == STATE.VISITED;
        }
        if (graph.get(source).isEmpty()) {
            //if reached a node with no outdegree, it should be the destination node
            return source == destination;
        }
        states[source] = STATE.VISITING;
        for (int neighbours : graph.get(source)) {
            if (!DFS(graph, neighbours, destination, states)) {
                //short circuit
                return false;
            }
        }
        states[source] = STATE.VISITED; //mark the node as done
        return true;
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        ArrayList<List<Integer>> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]);
        }
        return list;
    }

    private enum STATE {
        VISITED, VISITING, UNKNOWN
    }
}
