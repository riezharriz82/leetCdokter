import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/remove-comments/
 * <p>
 * Given a C++ program, remove comments from it. The program source is an array where source[i] is the i-th line of the source code.
 * This represents the result of splitting the original source code string by the newline character \n.
 */
public class RemoveComments {
    /**
     * Approach: String parsing while maintaining state, visualize it as a state machine, moving from one state to another
     * If /* is encountered, enable the blockComment mode and don't add anything until *{@literal /} is found
     * If not in blockComment and // is found, skip the entire line
     */
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        boolean blockComment = false; //needs to be instantiated outside because blockComment can span across multiple lines
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < source.length; i++) {
            String line = source[i];
            for (int j = 0; j < line.length(); j++) {
                if (blockComment) {
                    if (line.charAt(j) == '*' && j + 1 < line.length() && line.charAt(j + 1) == '/') {
                        blockComment = false;
                        j++; //skip the /
                    }
                } else {
                    if (line.charAt(j) == '/' && j + 1 < line.length() && line.charAt(j + 1) == '/') {
                        //line comment encountered, skip the entire line
                        break;
                    } else if (line.charAt(j) == '/' && j + 1 < line.length() && line.charAt(j + 1) == '*') {
                        blockComment = true;
                        j++; //skip the *
                    } else {
                        //valid character found
                        current.append(line.charAt(j));
                    }
                }
            }
            if (current.length() > 0 && !blockComment) { //if current string is non empty and block comment is not open, add it to the result
                result.add(current.toString());
                current.setLength(0); //reset the new line
            }
        }
        return result;
    }
}
