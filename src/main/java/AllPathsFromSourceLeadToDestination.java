import java.util.ArrayList;
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
        boolean[] visited = new boolean[n];
        boolean[] toDo = new boolean[n];
        return DFS(graph, source, destination, visited, toDo);
    }

    private boolean DFS(List<List<Integer>> graph, int source, int destination, boolean[] visited, boolean[] toDo) {
        if (toDo[source]) {
            //found a node that is not yet processed, cycle found
            return false;
        }
        if (graph.get(source).isEmpty()) {
            //if reached a node with no outdegree, it should be the destination node
            return source == destination;
        }
        if (visited[source]) {
            //if this node is already visited (in case of common path e.g. LCA) return true
            //we don't need to worry about returning false because if during previous traversal that path did not led to destination, then
            //it would have already returned false and would have shortcircuited
            return true;
        }
        toDo[source] = true;
        for (int neighbours : graph.get(source)) {
            if (!DFS(graph, neighbours, destination, visited, toDo)) {
                //short circuit
                return false;
            }
        }
        visited[source] = true;
        toDo[source] = false; //mark the node as done
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
}
