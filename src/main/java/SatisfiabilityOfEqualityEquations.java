import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/satisfiability-of-equality-equations/
 * <p>
 * Given an array equations of strings that represent relationships between variables,
 * each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".
 * Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
 * <p>
 * Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.
 * <p>
 * Input: ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
 * <p>
 * Input: ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 * <p>
 * Input: ["a==b","b==c","a==c"]
 * Output: true
 * <p>
 * Input: ["a==b","b!=c","c==a"]
 * Output: false
 * <p>
 * Input: ["c==c","b==d","x!=z"]
 * Output: true
 * <p>
 * Note:
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] and equations[i][3] are lowercase letters
 * equations[i][1] is either '=' or '!'
 * equations[i][2] is '='
 */
public class SatisfiabilityOfEqualityEquations {
    /**
     * Approach: Use Union Find to find connected components
     * <p>
     * {@link MaxValueOfEquation} {@link FindPositiveIntegerSolutionForGivenEquation} {@link EvaluateDivision} {@link MostStonesRemovedWithSameRowOrSameColumn}
     * {@link AccountsMerge} {@link NumberOfOperationsToMakeNetworkConnected} {@link RedundantConnection} related union find problems
     */
    public boolean equationsPossible(String[] equations) {
        List<String> equals = new ArrayList<>(); //contains all equations that are ==
        List<String> notEquals = new ArrayList<>(); //contains all equations that are !=
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                notEquals.add(equation);
            } else {
                equals.add(equation);
            }
        }
        int[] parents = new int[26]; //constraints mention that the variables are lower case single character
        for (int i = 0; i < 26; i++) {
            parents[i] = i;
        }
        for (String equal : equals) { //perform union on all the connected components
            union(equal.charAt(0) - 'a', equal.charAt(3) - 'a', parents);
        }
        for (String notEqual : notEquals) { //verify whether any of the disconnected components are actually connected or not
            int root1 = find(notEqual.charAt(0) - 'a', parents);
            int root2 = find(notEqual.charAt(3) - 'a', parents);
            if (root1 == root2) {
                return false;
            }
        }
        return true;
    }

    private int find(int index, int[] parents) {
        if (parents[index] == index) {
            return index;
        } else {
            return parents[index] = find(parents[index], parents);
        }
    }

    private void union(int index1, int index2, int[] parents) {
        int root1 = find(index1, parents);
        int root2 = find(index2, parents);
        if (root1 != root2) {
            parents[root1] = root2;
        }
    }
}
