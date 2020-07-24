import common.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RootToLeafPathSumTest {
    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertFalse(new RootToLeafPathSum().hasPathSum(root, 1));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(3);
        assertTrue(new RootToLeafPathSum().hasPathSum(root, 9));
    }
}
