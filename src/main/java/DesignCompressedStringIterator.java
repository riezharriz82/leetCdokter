/**
 * https://leetcode.com/problems/design-compressed-string-iterator/ Premium
 * <p>
 * Design and implement a data structure for a compressed string iterator. The given compressed string will be in the form of each letter
 * followed by a positive integer representing the number of this letter existing in the original uncompressed string.
 * <p>
 * Implement the StringIterator class:
 * <p>
 * next() Returns the next character if the original string still has uncompressed characters, otherwise returns a white space.
 * hasNext() Returns true if there is any letter needs to be uncompressed in the original string, otherwise returns false.
 * <p>
 * Input
 * ["StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext"]
 * [["L1e2t1C1o1d1e1"], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, "L", "e", "e", "t", "C", "o", true, "d", true]
 * <p>
 * Explanation
 * StringIterator stringIterator = new StringIterator("L1e2t1C1o1d1e1");
 * stringIterator.next(); // return "L"
 * stringIterator.next(); // return "e"
 * stringIterator.next(); // return "e"
 * stringIterator.next(); // return "t"
 * stringIterator.next(); // return "C"
 * stringIterator.next(); // return "o"
 * stringIterator.hasNext(); // return True
 * stringIterator.next(); // return "d"
 * stringIterator.hasNext(); // return True
 * <p>
 * <p>
 * Constraints:
 * 1 <= compressedString.length <= 1000
 * compressedString consists of lower-case an upper-case English letters and digits.
 * The number of a single character repetitions in compressedString is in the range [1, 10^9]
 * At most 100 calls will be made to next and hasNext.
 */
public class DesignCompressedStringIterator {
    int nextCharIndex; //index of next char
    int multiplier; //stores the multiplier of current char, if 0, then next char needs to be computed
    char current; //refers to the current char
    String input;

    public DesignCompressedStringIterator(String compressedString) {
        input = compressedString;
        compute();
    }

    public char next() {
        if (!hasNext()) { //if there is no character left, return whitespace
            return ' ';
        } else if (multiplier > 0) { //if current character is unfinished
            multiplier--;
            return current;
        } else { //need to compute the next character
            compute();
            multiplier--;
            return current;
        }
    }

    public boolean hasNext() {
        return multiplier != 0 || nextCharIndex != input.length();
    }

    private void compute() {
        current = input.charAt(nextCharIndex);
        int multiplierIndex = nextCharIndex + 1, curMultiplier = 0;
        while (multiplierIndex < input.length() && Character.isDigit(input.charAt(multiplierIndex))) { //extract the digits
            curMultiplier = 10 * curMultiplier + (input.charAt(multiplierIndex) - '0');
            multiplierIndex++;
        }
        //update the variables
        nextCharIndex = multiplierIndex;
        multiplier = curMultiplier;
    }
}
