import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/
 * <p>
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * <p>
 * For example:
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * AAA -> 702
 */
public class ExcelSheetColumnNumber {

    /**
     * https://leetcode.com/problems/excel-sheet-column-title/
     * Input: 701
     * Output: "ZY"
     */
    public String convertToTitle(int n) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        while (n != 0) {
            n--; //this step is the most important one, this is required because 0 is not present as valid digit so every digit is off by 1
            //rest everything is similar to how you will treat binary string
            int remainder = (n % 26);
            stack.push((char) ('A' + remainder));
            n /= 26;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.toString();
    }

    /**
     * Instead of using Math.pow and iterate from the end like a noob, instead iterate from the start and multiply the base to the previous result
     * {@link DecodeString} used the same pattern for multiplier
     */
    public int titleToNumberSimplified(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = 26 * res + (s.charAt(i) - 'A' + 1);
        }
        return res;
    }

    /**
     * Very similar to converting a binary number to its decimal equivalent
     */
    public int titleToNumber(String s) {
        int n = s.length();
        int res = 0;
        int multiplier = 0;
        for (int i = n - 1; i >= 0; i--) {
            res += (Math.pow(26, multiplier) * (s.charAt(i) - 'A' + 1));
            multiplier++;
        }
        return res;
    }
}
