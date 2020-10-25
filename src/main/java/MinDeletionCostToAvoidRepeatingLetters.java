/**
 * https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/
 * <p>
 * Given a string s and an array of integers cost where cost[i] is the cost of deleting the character i in s.
 * <p>
 * Return the minimum cost of deletions such that there are no two identical letters next to each other.
 * <p>
 * Notice that you will delete the chosen characters at the same time, in other words, after deleting a character, the costs of deleting other characters will not change.
 * <p>
 * Input: s = "abaac", cost = [1,2,3,4,5]
 * Output: 3
 * Explanation: Delete the letter "a" with cost 3 to get "abac" (String without two identical letters next to each other).
 */
public class MinDeletionCostToAvoidRepeatingLetters {
    /**
     * Approach: Trick of the problem was to only keep the character with the maximum cost as we need to keep only one characters in the end.
     * So all the characters with lower cost can be deleted.
     */
    public int minCost(String s, int[] cost) {
        int n = cost.length;
        int result = 0;
        int index = 0;
        while (index < n - 1) {
            int totalSum = cost[index], maximum = cost[index];
            int j;
            for (j = index + 1; j < n; j++) {
                if (s.charAt(j) == s.charAt(index)) {
                    totalSum += cost[j];
                    maximum = Math.max(maximum, cost[j]); //keep track of the maximum cost amongst repeating characters
                } else {
                    break;
                }
            }
            index = j;
            result += (totalSum - maximum); //all others except the maximum needs to be deleted
        }
        return result;
    }
}
