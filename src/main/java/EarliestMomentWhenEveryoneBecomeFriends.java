import java.util.Arrays;

/**
 * https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/
 * <p>
 * In a social group, there are N people, with unique integer ids from 0 to N-1.
 * <p>
 * We have a list of logs, where each logs[i] = [timestamp, id_A, id_B] contains a non-negative integer timestamp, and the ids of two different people.
 * <p>
 * Each log represents the time in which two different people became friends.  Friendship is symmetric: if A is friends with B, then B is friends with A.
 * <p>
 * Let's say that person A is acquainted with person B if A is friends with B, or A is a friend of someone acquainted with B.
 * <p>
 * Return the earliest time for which every person became acquainted with every other person. Return -1 if there is no such earliest time.
 * <p>
 * Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], N = 6
 * Output: 20190301
 * Explanation:
 * The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
 * The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
 * The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
 * The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
 * The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friend anything happens.
 * The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
 */
public class EarliestMomentWhenEveryoneBecomeFriends {
    /**
     * Approach: Sort by timestamps + perform union find until there is only one component left
     * Need to break when either there is only one uniqueComponent left or the size[root] of last union == N
     * The latter is simpler
     * <p>
     * {@link AccountsMerge} {@link SmallestStringWithSwaps} related union find problems
     */
    public int earliestAcq(int[][] logs, int N) {
        Arrays.sort(logs, ((o1, o2) -> Integer.compare(o1[0], o2[0])));
        int[] parent = new int[N];
        int[] size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int[] log : logs) {
            if (union(log[1], log[2], parent, size) == N) { //new approach, i learnt today
                return log[0];
            }
        }
        return -1;
    }

    private int union(int idx1, int idx2, int[] parent, int[] size) {
        int root1 = find(idx1, parent);
        int root2 = find(idx2, parent);
        if (root1 != root2) {
            if (size[root1] < size[root2]) { //union by size, make sure to update the parent of the smaller sized tree
                parent[root1] = root2;
                size[root2] += size[root1];
                return size[root2];
            } else {
                parent[root2] = root1;
                size[root1] += size[root2];
                return size[root1];
            }
        } else {
            return size[root1];
        }
    }

    private int find(int idx, int[] parent) {
        if (parent[idx] == idx) {
            return idx;
        } else {
            return parent[idx] = find(parent[idx], parent);
        }
    }
}
