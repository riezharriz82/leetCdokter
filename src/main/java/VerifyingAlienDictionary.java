/**
 * https://leetcode.com/problems/verifying-an-alien-dictionary/
 * <p>
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * <p>
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographicaly in this alien language.
 * <p>
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 */
public class VerifyingAlienDictionary {
    /**
     * First define the lexicographical order: At the first point of mismatch between two characters at two strings, if char at first
     * string is smaller than the other, then the first string is lexicographically smaller.
     * <p>
     * In case both the strings matches, then the length of two strings is considered. Length of first string should be smaller to be
     * lexicographically smaller
     * <p>
     * So we start comparing each word with its adjacent word and see whether they are sorted or not.
     * My first approach was to sort the complete array and then check whether the result is equal to the input but it took more time as
     * we can't short-circuit in between
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] rank = new int[26];
        for (int i = 0; i < order.length(); i++) {
            rank[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            //compare the current word and its adjacent word
            if (!compare(words[i], words[i + 1], rank)) {
                return false;
            }
        }
        return true;
    }

    private boolean compare(String word, String nextWord, int[] rank) {
        for (int i = 0; i < Math.min(word.length(), nextWord.length()); i++) { //iterate till the smaller index
            if (word.charAt(i) != nextWord.charAt(i)) { //if any characters mismatch, check their rank
                return rank[word.charAt(i) - 'a'] <= rank[nextWord.charAt(i) - 'a'];
            }
        }
        //if no characters mismatch, check their length
        return word.length() <= nextWord.length();
    }

}
