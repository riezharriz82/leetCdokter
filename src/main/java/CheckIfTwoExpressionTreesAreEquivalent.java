/**
 * https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent/
 * <p>
 * A binary expression tree is a kind of binary tree used to represent arithmetic expressions.
 * Each node of a binary expression tree has either zero or two children.
 * Leaf nodes (nodes with 0 children) correspond to operands (variables), and internal nodes (nodes with two children) correspond to the operators.
 * In this problem, we only consider the '+' operator (i.e. addition).
 * <p>
 * You are given the roots of two binary expression trees, root1 and root2. Return true if the two binary expression trees are equivalent. Otherwise, return false.
 * <p>
 * Two binary expression trees are equivalent if they evaluate to the same value regardless of what the variables are set to.
 * <p>
 * Follow up: What will you change in your solution if the tree also supports the '-' operator (i.e. subtraction)?
 * <p>
 * Input: root1 = [x], root2 = [x]
 * Output: true
 * <p>
 * Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,b,c,a]
 * Output: true
 * Explaination: a + (b + c) == (b + c) + a
 * <p>
 * Input: root1 = [+,a,+,null,null,b,c], root2 = [+,+,b,d,a]
 * Output: false
 * Explaination: a + (b + c) != (b + d) + a
 */
public class CheckIfTwoExpressionTreesAreEquivalent {
    /**
     * Approach: Simply count the no of variables in each tree, since only operator is + we need not worry about the location at which the
     * variable is present, instead just worry about the count
     * <p>
     * Got down the wrong path by comparing the problem to see if the provided trees are flip equivalent or not
     * {@link FlipBinaryTrees} Didn't notice that this strategy won't work for reordering of variable at mode that 1 level
     * This was an easy problem, should have been able to solve it on my own
     */
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] cnt = new int[26];
        DFS(root1, true, cnt);
        DFS(root2, false, cnt);
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private void DFS(Node root, boolean doIncrement, int[] cnt) {
        if (root != null) {
            if (Character.isLetter(root.val)) {
                if (doIncrement) {
                    cnt[root.val - 'a']++;
                } else {
                    cnt[root.val - 'a']--;
                }
            }
            DFS(root.left, doIncrement, cnt);
            DFS(root.right, doIncrement, cnt);
        }
    }

    private static class Node {
        char val;
        Node left;
        Node right;

        Node() {
            this.val = ' ';
        }

        Node(char val) {
            this.val = val;
        }

        Node(char val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
