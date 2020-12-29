import common.TreeNode;

/**
 * https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/
 * <p>
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one
 * permutation of the node values in the path is a palindrome.
 * <p>
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 * <p>
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes:
 * the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths
 * since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * <p>
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes:
 * the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1]
 * can be rearranged in [1,2,1] (palindrome).
 * <p>
 * Input: root = [9]
 * Output: 1
 */
public class PseudoPalindromicPathsInBinaryTree {
    /**
     * Approach: Remember that binary tree questions can be solved either by passing information from the parent or getting information
     * from the child, here we need to pass info from the parent. We need to know the frequency count of all elements present in a root
     * to leaf path. We can do that by keeping a int[] map for 9 digits.
     * <p>
     * At the leaf, we need to check that there should be at most 1 odd digit present, for the path to be transformed to palindrome
     * <p>
     * Instead of int[], we could leverage bitmasks to check whether there is only one set bit in the bitmask. We flip the bit position
     * of the current digit by using xor. This ensures that 1 bit indicates odd frequencies. Using n&(n-1)==0 we check that
     * there is only one set bit
     * <p>
     * {@link DeleteNodesAndReturnForest} {@link DistributeCoinsInBinaryTree} {@link PalindromePermutation}
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        int[] cnt = new int[10]; //store frequency of digits 1-9
        return recur(root, cnt);
    }

    private int recur(TreeNode root, int[] cnt) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            cnt[root.val] += 1; //increment the frequency of current digit
            int total = 0, odd = 0;
            for (int i = 1; i < 10; i++) {
                total += cnt[i];
                if (cnt[i] % 2 != 0) {
                    odd++;
                }
            }
            cnt[root.val] -= 1; //backtrack
            if (total % 2 == 0 && odd == 0) { //if even no of digits, no odd digits should be present
                return 1;
            } else if (total % 2 == 1 && odd == 1) { //if odd no of digits, only one odd digit should be present
                return 1;
            } else {
                return 0;
            }
        } else {
            cnt[root.val] += 1;
            int left = recur(root.left, cnt);
            int right = recur(root.right, cnt);
            cnt[root.val] -= 1; //backtrack
            return left + right;
        }
    }
}
