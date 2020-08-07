import common.TreeNode;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/smallest-string-starting-from-leaf/
 * <p>
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z':
 * a value of 0 represents 'a', a value of 1 represents 'b', and so on.
 * <p>
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 * <p>
 * As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".
 * <p>
 * Input: [2,2,1,null,1,0,null,0]
 * Output: "abc"
 */
public class SmallestStringStartingFromLeaf {
    private String minimum;

    public String smallestFromLeaf(TreeNode root) {
        //using a linkedlist in order to avoid reversing the string, it allows us to directly append to the head of the list
        DFS(root, new LinkedList<>());
        return minimum;
    }

    private void DFS(TreeNode root, LinkedList<Character> curPath) {
        if (root != null) {
            curPath.addFirst((char) ('a' + root.val));
            if (root.left == null && root.right == null) {
                StringBuilder temp = new StringBuilder();
                for (Character character : curPath) {
                    temp.append(character);
                }
                String stringStartingFromLeaf = temp.toString(); //current path
                if (minimum == null) {
                    minimum = stringStartingFromLeaf;
                } else {
                    //compare the current path with the current minimum string
                    minimum = stringStartingFromLeaf.compareTo(minimum) < 0 ? stringStartingFromLeaf : minimum;
                }
            } else {
                DFS(root.left, curPath);
                DFS(root.right, curPath);
            }
            curPath.removeFirst(); //this node is present at the head, so need to remove the head element
        }
    }
}
