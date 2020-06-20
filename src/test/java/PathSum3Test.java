import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathSum3Test {
    /**
     * 10
     * /  \
     * 5   -3
     * / \    \
     * 3   2   11
     * / \   \
     * 3  -2   1
     */
    @Test
    public void test() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        Assertions.assertEquals(3, new PathSum3().pathSum(root, 8));
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        Assertions.assertEquals(4, new PathSum3().pathSum(root, 1));
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(5);
        Assertions.assertEquals(3, new PathSum3().pathSum(root, 5));
    }
}
