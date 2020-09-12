import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/expressive-words/
 * <p>
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".
 * In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 * <p>
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation:
 * choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
 * <p>
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo",
 * but we cannot get "helloo" since the group "oo" has size less than 3.
 * Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".
 * If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 * <p>
 * Given a list of query words, return the number of words that are stretchy.
 * <p>
 * Input:
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 */
public class ExpressiveWords {
    /**
     * The main task was not coming up with the algorithm but to understand the problem statement itself.
     * ea can't be stretched to eaeaea because we can stretch only blocks of same characters
     * <p>
     * oo can be stretched to ooo because the problem statement says that the end block, if stretched should be of min 3 characters.
     * It does not say anything about adding minimum of 3 characters (that was my wrong assumption)
     * <p>
     * o cant be stretched to oo because the minimum stretch should be of 3 characters
     * <p>
     * Approach: Create a Run length encoding of the input word and the target word
     */
    public int expressiveWords(String S, String[] words) {
        if (S.length() == 0) {
            return 0;
        }
        List<Pair<Character, Integer>> inputGroups = generateGroupLength(S);
        int result = 0;
        for (String word : words) {
            List<Pair<Character, Integer>> wordGroups = generateGroupLength(word);
            if (wordGroups.size() == inputGroups.size()) {
                boolean isExpressive = true;
                for (int i = 0; i < wordGroups.size(); i++) {
                    Pair<Character, Integer> inputGroup = inputGroups.get(i);
                    Pair<Character, Integer> wordGroup = wordGroups.get(i);
                    if (inputGroup.getKey() != wordGroup.getKey()) { // if characters mismatch, then it can't be stretched
                        isExpressive = false;
                        break;
                    }
                    if (inputGroup.getValue() < wordGroup.getValue()) { //if input has less characters than the target, it can't be stretched
                        isExpressive = false;
                        break;
                    }
                    if (inputGroup.getValue() < 3 && inputGroup.getValue() != wordGroup.getValue()) {
                        //if input has 1,2 characters then the target should match it, because we can't stretch it as min stretch should be of 3 characters.
                        isExpressive = false;
                        break;
                    }
                }
                if (isExpressive) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * If the input is aaabbcccc, it returns {a=3, b=2, c=4}
     */
    private List<Pair<Character, Integer>> generateGroupLength(String s) {
        List<Pair<Character, Integer>> input = new ArrayList<>();
        int index = 1, curCount = 1;
        char prev = s.charAt(0);
        while (index < s.length()) {
            char current = s.charAt(index);
            if (current != prev) {
                input.add(new Pair<>(prev, curCount));
                prev = current;
                curCount = 1;
            } else {
                curCount++;
            }
            index++;
        }
        input.add(new Pair<>(prev, curCount));
        return input;
    }
}
