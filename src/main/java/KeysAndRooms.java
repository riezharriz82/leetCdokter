import java.util.List;

/**
 * https://leetcode.com/problems/keys-and-rooms/
 * <p>
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 * <p>
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.
 * A key rooms[i][j] = v opens the room with number v.
 * <p>
 * Initially, all the rooms start locked (except for room 0).
 * <p>
 * You can walk back and forth between rooms freely.
 * <p>
 * Return true if and only if you can enter every room.
 * <p>
 * Input: [[1],[2],[3],[]]
 * Output: true
 * Explanation:
 * We start in room 0, and pick up key 1.
 * We then go to room 1, and pick up key 2.
 * We then go to room 2, and pick up key 3.
 * We then go to room 3.  Since we were able to go to every room, we return true.
 * <p>
 * Input: [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can't enter the room with number 2.
 * <p>
 * Note:
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * The number of keys in all rooms combined is at most 3000.
 */
public class KeysAndRooms {
    int totalComponents;

    /**
     * Approach: Use union find to merge rooms that are not visited. Although union find is an overkill for this solution; as simple
     * DFS would suffice here, I wanted to practice Union find, hence I coded it
     * <p>
     * {@link MostStonesRemovedWithSameRowOrSameColumn} {@link SentenceSimilarity2}
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        int[] parent = new int[n + 1]; //n+1, to avoid visited array, and to use nth room as the marker room for performing union with
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        totalComponents = n + 1;
        recur(parent, rooms, 0, n);
        return totalComponents == 1;
    }

    private void recur(int[] parent, List<List<Integer>> rooms, int index, int markerIndex) {
        if (union(index, markerIndex, parent)) { //always try to perform union with the nth room, avoids visited array
            totalComponents--; //as learnt earlier, decrement total components only when union is performed
            for (int key : rooms.get(index)) {
                recur(parent, rooms, key, markerIndex);
            }
        }
        //if union returns false, this indicates that the room is already visited, nifty trick i thought of on the fly :P
    }

    private int find(int index, int[] parent) {
        if (parent[index] == index) {
            return index;
        } else {
            return parent[index] = find(parent[index], parent);
        }
    }

    private boolean union(int index1, int index2, int[] parent) {
        int root1 = find(index1, parent);
        int root2 = find(index2, parent);
        if (root1 == root2) {
            return false;
        } else {
            parent[root1] = root2;
            return true;
        }
    }
}
