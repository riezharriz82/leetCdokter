import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/kill-process/
 * <p>
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 * <p>
 * Each process only has one parent process, but may have one or more children processes. This is just like a tree structure.
 * Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.
 * <p>
 * We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.
 * <p>
 * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end.
 * You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.
 * <p>
 * Input:
 * pid =  [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 5
 * Output: [5,10]
 * Explanation:
 * <pre>
 *            3
 *          /   \
 *         1     5
 *              /
 *             10
 * </pre>
 * Kill 5 will also kill 10.
 * Note:
 * The given kill id is guaranteed to be one of the given PIDs.
 * n >= 1.
 */
public class KillProcess {
    /**
     * Approach: Create a directed graph by using hashmap and then recurse from the process to kill
     * <p>
     * {@link DeleteNodesAndReturnForest} related problem
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = pid.size();
        for (int i = 0; i < n; i++) {
            int parent = ppid.get(i);
            int child = pid.get(i);
            graph.computeIfAbsent(parent, __ -> new ArrayList<>()).add(child); //directed graph from parent pid -> child pid
        }
        List<Integer> result = new ArrayList<>();
        DFS(result, kill, graph);
        return result;
    }

    private void DFS(List<Integer> result, int kill, Map<Integer, List<Integer>> graph) {
        result.add(kill);
        for (int child : graph.getOrDefault(kill, new ArrayList<>())) { //recursively visit all child pid
            DFS(result, child, graph);
        }
    }
}
