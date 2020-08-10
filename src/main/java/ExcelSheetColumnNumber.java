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
