import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {
    /**
     * Approach: Top down recursion with memoization
     * Recursive solution was easy to implement, simply make cut at all places ie. try all valid substrings and check whether it's a palindrome or not.
     * If yes, recur for remaining substring.
     * <p>
     * Since each index will be called multiple times during recursion, if we memoize the results previously computed at any index, we can speed it up.
     * One gotcha is to work on a deep copy of the memoized result, because we are dealing with references and if we work on the original reference, we will
     * propagate the wrong results down the recursion.
     * <p>
     * Time complexity: In worst case if everything is a palindrome, we can make n-1 cuts for a string of length n.
     * Every cut has two options, either get picked or be skipped, so 2^(n-1)
     * <p>
     * {@link DivideChocolates} for similar subarray partitioning related problem
     */
    public List<List<String>> partition(String s) {
        HashMap<Integer, List<List<String>>> memoized = new HashMap<>();
        return recur(s, 0, memoized);
    }

    private List<List<String>> recur(String s, int index, HashMap<Integer, List<List<String>>> memoized) {
        if (index == s.length()) {
            return new LinkedList<>();
        } else if (memoized.containsKey(index)) {
            return memoized.get(index);
        } else {
            List<List<String>> result = new ArrayList<>();
            for (int i = index; i < s.length(); i++) {
                String candidate = s.substring(index, i + 1);
                if (isPalindrome(candidate)) {
                    List<List<String>> remaining = recur(s, i + 1, memoized);
                    List<List<String>> copy = new ArrayList<>();
                    //Gotcha: To create a deep copy, iterate through the nested list instead of just doing, copy = new ArrayList<>(remaining)
                    for (List<String> strings : remaining) {
                        copy.add(new ArrayList<>(strings));
                    }
                    if (copy.isEmpty()) {
                        List<String> temp = new LinkedList<>();
                        temp.add(candidate);
                        result.add(new LinkedList<>(temp));
                    } else { //extend the result by adding the current candidate at the front of the list
                        for (List<String> strings : copy) {
                            strings.add(0, candidate);
                            result.add(strings);
                        }
                    }
                }
            }
            memoized.put(index, result);
            return result;
        }
    }

    private boolean isPalindrome(String candidate) {
        char[] chars = candidate.toCharArray();
        int begin = 0, end = chars.length - 1;
        while (begin < end) {
            if (chars[begin] == chars[end]) {
                begin++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}
