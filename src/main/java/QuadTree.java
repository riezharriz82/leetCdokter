/**
 * https://leetcode.com/problems/construct-quad-tree/
 * <p>
 * Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.
 * <p>
 * Return the root of the Quad-Tree representing the grid.
 * <p>
 * Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.
 * <p>
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 * <p>
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 * isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 * <p>
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 * <p>
 * Quad-Tree format:
 * <p>
 * The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a path terminator where no node exists below.
 * <p>
 * It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a list [isLeaf, val].
 * <p>
 * If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or val is False we represent it as 0.
 * <p>
 * Input: grid = [[0,1],[1,0]]
 * Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
 * Explanation: The explanation of this example is shown below:
 * Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.
 * <p>
 * Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
 * Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
 * The topLeft, bottomLeft and bottomRight each has the same value.
 * The topRight have different values so we divide it into 4 sub-grids where each has the same value.
 * Explanation is shown in the photo below:
 * <p>
 * Input: grid = [[1,1],[1,1]]
 * Output: [[1,1]]
 * <p>
 * Input: grid = [[0]]
 * Output: [[1,0]]
 * <p>
 * Input: grid = [[1,1,0,0],[1,1,0,0],[0,0,1,1],[0,0,1,1]]
 * Output: [[0,1],[1,1],[1,0],[1,0],[1,1]]
 */
public class QuadTree {
    /**
     * Approach: Use Recursion to recursively divide the grid into smaller grid.
     * Check the return values to see if they are all equal, then return a new lead node, otherwise set the children pointer
     * <p>
     * Only tricky thing is how to divide the grid, initially i divided it by tracking x,y of topleft and bottomright cell of current grid
     * It made the calculations a bit tougher than to simply keep track of topleft + length
     */
    public Node construct(int[][] grid) {
        return recur(0, 0, grid.length, grid);
    }

    private Node recur(int x, int y, int len, int[][] grid) {
        if (len == 1) { //base case
            return new Node(grid[x][y] == 1, true);
        } else {
            Node topLeft = recur(x, y, len / 2, grid);
            Node topRight = recur(x, y + len / 2, len / 2, grid);
            Node bottomLeft = recur(x + len / 2, y, len / 2, grid);
            Node bottomRight = recur(x + len / 2, y + len / 2, len / 2, grid);
            if (topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val
                    && topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf) {
                //if all the subgrids are leaf nodes and have the same value, create a new leaf node
                return new Node(topLeft.val, true);
            } else {
                //different children, set the children pointers
                return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
            }
        }
    }

    private static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
