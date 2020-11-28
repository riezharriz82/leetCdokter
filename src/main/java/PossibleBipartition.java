import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/possible-bipartition/
 * <p>
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 * <p>
 * Each person may dislike some other people, and they should not go into the same group.
 * <p>
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 * <p>
 * Return true if and only if it is possible to split everyone into two groups in this way.
 * <p>
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * <p>
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * <p>
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 */
public class PossibleBipartition {
    /**
     * Approach: We are provided an array of dislikes, that is a relationship, whenever provided with relationships, think of creating a graph
     * Once graph is created, try to assign a color to the parent and the opposite color to its neighbour, check if its
     * possible to color the entire graph using this strategy
     * <p>
     * In my initial implementation, I did a recursive backtracking approach in which I assigned -1 to first person and 1 to it's disliked person
     * I would see if it's possible to assign those colors, if not, then I will backtrack and will try to flip the colors around
     * ie. try to assign 1 to first and -1 to second. When doing this, I have to flip all it's previous disliked person as well
     * If it's possible to flip, then I continue otherwise return false.
     * It works but it's a bit complicated to write than the simple solution
     * <p>
     * {@link IsGraphBipartite} related problem
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> graph = buildGraph(N, dislikes);
        int[] labels = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (labels[i] == 0 && !tryAssignColor(labels, i, -1, graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean tryAssignColor(int[] labels, int index, int color, List<List<Integer>> graph) {
        if (labels[index] != 0) { //if index is already assigned, check if the assignment matches the current color
            return labels[index] == color;
        }
        labels[index] = color;
        for (int neighbour : graph.get(index)) {
            if (!tryAssignColor(labels, neighbour, -color, graph)) {
                return false;
            }
        }
        return true;
    }

    private List<List<Integer>> buildGraph(int n, int[][] dislikes) {
        ArrayList<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) { //1 based input
            graph.add(new ArrayList<>());
        }
        for (int[] dislike : dislikes) {
            graph.get(dislike[0]).add(dislike[1]);
            graph.get(dislike[1]).add(dislike[0]);
        }
        return graph;
    }
}
