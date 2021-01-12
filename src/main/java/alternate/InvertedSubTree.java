package alternate;

/**
 * https://binarysearch.io/problems/Inverted-Subtree
 * <p>
 * A tree is defined to be an inversion of another tree if either:
 * <p>
 * Both trees are null
 * Its left and right children are optionally swapped and its left and right subtrees are inversions.
 * Given binary trees source and target, return whether there's some inversion T of source such that it's a subtree of target.
 * That is, whether there's a node in target that is identically same in values and structure as T including all of its descendants.
 * <p>
 * For example, given source and target of
 * <pre>
 *    0         5
 *   / \       / \
 *  1   2     2   0
 *   \           / \
 *    3         2   1
 *                 /
 *                3
 * </pre>
 * Return true.
 */
public class InvertedSubTree {
    /**
     * Very similar to {FlipBinaryTrees} but here the problem statement asks for finding a subtree, so for each node of target
     * we need to check whether it contains source as an inverted subtree or not
     */
    public boolean solve(Tree source, Tree target) {
        return recur(target, source);
    }

    private boolean recur(Tree target, Tree source) {
        if (target == null) {
            return source == null;
        }
        //checks whether subtree under target node contains source tree as an optionally inverted subtree
        //if not, check the left subtree and then right subtree
        return isOptionallyInverted(target, source) || recur(target.left, source) || recur(target.right, source);
    }

    private boolean isOptionallyInverted(Tree target, Tree source) {
        if (target == null && source == null) {
            return true;
        } else if (target == null || source == null) {
            return false;
        } else {
            return source.val == target.val
                    && ((isOptionallyInverted(target.left, source.left) && isOptionallyInverted(target.right, source.right)) //not flipped
                    || (isOptionallyInverted(target.right, source.left) && isOptionallyInverted(target.left, source.right))); //flipped
        }
    }

    public static class Tree {
        int val;
        Tree left;
        Tree right;
    }
}
