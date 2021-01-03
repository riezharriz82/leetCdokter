import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/course-schedule/
 * <p>
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length != 0) {
            List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
            STATE[] states = new STATE[numCourses];
            Arrays.fill(states, STATE.UNKNOWN); //don't forget to initialize it with UNKNOWN states
            for (int i = 0; i < numCourses; i++) {
                //try each node as the starting point of DFS as it's not a complete graph
                if (states[i] == STATE.UNKNOWN && hasCycle(states, graph, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    //perform topological sort (postorder traversal), return true if it has cycles
    private boolean hasCycle(STATE[] states, List<List<Integer>> graph, int startingCourse) {
        if (states[startingCourse] != STATE.UNKNOWN) {
            return states[startingCourse] == STATE.VISITING; //if the node is in a visiting state, we have a cycle
        }
        states[startingCourse] = STATE.VISITING;
        for (Integer adjacentNode : graph.get(startingCourse)) {
            if (hasCycle(states, graph, adjacentNode)) {
                return true;
            }
        }
        states[startingCourse] = STATE.VISITED;
        return false;
    }

    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        return graph;
    }

    private enum STATE {
        VISITING, VISITED, UNKNOWN
    }
}
