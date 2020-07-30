import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * <p>
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * <p>
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * <pre>
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * </pre>
 * return 13.
 */
public class KthSmallestElementInSortedMatrix {
    /**
     * Problem is similar to finding kth smallest element in N sorted lists. Put all the first elements of the list in a heap
     * and poll from the heap k times. Complexity O(klogn)
     */
    public int kthSmallestUsingPriorityQueue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < m; i++) {
            queue.add(new Node(i, 0, matrix[i][0]));
        }
        int popped = 0;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            popped++;
            if (popped == k) {
                return curr.val;
            } else {
                if (curr.col + 1 < n) {
                    queue.add(new Node(curr.row, curr.col + 1, matrix[curr.row][curr.col + 1]));
                }
            }
        }
        return -1;
    }

    private static class Node {
        int row;
        int col;
        int val;

        public Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
}
