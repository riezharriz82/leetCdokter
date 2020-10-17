/**
 * https://leetcode.com/problems/jump-game-iii/
 * <p>
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 */
public class JumpGame3 {
    /**
     * Approach: Always initially try with brute force, initially I thought that I would need some fancy DP/greedy to optimize the runtime
     * but i thought let's start with brute force.
     * So I used recursion to jump left and right, only trick is to not to jump in circles which can be avoided using visited array, similar to DFS
     */
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return DFS(arr, start, visited);
    }

    private boolean DFS(int[] arr, int index, boolean[] visited) {
        if (index < 0 || index >= arr.length || visited[index]) {
            return false;
        }
        if (arr[index] == 0) {
            return true;
        }
        visited[index] = true;
        return DFS(arr, index + arr[index], visited) || DFS(arr, index - arr[index], visited);
    }
}
