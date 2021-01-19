/**
 * https://leetcode.com/problems/count-sorted-vowel-strings/
 * <p>
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 * <p>
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 * <p>
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 * <p>
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 * <p>
 * Input: n = 33
 * Output: 66045
 * <p>
 * Constraints:
 * 1 <= n <= 50
 */
public class CountSortedVowelStrings {
    /**
     * Approach: Digit DP but instead of an upper bound per digit, we have lower bound, which makes it easier to solve
     * Instead of visualizing placing a,e,i,o,u try to place 1,2,3,4,5 instead
     * <p>
     * Gets AC without memoization too because of low constraints
     * <p>
     * {@link NumbersAtMostNGivenDigitSet} {@link KnightDialer} related problem
     */
    public int countVowelStrings(int n) {
        int[][] memo = new int[n + 1][6];
        return recur(n, 1, memo); //start placing from 1
    }

    private int recur(int n, int lowerBound, int[][] memo) {
        if (n == 0) {
            return 1; //if all digits have been placed
        } else if (memo[n][lowerBound] != 0) {
            return memo[n][lowerBound];
        } else {
            int res = 0;
            for (int i = lowerBound; i <= 5; i++) { //do not place any digit < lowerBound and try to place till 5
                res += recur(n - 1, i, memo);
            }
            return memo[n][lowerBound] = res;
        }
    }
}
