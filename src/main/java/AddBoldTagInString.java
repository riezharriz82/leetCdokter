/**
 * https://leetcode.com/problems/add-bold-tag-in-string/ Premium
 * <p>
 * https://leetcode.com/problems/bold-words-in-string/ Premium
 * <p>
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict.
 * If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 * <p>
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 * <p>
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 * <p>
 * Constraints:
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 */
public class AddBoldTagInString {
    Trie root = new Trie();

    /**
     * Approach: Very similar to {@link WordBreak1}
     * This approach has similar worst time complexity as Trie approach but is a bit faster ~8 ms than Trie approach ~35 ms
     * as we have to spend time creating the trie
     * <p>
     * {@link LongestValidParentheses}
     */
    public String addBoldTagSimplified(String s, String[] dict) {
        int n = s.length();
        boolean[] isBold = new boolean[n];
        int largestBoldIndex = -1;
        for (int i = 0; i < n; i++) {
            for (String word : dict) {
                if (s.startsWith(word, i)) { //same approach as trie, for each index i, find the longest valid word starting from ith index
                    largestBoldIndex = Math.max(largestBoldIndex, i + word.length() - 1);
                }
            }
            isBold[i] = largestBoldIndex >= i;
        }
        //merge overlapping intervals
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < n) {
            if (isBold[index]) {
                res.append("<b>");
                while (index < n && isBold[index]) {
                    res.append(s.charAt(index++));
                }
                res.append("</b>");
            } else {
                res.append(s.charAt(index++));
            }
        }
        return res.toString();
    }

    /**
     * Approach: Trie, Need to find the longest valid word starting from each index i in s. Worst case time complexity in finding O(n^2) as we
     * have to repeat this process for each index.
     * Once found, mark those indices as bold. Marking indices will easily allow to merge overlapping intervals as we can have another pass over
     * the array keeping track of longest consecutive bold indices.
     * <p>
     * {@link RemoveComments} {@link WordSearch2} {@link WordBreak1} {@link MergeIntervals}
     */
    public String addBoldTagTrie(String s, String[] dict) {
        for (String word : dict) {
            addWord(word);
        }
        int n = s.length();
        boolean[] isBold = new boolean[n];
        for (int i = 0; i < n; i++) {
            //find the longest valid word starting from ith index by simultaneously traversing trie
            Trie temp = root;
            int index = i, lastValidIndex = -1;
            while (true) {
                if (index == n) {
                    break;
                }
                char c = s.charAt(index);
                temp = temp.nodes[c];
                if (temp == null) { //character not found in trie, prefix is invalid, break
                    break;
                }
                if (temp.isEndOfWord) { //keep track of all intermediate word indices found
                    //e.g given dictionary ["abc", "abcd", "abcdx"] and current word "abcde", if i is at 0, lastValidIndex is 3
                    lastValidIndex = index;
                }
                index++;
            }
            if (lastValidIndex != -1) {
                //flag all intermediate indexes as bold, this extra loop can be avoided if we instead keep track of largest valid index found so far
                //and change isBold[i] = (largestValidIndex >= i)
                for (int j = i; j <= lastValidIndex; j++) {
                    isBold[j] = true;
                }
            }
        }
        //merge overlapping intervals
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < n) {
            if (isBold[index]) {
                res.append("<b>");
                while (index < n && isBold[index]) {
                    res.append(s.charAt(index++));
                }
                res.append("</b>");
            } else {
                res.append(s.charAt(index++));
            }
        }
        return res.toString();
    }

    private void addWord(String word) {
        Trie temp = root;
        for (char c : word.toCharArray()) {
            if (temp.nodes[c] == null) {
                temp.nodes[c] = new Trie();
            }
            temp = temp.nodes[c];
        }
        temp.isEndOfWord = true;
    }

    private static class Trie {
        Trie[] nodes = new Trie[128];
        boolean isEndOfWord;
    }
}
