import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/rearrange-words-in-a-sentence/
 * Given a sentence text (A sentence is a string of space-separated words) in the following format:
 * <p>
 * First letter is in upper case.
 * Each word in text are separated by a single space.
 * Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
 * If two words have the same length, arrange them in their original order.
 * <p>
 * Return the new text following the format shown above.
 * <p>
 * Input: text = "Keep calm and code on"
 * Output: "On and keep calm code"
 * Explanation: Output is ordered as follows:
 * "On" 2 letters.
 * "and" 3 letters.
 * "keep" 4 letters in case of tie order by position in original text.
 * "calm" 4 letters.
 * "code" 4 letters.
 */
public class RearrangeWordsInSentenceByWordLength {

    //Tricky thing to note is the case when string length is equal we need to preserve the original order
    // So this is a hint towards stable sort
    // Luckily Arrays.sort use stable sort, so we need not worry about storing the original index in another array
    // My initial solution was to use pair<string, integer> and to return the final string by streaming the arrays and
    // use collector.joining to join the strings
    // but a simpler way to join would be to use String.join()
    // That's why boys remember your Collection API
    public String arrangeWords(String text) {
        String[] splits = text.split(" ");
        splits[0] = splits[0].toLowerCase();
        Arrays.sort(splits, Comparator.comparingInt(String::length));
        splits[0] = splits[0].substring(0, 1).toUpperCase() + splits[0].substring(1);
        return String.join(" ", splits);
    }
}
