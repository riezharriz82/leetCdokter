package alternate;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://www.interviewbit.com/problems/diagonal-traversal/
 * <p>
 * Given a Binary Tree A containing N nodes, return all diagonal elements in a binary tree belonging to same line.
 * Input 1:
 * <pre>
 *             1
 *           /   \
 *          4      2
 *         / \      \
 *        8   5      3
 *           / \    /
 *          9   7  6
 * </pre>
 * 1) Diagonal 1 contains [1, 2, 3]
 * 2) Diagonal 2 contains [4, 5, 7, 6]
 * 3) Diagonal 3 contains [8, 9]
 * So concatenate all the array as return it as a single one.
 * Final output: [1, 2, 3, 4, 5, 7, 6, 8, 9]
 */
public class DiagonalTraversalOfBinaryTree {
    /**
     * Approach: All elements in the diagonal have the same key, so when we go right, pass in the same key as current node
     * When we go left, either increment/decrement the key.
     * <p>
     * Store values of nodes with the same key, they are the nodes that are present in the same diagonal
     * Finally concatenate them in one list and return it.
     */
    public ArrayList<Integer> solve(TreeNode A) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        DFS(A, map, 0);
        ArrayList<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            result.addAll(entry.getValue());
        }
        return result;
    }

    private void DFS(TreeNode node, Map<Integer, List<Integer>> map, int key) {
        if (node == null) {
            return;
        }
        map.computeIfAbsent(key, __ -> new ArrayList<>()).add(node.val);
        DFS(node.left, map, key + 1); //change the key when recurring left to denote start of a new diagonal
        DFS(node.right, map, key); //pass in the same key when recurring right to denote the next node is part of the current diagonal
    }
}
