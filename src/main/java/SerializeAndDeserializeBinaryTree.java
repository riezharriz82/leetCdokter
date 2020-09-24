import common.TreeNode;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */
public class SerializeAndDeserializeBinaryTree {
    /**
     * Encodes a tree to a single string. root (size of left subtree) left subtree (size of right subtree) right subtree
     * A simpler solution would be to store like root, root.left.val, root.left.left.val, root.left.right.val, root.right.val, null, null
     * While decoding we could simply split the string by , and create a queue from the resultant array
     * Queue will help to recursively fix the root element, then recurse for left & right respectively assigning the head of the queue
     * to the root.
     * <p>
     * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/74253/Easy-to-understand-Java-Solution
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "N";
        } else {
            String left = serialize(root.left);
            String right = serialize(root.right);
            String leftSerialized = "N", rightSerialized = "N";
            if (!left.equals("N")) {
                leftSerialized = "(" + left.length() + ")" + left;
            }
            if (!right.equals("N")) {
                rightSerialized = "(" + right.length() + ")" + right;
            }
            return root.val + leftSerialized + rightSerialized;
        }
    }

    // Decodes your encoded data to tree.
    // TIP: tree has negative elements too
    // first parse the root node, then recursively solve for left subtree by leveraging the length of left subtree
    // similarly recursively solve for right subtree by leveraging length of right subtree
    public TreeNode deserialize(String data) {
        if (data.equals("N")) {
            return null;
        } else {
            int index = 0;
            int val = 0;
            boolean isNegative = false;
            if (data.charAt(index) == '-') {
                isNegative = true;
                index++;
            }
            while (index < data.length() && Character.isDigit(data.charAt(index))) {
                val = 10 * val + (data.charAt(index) - '0');
                index++;
            }
            if (isNegative) {
                val *= -1; //negate if negative value
            }
            TreeNode root = new TreeNode(val);
            if (data.charAt(index) == 'N') {
                root.left = null;
                index++;
            } else {
                index++; // (
                int leftSubtreeLength = 0;
                while (index < data.length() && Character.isDigit(data.charAt(index))) {
                    leftSubtreeLength = 10 * leftSubtreeLength + (data.charAt(index) - '0');
                    index++;
                }
                index++; // )
                root.left = deserialize(data.substring(index, index + leftSubtreeLength));
                index += leftSubtreeLength;
            }
            //same code as left subtree but repeated for right subtree
            if (data.charAt(index) == 'N') {
                root.right = null;
            } else {
                index++; // (
                int rightSubtreeLength = 0;
                while (index < data.length() && Character.isDigit(data.charAt(index))) {
                    rightSubtreeLength = 10 * rightSubtreeLength + (data.charAt(index) - '0');
                    index++;
                }
                index++; // )
                root.right = deserialize(data.substring(index, index + rightSubtreeLength));
            }
            return root;
        }
    }
}
