import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/time-needed-to-inform-all-employees/
 * <p>
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the one with headID.
 * <p>
 * Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th employee,
 * manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.
 * <p>
 * The head of the company wants to inform all the employees of the company of an urgent piece of news.
 * He will inform his direct subordinates and they will inform their subordinates and so on until all employees know about the urgent news.
 * <p>
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i] minutes,
 * all his direct subordinates can start spreading the news).
 * <p>
 * Return the number of minutes needed to inform all the employees about the urgent news.
 * <p>
 * Input: n = 7, headID = 6, manager = [1,2,3,4,5,6,-1], informTime = [0,6,5,4,3,2,1]
 * Output: 21
 * Explanation: The head has id = 6. He will inform employee with id = 5 in 1 minute.
 * The employee with id = 5 will inform the employee with id = 4 in 2 minutes.
 * The employee with id = 4 will inform the employee with id = 3 in 3 minutes.
 * The employee with id = 3 will inform the employee with id = 2 in 4 minutes.
 * The employee with id = 2 will inform the employee with id = 1 in 5 minutes.
 * The employee with id = 1 will inform the employee with id = 0 in 6 minutes.
 * Needed time = 1 + 2 + 3 + 4 + 5 + 6 = 21.
 */
public class TimeNeededToInformAllEmployees {

    /**
     * Build tree, Do DFS from manager. Trick is to return max time from all the reportees
     * E.g. If manager has two nodes and first reportee takes 10 min and another one takes 20 min
     * So it will take only 20 minute total not 30 minute
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] != -1) {
                tree.get(manager[i]).add(i);
            }
        }
        return DFS(tree, headID, informTime);
    }

    private int DFS(List<List<Integer>> tree, int headID, int[] informTime) {
        int sum = 0;
        for (int reportee : tree.get(headID)) {
            sum = Math.max(sum, DFS(tree, reportee, informTime));
        }
        return sum + informTime[headID];
    }
}
