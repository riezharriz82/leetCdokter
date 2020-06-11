/**
 * https://leetcode.com/problems/length-of-last-word/
 */
public class LengthofLastWord {
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
