import common.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructMaximumBinaryTreeTest {
    @Test
    public void test() {
        TreeNode root = new ConstructMaximumBinaryTree().constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        assertEquals(6, root.val);
        assertEquals(3, root.left.val);
        assertEquals(5, root.right.val);
        assertEquals(2, root.left.right.val);
        assertEquals(0, root.right.left.val);
        assertEquals(1, root.left.right.right.val);
    }
}
