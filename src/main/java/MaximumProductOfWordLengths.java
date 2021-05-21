/**
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * <p>
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters.
 * If no such two words exist, return 0.
 * <p>
 * Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 * <p>
 * Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 * <p>
 * Input: words = ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 * <p>
 * Constraints:
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] consists only of lowercase English letters.
 */
public class MaximumProductOfWordLengths {
    /**
     * Approach: Use Bitmasking to maintain a mask of characters present for each word. If two words share no common character,
     * '&' on their bitmask should not have any characters set.
     * Time Complexity: O(n^2 + L) where L is the total length of all words.
     * <p>
     * Have to see the related topics to solve this question, was not able to think of bitmask, was able to think of maintaining reverse lookup table
     * for each character and doing fancy set union and intersection
     * <p>
     * {@link ShortestPathVisitingAllNodes} {@link BeautifulArrangement} {@link MaximumLengthOfAConcatenatedStringWithUniqueCharacters}
     */
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] bitmasks = new int[n]; //index -> bitmask
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int bitmask = getBitMask(word);
            bitmasks[i] = bitmask;
        }
        int maxProduct = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((bitmasks[i] & bitmasks[j]) == 0) { //ith index and jth index share no common character
                    maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
                }
            }
        }
        return maxProduct;
    }

    private int getBitMask(String word) {
        int bitmask = 0;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            bitmask |= (1 << index);
        }
        return bitmask;
    }
}
