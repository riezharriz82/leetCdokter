import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generalized-abbreviation/
 * <p>
 * Write a function to generate the generalized abbreviations of a word.
 * <p>
 * Note: The order of the output does not matter.
 * <p>
 * Input: "word"
 * Output:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
public class GeneralizedAbbreviations {
    /**
     * Approach: Each character has two choices, either be abbreviated or don't. So time complexity is 2^n.
     * Similar to {@link Subsets}
     * In my initial implementation, I did process the input in a loop which caused duplicate words in the result. I have to use set
     * to get unique results but it took ~2000 ms
     * <p>
     * After switching to taking decisions for each character independently, time taken is ~20ms
     * <p>
     * Feeling a bit ashamed, it took me a long time to implement this simple recursion problem; at this stage of my preparation
     */
    public List<String> generateAbbreviations(String word) {
        ArrayList<String> result = new ArrayList<>();
        recur(word, 0, result, "", "");
        return result;
    }

    private void recur(String word, int idx, ArrayList<String> result, String candidate, String abbreviation) {
        if (idx == word.length()) {
            String temp = abbreviation.isEmpty() ? "" : Integer.toString(abbreviation.length());
            result.add(candidate + temp);
        } else {
            recur(word, idx + 1, result, candidate, abbreviation + word.charAt(idx)); //add the current word to the abbreviation
            //don't abbreviate the current word
            //if input is "word", and we are at char d
            //the new candidate would be the concatenation of candidate so far {wo} + abbreviation so far {1} + the current character {d}
            // candidate = wo1d
            String temp = abbreviation.isEmpty() ? "" : Integer.toString(abbreviation.length());
            recur(word, idx + 1, result, candidate + temp + word.charAt(idx), ""); //reset the abbreviation
        }
    }
}
