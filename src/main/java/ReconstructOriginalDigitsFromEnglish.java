/**
 * https://leetcode.com/problems/reconstruct-original-digits-from-english/
 * <p>
 * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
 * <p>
 * Note:
 * Input contains only lowercase English letters.
 * Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
 * Input length is less than 50,000.
 * <p>
 * Input: "owoztneoer"
 * Output: "012"
 * <p>
 * Input: "fviefuro"
 * Output: "45"
 */
public class ReconstructOriginalDigitsFromEnglish {
    /**
     * Approach: Greedy does not work e.g "twonine", if we greedily check whether "one" is present then it will result in invalid result
     * as "one" won't result in optimal utilization of characters and would result in incorrect result.
     * <p>
     * In my initial solution I greedily deducted required characters of zero, checked how many times we can deduct it e.g "zerozero"
     * but it resulted in WA, as it resulted in impartial fit and resulted in leftover digits.
     * <p>
     * Correct solution would be to look for uniqueness e.g.
     * 'z' uniquely occurs in "zero"
     * 'w' uniquely occurs in "two"
     * 'u' uniquely occurs in "four"
     * 'x' uniquely occurs in "six"
     * 'g' uniquely occurs in "eight"
     * <p>
     * After we deduct frequency of required characters, we are left with 5 odd digits
     * In remaining odd digits, 'o' occurs only in 'one'
     * In remaining odd digits, 't' occurs only in 'three'
     * In remaining odd digits, 'f' occurs only in 'five'
     * In remaining odd digits, 's' occurs only in 'seven'
     * 'nine' has no unique character, so we have to find remaining frequencies
     * <p>
     * {@link MaximumProductOfWordLengths} {@link NumberOfMatchingSubsequences} {@link DetermineIfTwoStringsAreClose}
     * {@link PartitionEqualSubsetSum} {@link ReconstructItinerary}
     */
    public String originalDigits(String s) {
        int[] freqs = new int[26];
        for (char c : s.toCharArray()) {
            freqs[c - 'a']++;
        }
        StringBuilder result = new StringBuilder();
        int[] times = new int[10]; //stores how many times a digit is repeated
        if (freqs['z' - 'a'] > 0) { //zero
            int multiples = freqs['z' - 'a'];
            freqs['z' - 'a'] -= multiples;
            freqs['e' - 'a'] -= multiples;
            freqs['r' - 'a'] -= multiples;
            freqs['o' - 'a'] -= multiples;
            times[0] += multiples; //update the times zero has been repeated
        }
        if (freqs['w' - 'a'] > 0) { //two
            int multiples = freqs['w' - 'a'];
            freqs['t' - 'a'] -= multiples;
            freqs['w' - 'a'] -= multiples;
            freqs['o' - 'a'] -= multiples;
            times[2] += multiples;
        }
        if (freqs['u' - 'a'] > 0) { //four
            int multiples = freqs['u' - 'a'];
            freqs['f' - 'a'] -= multiples;
            freqs['o' - 'a'] -= multiples;
            freqs['u' - 'a'] -= multiples;
            freqs['r' - 'a'] -= multiples;
            times[4] += multiples;
        }
        if (freqs['x' - 'a'] > 0) { //six
            int multiples = freqs['x' - 'a'];
            freqs['s' - 'a'] -= multiples;
            freqs['i' - 'a'] -= multiples;
            freqs['x' - 'a'] -= multiples;
            times[6] += multiples;
        }
        if (freqs['g' - 'a'] > 0) { //eight
            int multiples = freqs['g' - 'a'];
            freqs['e' - 'a'] -= multiples;
            freqs['i' - 'a'] -= multiples;
            freqs['g' - 'a'] -= multiples;
            freqs['h' - 'a'] -= multiples;
            freqs['t' - 'a'] -= multiples;
            times[8] += multiples;
        }
        if (freqs['o' - 'a'] > 0) { //one
            int multiples = freqs['o' - 'a'];
            freqs['o' - 'a'] -= multiples;
            freqs['n' - 'a'] -= multiples;
            freqs['e' - 'a'] -= multiples;
            times[1] += multiples;
        }
        if (freqs['t' - 'a'] > 0) { //three
            int multiples = freqs['t' - 'a'];
            freqs['t' - 'a'] -= multiples;
            freqs['h' - 'a'] -= multiples;
            freqs['r' - 'a'] -= multiples;
            freqs['e' - 'a'] -= multiples;
            freqs['e' - 'a'] -= multiples;
            times[3] += multiples;
        }
        if (freqs['f' - 'a'] > 0) { //five
            int multiples = freqs['f' - 'a'];
            freqs['f' - 'a'] -= multiples;
            freqs['i' - 'a'] -= multiples;
            freqs['v' - 'a'] -= multiples;
            freqs['e' - 'a'] -= multiples;
            times[5] += multiples;
        }
        if (freqs['s' - 'a'] > 0) { //seven
            int multiples = freqs['s' - 'a'];
            freqs['s' - 'a'] -= multiples;
            freqs['e' - 'a'] -= multiples;
            freqs['v' - 'a'] -= multiples;
            freqs['e' - 'a'] -= multiples;
            freqs['n' - 'a'] -= multiples;
            times[7] += multiples;
        }
        if (freqs['i' - 'a'] > 0) { //nine
            int multiples = freqs['i' - 'a'];
            freqs['n' - 'a'] -= multiples;
            freqs['i' - 'a'] -= multiples;
            freqs['n' - 'a'] -= multiples;
            freqs['e' - 'a'] -= multiples;
            times[9] += multiples;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < times[i]; j++) {
                result.append(i);
            }
        }
        return result.toString();
    }
}
