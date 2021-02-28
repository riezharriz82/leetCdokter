import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/number-of-distinct-substrings-in-a-string/
 * <p>
 * Given a string s, return the number of distinct substrings of s.
 * <p>
 * Input: s = "aabbaba"
 * Output: 21
 * <p>
 * Input: s = "abcdefg"
 * Output: 28
 * <p>
 * Constraints:
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 * <p>
 * Follow up: Can you solve this problem in O(n) time complexity?
 */
public class NumberOfDistinctSubstringsInAString {

    /**
     * Approach: Use two pointer approach to iteratively increment trie node as well as string index
     * <p>
     * This optimization is very crucial to solve it in an optimal manner. Very similar to {@link WordSearch2}
     * It allows us to find whether current words exist in trie without actually generating the whole string.
     * Generating the whole string would take o(n) operation.
     * Imagine it to be comparing strings across same height, one length string are compared at the root, 2 length from 1 level down from root
     * <p>
     * Time ~49 ms, Memory 78 MB
     * <p>
     * {@link WordSearch2} {@link DesignSearchAutocompleteSystem} {@link LongestCommonSubarray}
     */
    public int countDistinctTrie(String s) {
        Node root = new Node();
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            Node temp = root;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (temp.children[c - 'a'] == null) {
                    temp.children[c - 'a'] = new Node();
                    res++; //found a unique occurrence
                }
                temp = temp.children[c - 'a'];
            }
        }
        return res;
    }

    /**
     * Approach: Generate all substrings in an optimized manner using StringBuilder and maintain a set to count distinct substrings
     * <p>
     * Time ~591 ms, Memory 112 MB
     */
    public int countDistinctBruteForce(String s) {
        Set<String> substrings = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; j++) {
                sb.append(s.charAt(j));
                substrings.add(sb.toString()); //this is the expensive operation, on prima facie the time complexity is n^2 but this make it n^3
                //because it performs an array copy operation for each substring
            }
        }
        return substrings.size();
    }

    private static class Node {
        Node[] children = new Node[26];
    }
}
