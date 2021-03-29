import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/parallel-courses/ Premium
 * <p>
 * You are given an integer n which indicates that we have n courses, labeled from 1 to n. You are also given an array relations where relations[i] = [a, b],
 * representing a prerequisite relationship between course a and course b: course a has to be studied before course b.
 * <p>
 * In one semester, you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 * <p>
 * Return the minimum number of semesters needed to study all courses. If there is no way to study all the courses, return -1.
 * <p>
 * Input: n = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation: In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
 * <p>
 * Input: n = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: No course can be studied because they depend on each other.
 * <p>
 * Constraints:
 * 1 <= n <= 5000
 * 1 <= relations.length <= 5000
 * 1 <= a, b <= n
 * a != b
 * All the pairs [a, b] are unique.
 */
public class ParallelCourses {
    /**
     * Approach: DP on Graph. Find the length of longest path in a DAG using DFS + Memoization, it will be equivalent to minimum no of semesters required.
     * <p>
     * This could also have been solved using BFS, very similar to Khan's Topological sorting algorithm.
     * Push nodes with indegree 0 to queue and while traversal, decrement the indegree of adjacent nodes. Push to queue if indegree == 0
     * In the end check if all nodes have been visited. If no, return -1
     * <p>
     * {@link CourseSchedule} {@link CourseSchedule2} {@link LongestIncreasingPathInMatrix}
     */
    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) { //1 based indexing
            graph.add(new ArrayList<>());
        }
        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
        }
        STATE[] states = new STATE[n + 1]; //states for detecting cycle
        int[] depth = new int[n + 1]; //length of DAG at index i
        Arrays.fill(states, STATE.UNKNOWN);
        int maxDepth = 0;
        for (int i = 1; i <= n; i++) {
            if (states[i] == STATE.UNKNOWN) {
                boolean hasCycle = DFS(i, graph, states, depth);
                if (hasCycle) {
                    return -1;
                } else {
                    maxDepth = Math.max(maxDepth, depth[i]);
                }
            }
        }
        return maxDepth;
    }

    //returns true in case a cycle is found
    private boolean DFS(int index, List<List<Integer>> graph, STATE[] states, int[] depth) {
        if (states[index] == STATE.VISITING) {
            return true; //cycle found
        } else if (states[index] == STATE.VISITED) {
            return false;
        } else {
            states[index] = STATE.VISITING;
            int maxChildDepth = 0;
            for (int dependent : graph.get(index)) {
                boolean hasCycle = DFS(dependent, graph, states, depth);
                if (hasCycle) {
                    return true;
                } else {
                    //get the depth of neighbour by looking at depth[]
                    maxChildDepth = Math.max(maxChildDepth, depth[dependent]);
                }
            }
            depth[index] = 1 + maxChildDepth;
            states[index] = STATE.VISITED;
            return false; //no cycle found
        }
    }

    enum STATE {
        VISITING, VISITED, UNKNOWN
    }
}
