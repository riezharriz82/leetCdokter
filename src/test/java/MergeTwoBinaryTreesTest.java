import common.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeTwoBinaryTreesTest {
    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);

        TreeNode mergedRoot = new MergeTwoBinaryTrees().mergeTrees(root, root2);
        assertEquals(3, mergedRoot.val);
        assertEquals(4, mergedRoot.left.val);
        assertEquals(5, mergedRoot.right.val);
        assertEquals(5, mergedRoot.left.left.val);
        assertEquals(4, mergedRoot.left.right.val);
        assertEquals(7, mergedRoot.right.right.val);
    }
}
