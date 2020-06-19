import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvertBinaryTreeTest {
    @Test
    public void test() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeNode newRoot = new InvertBinaryTree().invertTree(root);
        assertEquals(4, newRoot.val);
        assertEquals(7, newRoot.left.val);
        assertEquals(2, newRoot.right.val);
        assertEquals(9, newRoot.left.left.val);
        assertEquals(6, newRoot.left.right.val);
        assertEquals(3, newRoot.right.left.val);
        assertEquals(1, newRoot.right.right.val);
    }
}
