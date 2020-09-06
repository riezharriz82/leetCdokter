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
     * Very similar to {FlipBinaryTrees} but here the problem statement asks for finding a subtree, so we need to check each non null node of target
     */
    public boolean solve(Tree source, Tree target) {
        return iterate(target, source);
    }

    private boolean iterate(Tree target, Tree source) {
        if (target == null) {
            return source == null;
        }
        return check(target, source) || iterate(target.left, source) || iterate(target.right, source);
    }

    private boolean check(Tree target, Tree source) {
        if (target == null && source == null) {
            return true;
        } else if (target == null || source == null) {
            return false;
        } else {
            return source.val == target.val
                    && ((check(target.left, source.left) && check(target.right, source.right)) //not flipped
                    || (check(target.right, source.left) && check(target.left, source.right))); //flipped
        }
    }

    public static class Tree {
        int val;
        Tree left;
        Tree right;
    }
}
