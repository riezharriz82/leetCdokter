import java.util.Arrays;

/**
 * https://leetcode.com/problems/short-encoding-of-words/
 * <p>
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 * <p>
 * words.length == indices.length
 * The reference string s ends with the '#' character.
 * For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 * <p>
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 * <p>
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 * <p>
 * Constraints:
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * words[i] consists of only lowercase letters.
 */
public class ShortEncodingOfWords {
    /**
     * Approach: Create Trie by reversing input words which will ensure that if a prefix is already present in a trie, we need not encode it again.
     * e.g. {time, me} -> first insert "emit" and then try to insert "em", since "em" already exists, we need not encode it again.
     * Result is "time#"
     * <p>
     * Care must be taken to process strings in decreasing order of their length as we want the longer string to be inserted first in the trie
     * <p>
     * {@link StreamChecker} {@link ConcatenatedWords}
     */
    public int minimumLengthEncoding(String[] words) {
        Trie root = new Trie();
        Arrays.sort(words, (o1, o2) -> Integer.compare(o2.length(), o1.length())); //sort strings based on decreasing length
        int result = 0;
        for (String word : words) {
            String reversed = reverseWord(word);
            Trie temp = root;
            boolean isWordPresent = true;
            //try to insert the entire word into the trie and see if it already exists or not
            for (char c : reversed.toCharArray()) {
                if (temp.children[c - 'a'] == null) {
                    isWordPresent = false;
                    temp.children[c - 'a'] = new Trie();
                }
                temp = temp.children[c - 'a'];
            }
            if (!isWordPresent) {
                result += word.length(); //add current word in the encoding
                result++; //add 1 for '#'
            }
        }
        return result;
    }

    private String reverseWord(String word) {
        char[] chars = word.toCharArray();
        int begin = 0, end = chars.length - 1;
        while (begin < end) {
            char temp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = temp;
            begin++;
            end--;
        }
        return new String(chars);
    }

    private static class Trie {
        Trie[] children = new Trie[26];
    }
}
