import java.util.*;

/**
 * https://leetcode.com/problems/jump-game-iv/
 * <p>
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * <p>
 * In one step you can jump from index i to index:
 * <p>
 * 1. i + 1 where: i + 1 < n.
 * 2. i - 1 where: i - 1 >= 0.
 * 3. j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 * <p>
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You don't need to jump.
 * <p>
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 * <p>
 * Input: arr = [6,1,9]
 * Output: 2
 * <p>
 * Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
 * Output: 3
 */
public class JumpGame4 {
    /**
     * Approach: As you remember, to find shortest distance among two nodes in a grid, use BFS and to find shortest distance
     * among two types of nodes in a grid, use Multi Source BFS. Here we can use BFS
     * <p>
     * The only important thing to note here is that in case of lots of duplicate nodes in the graph, you will try to visit a lot of
     * redundant edges, this will degrade the run time to n^2, so after visiting a node, we remove all its nodes so that we don't
     * visit it again from duplicate nodes, similar to {@link WordLadder}
     * <p>
     * {@link JumpGame2} {@link JumpGame3} related problems
     */
    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>(); //map of val to list of indices
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], __ -> new ArrayList<>()).add(i);
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int head = queue.remove();
                if (head == n - 1) {
                    return level - 1;
                }
                if (head + 1 < n && !visited[head + 1]) {
                    visited[head + 1] = true;
                    queue.add(head + 1);
                }
                if (head - 1 >= 0 && !visited[head - 1]) {
                    visited[head - 1] = true;
                    queue.add(head - 1);
                }
                for (int indices : map.getOrDefault(arr[head], new ArrayList<>())) {
                    if (indices != head && !visited[indices]) {
                        visited[indices] = true; //remember to mark the new node as visited before adding it to the queue
                        queue.add(indices);
                    }
                }
                map.remove(arr[head]); //important to remove, to avoid visiting nodes again
            }
        }
        return -1;
    }

    /**
     * Approach: Bidirectional BFS, After a lot of procrastination, decided to finally implement Bidirectional BFS
     * TimeComplexity: b^(d/2), where b is the branching factor and d is the distance between source and target
     */
    public int minJumpsBidirectionalBFS(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>(); //map of val to list of indices
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], __ -> new ArrayList<>()).add(i);
        }
        Set<Integer> forwardQueue = new HashSet<>();
        Set<Integer> backwardQueue = new HashSet<>();
        forwardQueue.add(0);
        backwardQueue.add(n - 1);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        visited[n - 1] = true;
        int steps = 0;
        while (!forwardQueue.isEmpty()) {
            //this check ensures that we alternate between forwardQueue and backwardQueue, without this check, we would have to duplicate
            //a lot of code, initially both size would be same, but after first iteration, forwardQueue will be bigger, ensuring that
            //backwardQueue gets visited next time and so we keep on alternating until we are at the middle level
            if (forwardQueue.size() > backwardQueue.size()) {
                Set<Integer> temp = forwardQueue;
                forwardQueue = backwardQueue;
                backwardQueue = temp;
            }
            Set<Integer> nextLevel = new HashSet<>();
            for (int node : forwardQueue) { //for each element in the current queue
                for (Integer neighbour : map.get(arr[node])) { //get the neighbours
                    if (backwardQueue.contains(neighbour)) { //check if neighbour exists in the other queue
                        return steps + 1; //if yes, we have met in the middle
                    }
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        nextLevel.add(neighbour);
                    }
                }
                map.get(arr[node]).clear(); //very important to clear or remove the nodes to avoid trying to visit duplicate nodes
                //need to check whether any neighbour of current node, exist in other queue or not
                if (backwardQueue.contains(node - 1) || backwardQueue.contains(node + 1)) {
                    return steps + 1;
                }
                if (node - 1 >= 0 && !visited[node - 1]) {
                    visited[node - 1] = true;
                    nextLevel.add(node - 1);
                }
                if (node + 1 < n && !visited[node + 1]) {
                    visited[node + 1] = true;
                    nextLevel.add(node + 1);
                }
            }
            forwardQueue = nextLevel; //make the current level point to nodes of next level
            steps++;
        }
        return -1;
    }
}
