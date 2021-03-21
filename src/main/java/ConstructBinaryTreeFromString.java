import common.TreeNode;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-string/
 * <p>
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * <p>
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 * <p>
 * You always start to construct the left child node of the parent first if it exists.
 * <p>
 * Input: s = "4(2(3)(1))(6(5))"
 * Output: [4,2,6,3,1,5]
 * <p>
 * Input: s = "4(2(3)(1))(6(5)(7))"
 * Output: [4,2,6,3,1,5,7]
 * <p>
 * Input: s = "-4(2(3)(1))(6(5)(7))"
 * Output: [-4,2,6,3,1,5,7]
 * <p>
 * Constraints:
 * 0 <= s.length <= 3 * 104
 * s consists of digits, '(', ')', and '-' only.
 */
public class ConstructBinaryTreeFromString {
    /**
     * Approach: Use stack to simulate tree creation. Whenever nested parenthesis is involved, stacks must always be used.
     * Similar to {@link DecodeString} create a empty node when ( is encountered.
     * Upon seeing ) try linking the current node with the parent node by either attaching it to left pointer or right pointer.
     * <p>
     * Care must be taken to properly check whether the current number is valid or not e.g ))
     * <p>
     * {@link SerializeAndDeserializeBinaryTree} {@link SerializeAndDeserializeBST} {@link DecodeString} {@link NumberOfAtoms} {@link BasicCalculator2}
     */
    public TreeNode str2tree(String s) {
        if (s.isEmpty()) {
            return null;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(new TreeNode()); //init with an empty node whose value will be modified
        int multiplier = 1;
        int number = 0;
        boolean isValidNumber = false; //this is to ensure that the current number is valid
        for (char c : s.toCharArray()) {
            if (c == '-') {
                multiplier *= -1;
            } else if (Character.isDigit(c)) {
                isValidNumber = true;
                number = (number * 10) + (c - '0');
            } else if (c == '(') {
                if (isValidNumber) { //to handle cases like )(
                    stack.peek().val = number * multiplier;
                    isValidNumber = false;
                }
                stack.push(new TreeNode()); //push a empty node similar to other nested string problems
                number = 0; //reset
                multiplier = 1;
            } else if (c == ')') {
                if (isValidNumber) { //to handle cases like ))
                    stack.peek().val = number * multiplier;
                    isValidNumber = false;
                }
                number = 0; //reset
                multiplier = 1;
                TreeNode curNode = stack.pop();
                TreeNode lastNode = stack.peek();
                //link the curNode to the lastNode by either attaching it to left or right pointer
                if (lastNode.left == null) { //try assigning left first if available, as per problem statement
                    lastNode.left = curNode;
                } else {
                    lastNode.right = curNode;
                }
            }
        }
        //edge case to handle cases like 467
        if (isValidNumber) {
            stack.peek().val = number * multiplier;
        }
        return stack.pop();
    }
}
