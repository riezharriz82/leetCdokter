/**
 * https://leetcode.com/problems/fruit-into-baskets/
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 * <p>
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 * <p>
 * You start at any tree of your choice, then repeatedly perform the following steps:
 * <p>
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 * <p>
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 * <p>
 * What is the total amount of fruit you can collect with this procedure?
 * <p>
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * <p>
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 */
public class FruitIntoBaskets {
    /**
     * Approach: Hidden Sliding window logic, the problem is asking for the longest subarray with at most 2 distinct type of fruits
     * <p>
     * Google freaking loveeess sliding window problems
     * <p>
     * {@link MaximumPointsToObtainFromCards} similar hidden sliding window logic
     */
    public int totalFruit(int[] tree) {
        int[] cnt = new int[tree.length];
        int begin = 0, end = 0, distinctFruits = 0;
        int result = 0;
        while (end < tree.length) {
            int last = tree[end];
            if (cnt[last] == 0) {
                distinctFruits++; //new type of fruit found
            }
            cnt[last]++;
            while (distinctFruits > 2) { //more than 2 distinct fruits in window, need to shrink the window from the left
                int first = tree[begin];
                if (cnt[first] == 1) {
                    distinctFruits--;
                }
                cnt[first]--;
                begin++;
            }
            result = Math.max(result, end - begin + 1); //keep track of the longest valid window found so far
            end++;
        }
        return result;
    }
}
