import java.util.LinkedList;

/**
 * https://leetcode.com/problems/reformat-the-string/
 * <p>
 * Given alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).
 * <p>
 * You have to find a permutation of the string where no letter is followed by another letter and no digit is followed by another digit. That is, no two adjacent characters have the same type.
 * <p>
 * Return the reformatted string or return an empty string if it is impossible to reformat the string.
 * <p>
 * Input: s = "a0b1c2"
 * Output: "0a1b2c"
 * Explanation: No two adjacent characters have the same type in "0a1b2c". "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
 * <p>
 * Input: s = "leetcode"
 * Output: ""
 * Explanation: "leetcode" has only characters so we cannot separate them by digits.
 * <p>
 * Input: s = "1229857369"
 * Output: ""
 * Explanation: "1229857369" has only digits so we cannot separate them by characters.
 * <p>
 * Input: s = "covid2019"
 * Output: "c2o0v1i9d"
 * <p>
 * Input: s = "ab123"
 * Output: "1a2b3"
 * <p>
 * Constraints:
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters and/or digits.
 */
public class ReformatTheString {
    /**
     * Approach: Greedy, Count the no of digits and letters. If their absolute difference is <= 1, then it can be rearranged.
     * Place the letters first if frequency of letters is greater than digits
     * <p>
     * {@link ReorganizeString} {@link RearrangeStringKDistanceApart} {@link DistantBarcodes} related string reorganization problems
     */
    public String reformat(String s) {
        LinkedList<Character> digits = new LinkedList<>();
        LinkedList<Character> letters = new LinkedList<>();
        int numDigits = 0, numLetters = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                numDigits++;
                digits.add(c);
            } else {
                numLetters++;
                letters.add(c);
            }
        }
        if (Math.abs(numDigits - numLetters) > 1) { //invalid case
            return "";
        }
        boolean placeLetter = letters.size() > digits.size(); //allows us to remove duplicate code
        //in my initial implementation i added a if/else check to place letters first if letters.size() > digits.size() and then duplicated the code in else block
        StringBuilder sb = new StringBuilder();
        while (!digits.isEmpty() || !letters.isEmpty()) {
            if (placeLetter && !letters.isEmpty()) {
                sb.append(letters.removeFirst());
            } else if (!digits.isEmpty()) {
                sb.append(digits.removeFirst());
            }
            placeLetter = !placeLetter;
        }
        return sb.toString();
    }
}
