import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-eventual-safe-states/
 * <p>
 * We start at some node in a directed graph, and every turn, we walk along a directed edge of the graph.
 * If we reach a terminal node (that is, it has no outgoing directed edges), we stop.
 * <p>
 * We define a starting node to be safe if we must eventually walk to a terminal node. More specifically, there is a natural number k,
 * so that we must have stopped at a terminal node in less than k steps for any choice of where to walk.
 * <p>
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 * <p>
 * The directed graph has n nodes with labels from 0 to n - 1, where n is the length of graph.
 * The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph, going from node i to node j.
 * <p>
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 * <p>
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 * <p>
 * Constraints:
 * n == graph.length
 * 1 <= n <= 104
 * 0 <= graph[i].legnth <= n
 * graph[i] is sorted in a strictly increasing order.
 * The graph may contain self-loops.
 * The number of edges in the graph will be in the range [1, 4 * 104].
 */
public class FindEventualSafeStates {
    /**
     * Approach: Problem reduces to finding cycle in a directed graph, so use cycle finding algorithm similar to toplogical sort
     * But need to maintain another state[] array to memoize prior state. So if an index is already visited, return the state previously stored
     * <p>
     * Instead of maintaining 3 arrays, we can create state enum denoting whether the index is safe/unsafe/unknown.
     * <p>
     * {@link AllPathsFromSourceToTarget} {@link AllPathsFromSourceLeadToDestination} {@link CourseSchedule}
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        State[] states = new State[n];
        Arrays.fill(states, State.UNKNOWN);
        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (DFS(i, graph, states)) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    //returns true in case the index is safe i.e. it has no cycle
    private boolean DFS(int index, int[][] graph, State[] states) {
        if (states[index] != State.UNKNOWN) {
            //if state is unsafe, a cycle is detected, so return false
            return states[index] == State.SAFE;
        }
        states[index] = State.UNSAFE; //mark the state as unsafe, before processing
        for (int neighbor : graph[index]) {
            if (!DFS(neighbor, graph, states)) {
                return false;
            }
        }
        states[index] = State.SAFE; //only mark the state as safe, if all it's children are in safe state
        return true;
    }

    private enum State {
        UNKNOWN,
        SAFE,
        UNSAFE
    }
}
