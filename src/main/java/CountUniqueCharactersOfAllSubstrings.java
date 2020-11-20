import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
 * <p>
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s,
 * for example if s = "LEETCODE" then "L", "T","C","O","D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * <p>
 * On this problem given a string s we need to return the sum of countUniqueChars(t) where t is a substring of s.
 * Notice that some substrings can be repeated so on this case you have to count the repeated ones too.
 * <p>
 * Since the answer can be very large, return the answer modulo 10 ^ 9 + 7.
 * <p>
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Evey substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * <p>
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 * <p>
 * Input: s = "LEETCODE"
 * Output: 92
 */
public class CountUniqueCharactersOfAllSubstrings {
    /**
     * Approach: Find the contribution of each character individually and repeat it for all characters to get the result
     * <p>
     * Consider EAECE, for A at index 1, contribution is 2 * 4 = (i + 1) * (n - i)
     * but for E at index 2, contribution is 2 * 2 = (2 - 0) * (4 - 2)
     * E at middle is bounded by the previous and next occurrence of E
     * So if we keep track of all the occurrences of a character we can find the contribution by finding the next greater and lower index
     * This is an nlogn solution
     * <p>
     * Can be easily optimized to linear time by maintaining four arrays L[i] and R[i] and LastL[i] and LastR[i]
     * We can directly update the L[i] by diffing i with LastL[i]
     * https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/224001/C%2B%2B-Solution-8ms-by-%22Contribution%22
     * <p>
     * I would not have been able to solve this question if lee215 would not have explained the logic for finding no of subarrays an
     * element is part of in {@link SumOfAllOddLengthSubarrays}
     * <p>
     * {@link SumOfSubarrayMinimums} similar problem
     */
    public int uniqueLetterString(String s) {
        List<TreeSet<Integer>> occurrences = new ArrayList<>(); //store index of occurrences of each character
        for (int i = 0; i < 26; i++) {
            occurrences.add(new TreeSet<>());
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            occurrences.get(c - 'A').add(i);
        }
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int leftContributions, rightContributions;
            Integer higherIndex = occurrences.get(c - 'A').higher(i);
            Integer lowerIndex = occurrences.get(c - 'A').lower(i);
            if (lowerIndex == null) {
                //if the character does not occur to the left
                leftContributions = i + 1;
            } else {
                //get the substrings in between for which the current character can contribute
                //For example E at index 2 can contribute for AE, E, EC, AEC
                leftContributions = i - lowerIndex;
            }
            if (higherIndex == null) {
                rightContributions = s.length() - i;
            } else {
                rightContributions = higherIndex - i;
            }
            result += (leftContributions * rightContributions);
        }
        return result;
    }
}
