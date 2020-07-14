import java.util.ArrayList;
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
            boolean[] visited = new boolean[numCourses];
            boolean[] toDo = new boolean[numCourses];
            for (int i = 0; i < numCourses; i++) {
                //try each node as the starting point of DFS as it's not a complete graph
                if (!visited[i] && hasCycle(visited, toDo, graph, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    //topological sort
    private boolean hasCycle(boolean[] visited, boolean[] toDo, List<List<Integer>> graph, int startingCourse) {
        if (visited[startingCourse]) {
            return false;
        }
        if (toDo[startingCourse]) {
            return true; // same node found in the recursion stack
        }
        toDo[startingCourse] = true;
        for (Integer adjacentNode : graph.get(startingCourse)) {
            boolean cycle = hasCycle(visited, toDo, graph, adjacentNode);
            if (cycle) {
                return true;
            }
        }
        toDo[startingCourse] = false;
        visited[startingCourse] = true;
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
}
