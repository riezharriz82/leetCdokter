import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/brace-expansion/ Premium
 * <p>
 * A string S represents a list of words.
 * <p>
 * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.
 * If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
 * <p>
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 * <p>
 * Return all words that can be formed in this manner, in lexicographical order.
 * <p>
 * Input: "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * <p>
 * Input: "abcd"
 * Output: ["abcd"]
 * <p>
 * 1 <= S.length <= 50
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
public class BraceExpansion {
    /**
     * Approach: Store all the possible options for each digit in a list of list.
     * Then use backtracking to generate all combinations
     * <p>
     * {@link LetterCombinationsOfPhoneNumber} similar problem
     */
    public String[] expand(String S) {
        List<List<Character>> options = new ArrayList<>();
        List<Character> currentOption = new ArrayList<>();
        boolean isMultipleOptions = false;
        for (char c : S.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                if (isMultipleOptions) { //if inside { block, add to the currentOptions
                    currentOption.add(c);
                } else { //otherwise add directly to the result, because their are no more options
                    options.add(Arrays.asList(c));
                }
            } else if (c == '{') {
                isMultipleOptions = true;
            } else if (c == '}') {
                options.add(new ArrayList<>(currentOption));
                //reset the flags
                currentOption.clear();
                isMultipleOptions = false;
            }
        }
        for (List<Character> option : options) {
            Collections.sort(option);
        }
        List<String> result = new ArrayList<>();
        recur(result, options, new ArrayList<>(), 0);
        return result.toArray(new String[result.size()]);
    }

    //backtracking, try each possible character present for an index and recur for the next index
    private void recur(List<String> result, List<List<Character>> options, ArrayList<Character> currentResult, int index) {
        if (index == options.size()) {
            //if all letters of the characters have been placed, store the currentResult
            char[] candidate = new char[currentResult.size()];
            for (int i = 0; i < currentResult.size(); i++) {
                candidate[i] = currentResult.get(i);
            }
            result.add(new String(candidate));
        } else {
            for (Character character : options.get(index)) {
                currentResult.add(character);
                recur(result, options, currentResult, index + 1);
                currentResult.remove(currentResult.size() - 1);
            }
        }
    }
}
