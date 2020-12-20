/**
 * https://leetcode.com/problems/number-of-operations-to-make-network-connected/
 * <p>
 * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where connections[i] = [a, b]
 * represents a connection between computers a and b. Any computer can reach any other computer directly or indirectly through the network.
 * <p>
 * Given an initial computer network connections. You can extract certain cables between two directly connected computers,
 * and place them between any pair of disconnected computers to make them directly connected.
 * Return the minimum number of times you need to do this in order to make all the computers connected. If it's not possible, return -1.
 * <p>
 * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
 * Output: 1
 * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
 * <p>
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * Output: 2
 * <p>
 * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * Output: -1
 * Explanation: There are not enough cables.
 * <p>
 * Input: n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= n <= 10^5
 * 1 <= connections.length <= min(n*(n-1)/2, 10^5)
 * connections[i].length == 2
 * 0 <= connections[i][0], connections[i][1] < n
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 * No two computers are connected by more than one cable.
 */
public class NumberOfOperationsToMakeNetworkConnected {
    int uniqueComponents;

    /**
     * Approach: Use Union find to find the no of unique connected components/islands.
     * If there are 5 connected islands, you need 4 cables to connect them.
     * <p>
     * In my initial implementation I counted no of isolated components instead which gave WA.
     * Consider a graph like this 1----2----3      4-----5
     * Here no component is isolated so my original approach returns 0 but correct answer is 1
     * <p>
     * To count no of unique components, initialize total unique components with N and decrement it only when root1 != root2
     * This is the fastest way or call find() for each index and keep track of unique root found. Size of the set is the answer
     * <p>
     * {@link GraphValidTree} {@link MostStonesRemovedWithSameRowOrSameColumn}
     */
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) { //if no of edges < n-1, you can never form a graph
            return -1;
        }
        //if you didn't know this fact (Thankfully I did!), the way to solve this would be to keep track of extra edges
        //ie. if a connection is redundant ie. both nodes already have the same parent, you got an extra edge
        //if extraEdges < requiredEdges return -1
        int[] parent = new int[n];
        int[] size = new int[n];
        uniqueComponents = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int[] connection : connections) {
            union(connection[0], connection[1], parent, size);
        }
        return uniqueComponents - 1;
    }

    private int find(int idx, int[] parent) {
        if (parent[idx] == idx) {
            return idx;
        } else {
            return parent[idx] = find(parent[idx], parent);
        }
    }

    private void union(int idx1, int idx2, int[] parent, int[] size) {
        int root1 = find(idx1, parent);
        int root2 = find(idx2, parent);
        if (root1 != root2) {
            uniqueComponents--; //only decrement when root1 != root2, ie. when actually performing union
            if (size[root1] < size[root2]) {
                parent[root1] = root2;
                size[root2] += size[root1];
            } else {
                parent[root2] = root1;
                size[root1] += size[root2];
            }
        }
    }
}
