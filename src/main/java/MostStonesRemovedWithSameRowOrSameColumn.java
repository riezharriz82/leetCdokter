import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 * <p>
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 * <p>
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 * <p>
 * What is the largest possible number of moves we can make?
 * <p>
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * <p>
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * <p>
 * Input: stones = [[0,0]]
 * Output: 0
 * <p>
 * Note:
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 */
public class MostStonesRemovedWithSameRowOrSameColumn {
    /**
     * Approach: Find the no of islands, in each island we have to keep only one of the stone. Result would be total stones - no of islands
     * <p>
     * Initially I went ahead with recursion in which I found all the conflicting stones and tried to either delete current stone or delete
     * remaining conflicting stones, but it gave WA
     * The thing I did wrong here was I missed the point in which I could think of greedily keeping only one stone in a conflicting group
     * and delete others. This way we would always perform the largest possible no of moves. As with all greedy solutions, its difficult
     * to prove this.
     * <p>
     * {@link SmallestStringWithSwaps} related tricky union find problem
     */
    public int removeStonesDFS(int[][] stones) {
        int n = stones.length;
        boolean[] visited = new boolean[n];
        int noOfIslands = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                noOfIslands++;
                visited[i] = true;
                DFS(visited, stones, stones[i]);
            }
        }
        return n - noOfIslands;
    }

    private void DFS(boolean[] visited, int[][] stones, int[] stone) {
        for (int i = 0; i < stones.length; i++) {
            if (!visited[i] && (stones[i][0] == stone[0] || stones[i][1] == stone[1])) {
                visited[i] = true;
                DFS(visited, stones, stones[i]);
                //this recursive part is important e.g if initial stone was [0,3] and current stone is [5,3] we need to delete
                //all stones placed on x=5 as well because they are transitively part of the same group, hence the recursion
            }
        }
    }

    /**
     * Approach: Union find
     * It's a bit difficult to visualize as we are performing union find on 2D points
     * Important to connect row and column of the same index as part of the same component, this will help to connect new stones
     * which share either the same row or the same column
     * [0,5] mapped to 0
     * [3,5] 3 is unmapped but 5 is mapped to 0, so 3 will be mapped to 0 as well
     * [0,4] 0 is mapped to 0 and 4 is unmapped so 4 will be mapped to 0 as well
     * 0,3,4,5 all will be mapped to same component 0
     */
    public int removeStonesUnionFind(int[][] stones) {
        int[] parent = new int[20000]; //max value of stone[i] is 10000 so x is < 10000, consider y to start from 10000
        for (int i = 0; i < 20000; i++) {
            parent[i] = i;
        }
        for (int[] stone : stones) {
            union(stone[0], 10000 + stone[1], parent); //make current row and current col part of the same component
        }
        Set<Integer> uniqueComponents = new HashSet<>();
        for (int[] stone : stones) {
            uniqueComponents.add(find(stone[0], parent)); //find component id of each stone
        }
        return stones.length - uniqueComponents.size();
    }

    private int find(int idx, int[] parent) {
        if (parent[idx] == idx) {
            return idx;
        } else {
            return parent[idx] = find(parent[idx], parent);
        }
    }

    private void union(int idx1, int idx2, int[] parent) {
        int root1 = find(idx1, parent);
        int root2 = find(idx2, parent);
        if (root1 != root2) {
            parent[root1] = root2;
        }
    }
}