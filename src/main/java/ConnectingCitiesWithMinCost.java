import java.util.Arrays;

/**
 * https://leetcode.com/problems/connecting-cities-with-minimum-cost/
 * <p>
 * There are N cities numbered from 1 to N.
 * <p>
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.
 * (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)
 * <p>
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.
 * The cost is the sum of the connection costs used. If the task is impossible, return -1.
 * <p>
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 * <p>
 * Input: N = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation:
 * There is no way to connect all cities even if all edges are used.
 */
public class ConnectingCitiesWithMinCost {
    /**
     * Approach: Create Minimum Spanning Tree to connect all the cities with min cost using Kruskal
     * <p>
     * {@link OptimizeWaterDistributionInAVillage} {@link MinimumCostToConnectSticks} {@link PathWithMaximumMinimumValue} related problems
     */
    public int minimumCost(int N, int[][] connections) {
        int[] parent = new int[N + 1]; //nodes are 1 based
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        int uniqueComponents = N;
        Arrays.sort(connections, (o1, o2) -> Integer.compare(o1[2], o2[2])); //sorting is required
        int totalCost = 0;
        for (int[] connection : connections) {
            if (union(connection[0], connection[1], parent)) {
                uniqueComponents--; //remember to only decrement when union was performed
                totalCost += connection[2]; //increment the total cost
            }
        }
        return uniqueComponents == 1 ? totalCost : -1;
    }

    private int find(int index, int[] parent) {
        if (parent[index] == index) {
            return index;
        } else {
            return parent[index] = find(parent[index], parent);
        }
    }

    private boolean union(int idx1, int idx2, int[] parent) {
        int root1 = find(idx1, parent);
        int root2 = find(idx2, parent);
        if (root1 != root2) {
            parent[root1] = root2;
            return true;
        } else {
            return false;
        }
    }
}
