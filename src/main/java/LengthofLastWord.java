/**
 * https://leetcode.com/problems/length-of-last-word/
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word (last word means the last appearing word if we loop from left to right) in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a maximal substring consisting of non-space characters only.
 * <p>
 * Example:
 * <p>
 * Input: "Hello World"
 * Output: 5
 */
public class LengthofLastWord {
    /**
     * Approach: Just iterate from the end to find the index of the first non space character
     * Once found, iterate until we find a space character. Keep track of the length of the string.
     */
    public int lengthOfLastWordInSingleLoop(String s) {
        int lastNonSpaceCharIndex = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (!Character.isSpaceChar(s.charAt(i))) {
                lastNonSpaceCharIndex = i;
                break;
            }
        }
        if (lastNonSpaceCharIndex == -1) {
            return 0;
        } else {
            int len = 0;
            for (int i = lastNonSpaceCharIndex; i >= 0; i--) {
                if (Character.isSpaceChar(s.charAt(i))) {
                    break;
                }
                len++;
            }
            return len;
        }
    }

    /**
     * My initial approach: How stupid I was to write this complicated code
     */
    public int lengthOfLastWord(String s) {
        int last_space_index = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                last_space_index = i;
            }
        }
        if (last_space_index == -1) { // no spaces found, return the entire word
            return s.length();
        }
        if (last_space_index == s.length() - 1) { //space is the last character, need to loop back
            for (int i = last_space_index; i >= 0; i--) {
                if (s.charAt(i) != ' ') { //valid character found
                    for (int j = i; j >= 0; j--) {
                        if (s.charAt(j) == ' ') {
                            return i - j; //for handling cases like '  abc  '
                        }
                    }
                    return i + 1; //for cases like 'a  '
                }
            }
        }
        return s.length() - last_space_index - 1;
    }
}
