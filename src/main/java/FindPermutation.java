/**
 * https://leetcode.com/problems/find-permutation/
 * <p>
 * By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers,
 * 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array,
 * which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1).
 * For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4],
 * which are both illegal constructing special string that can't represent the "DI" secret signature.
 * <p>
 * On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.
 * <p>
 * Input: "I"
 * Output: [1,2]
 * Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I", where the number 1 and 2 construct an increasing relationship.
 * <p>
 * Input: "DI"
 * Output: [2,1,3]
 * Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI",
 * but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
 */
public class FindPermutation {
    /**
     * Approach: Greedy, for a string of length 3, smallest permutation can be 1,2,3,4
     * Now if we see DDII, to satisfy the constraint we have to change 1,2,3, smallest permutation would be to change it to 3,2,1
     * So reverse the permutation covered by D and you will find the smallest such permutation
     * <p>
     * {@link DIStringMatch} {@link SmallestSubsequenceOfDistinctCharacters} related tricky greedy problems
     */
    public int[] findPermutation(String s) {
        int n = s.length();
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = i + 1;
        }
        int index = 0;
        while (index < n) {
            char c = s.charAt(index);
            if (c == 'D') {
                int temp = index;
                while (temp < n && s.charAt(temp) == 'D') { //find the length of D's
                    temp++;
                }
                reverse(result, index, temp); //reverse the values between [index, temp] both inclusive
                index = temp;
            }
            index++;
        }
        return result;
    }

    private void reverse(int[] result, int start, int end) {
        int low = start, high = end;
        while (low < high) {
            int temp = result[low];
            result[low] = result[high];
            result[high] = temp;
            low++;
            high--;
        }
    }
}
