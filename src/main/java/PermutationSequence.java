import java.util.ArrayList;

/**
 * https://leetcode.com/problems/permutation-sequence/
 * <p>
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * <p>
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * <p>
 * Input: n = 3, k = 3
 * Output: "213"
 * <p>
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class PermutationSequence {
    int currentIndex;

    /**
     * Approach: Greedy, Fix each digit of the kth sequence one by one using maths. Big brain time
     * For {1,2,3,4}, 1 can lead 3! permutations, 2 can lead the next 3! permutations, 3 can lead next 3! permutations and so on.
     * So if we require the 14th permutation (0 based indexing), we can directly fix 3 as the first digit.
     * <p>
     * Now we have eliminated 12 permutations and need the 2nd permutation
     * Digits left {1,2,4}, 1 can lead 2! permutations, 2 can lead the next 2! permutations.
     * Since we need the 2nd permutation, we can fix 1 as the second digit
     * <p>
     * Now we have eliminated additional 0 permutations, as we chose the first bucket itself.
     * Now we have {2,4}, 2 can lead 1 permutations, 4 can lead next 1 permutation
     * Since we need the 2nd permutation we can fix 4 as the third digit
     * <p>
     * Now we are left with 2, so result would be 3,1,4,2
     */
    public String getPermutationOptimized(int n, int k) {
        int[] fact = new int[n + 1]; //store the factorial, 1!, 2!, 3!....
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = i * fact[i - 1];
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        k = k - 1; //because input k is 1 based
        String result = "";
        for (int i = 1; i <= n; i++) { //need to repeat it for n digits
            int elementsRemaining = list.size();
            int bucketIndex = k / fact[elementsRemaining - 1]; //find the index of the bucket we need to pick
            result += list.get(bucketIndex);
            list.remove(bucketIndex); //remove the chosen element so that it does not get picked again
            k -= (bucketIndex * fact[elementsRemaining - 1]); //reduce k by elements that are generated by lower buckets
        }
        return result;
    }

    /**
     * Approach: Generate all the permutations till the kth required sequence, once found, return it
     * Similar to {@link Permutations}
     */
    public String getPermutationBruteForce(int n, int k) {
        boolean[] visited = new boolean[n + 1];
        StringBuilder sb = new StringBuilder();
        return recur(visited, sb, k, n);
    }

    private String recur(boolean[] visited, StringBuilder sb, int k, int n) {
        if (sb.length() == n) {
            currentIndex++;
            if (currentIndex == k) {
                return sb.toString();
            } else {
                return "";
            }
        } else {
            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    sb.append(i);
                    String result = recur(visited, sb, k, n);
                    if (!result.isEmpty()) {
                        return result;
                    }
                    sb.setLength(sb.length() - 1);
                    visited[i] = false;
                }
            }
            return "";
        }
    }
}
