import common.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeIterativeInorderTraversalTest {
    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(Arrays.asList(2, 1, 3), new BinaryTreeIterativeInorderTraversal().inorderTraversal(root));
    }
}
