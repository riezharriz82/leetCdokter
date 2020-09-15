/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 * <p>
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 * <p>
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 * <pre>
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * </pre>
 */
public class UniqueBST {
    //Consider n = 5 {1,2,3,4,5} To build BST, you need to iteratively pick each node as root and check the no of ways you can create bst from left
    //subarray * no of ways to create bst from right subarray
    // if you fix 1, right subarray is {2,3,4,5} which is similar to {1,2,3,4} ie. dp[4]
    // similarly if you fix 2, left subarray is {2} i.e dp [1] and right subarray is {3,4,5} ie. dp[3]
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int res = 0;
            for (int j = 0; j < i; j++) { //iteratively pick each index as root
                int left_size = j; //size of left subarray
                int right_size = i - j - 1; //size of right subarray
                int temp = 1;
                temp *= dp[left_size];
                temp *= dp[right_size];
                res += temp;
            }
            dp[i] = res;
        }
        return dp[n];
    }
}
