package alternate;

/**
 * https://binarysearch.io/problems/Friend-Groups
 * <p>
 * You are given a two-dimensional friends list, where friends[i] is a list of people i is friends with. Friendships are two-way.
 * Everyone's a friend with themselves and two people are in a friend group as long as there is some path of mutual friends connecting them.
 * <p>
 * Return the total number of friend groups.
 * <pre>
 * friends = [
 *     [0, 1],
 *     [1, 0, 2],
 *     [2, 1],
 *     [3, 4],
 *     [4, 3],
 *     [5]
 * ]
 * Output 3
 * </pre>
 * The three friend groups are
 * <p>
 * [0, 1, 2]
 * [3, 4]
 * [5]
 */
public class FriendGroups {

    /**
     * {@see NumberOfIslands} for similar problem.
     * For each friend, check if it's not visited and then perform DFS for each friend and mark all the friends as visited.
     * TODO: Solve using Union find
     * https://leetcode.com/problems/friend-circles/discuss/101354/C%2B%2B-Clean-Code-DFSorUnionFind
     */
    public int solve(int[][] friends) {
        boolean[] visited = new boolean[friends.length];
        int result = 0;
        for (int i = 0; i < friends.length; i++) {
            if (!visited[i]) {
                result++; //new group found
                DFS(i, friends, visited);
            }
        }
        return result;
    }

    private void DFS(int index, int[][] graph, boolean[] visited) {
        if (!visited[index]) {
            visited[index] = true;
            for (int friend : graph[index]) {
                DFS(friend, graph, visited);
            }
        }
    }
}
