import common.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BinaryTreeLevelOrderTraversal2Test {
    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> actual = new BinaryTreeLevelOrderTraversal2().levelOrderBottom(root);
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(15, 7), Arrays.asList(9, 20), Arrays.asList(3));
        Assertions.assertEquals(expected, actual);
    }
}
