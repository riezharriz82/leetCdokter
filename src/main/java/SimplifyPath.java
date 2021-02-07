import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/simplify-path/
 * <p>
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system,
 * convert it to the simplified canonical path.
 * <p>
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level,
 * and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
 * For this problem, any other format of periods such as '...' are treated as file/directory names.
 * <p>
 * The canonical path should have the following format:
 * <p>
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 * <p>
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * <p>
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * <p>
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 * <p>
 * Input: path = "/a/./b/../../c/"
 * Output: "/c"
 * <p>
 * Constraints:
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 **/
public class SimplifyPath {
    /**
     * Approach: Use Stack to represent moving up and down the directories
     * <p>
     * {@link LongestAbsoluteFilePath} related file path problem
     */
    public String simplifyPath(String path) {
        String[] splits = path.split("/");
        ArrayDeque<String> queue = new ArrayDeque<>();
        for (String split : splits) {
            split = split.trim();
            if (split.equals("..")) { //need to pop up the top of the stack if there is a directory present
                if (!queue.isEmpty()) {
                    queue.pollLast();
                }
            } else if (!split.isEmpty() && !split.equals(".")) { //ignore . and visit any directory if valid
                queue.addLast(split);
            }
        }
        //need to model it as a queue instead because we need to concatenate file path from the beginning
        //PS: If you use enhanced for loop on stack, it will return the contents in opposite direction, sounds weird!
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        while (!queue.isEmpty()) {
            sb.append(queue.pollFirst());
            sb.append("/");
        }
        if (sb.length() > 1) { //edge case to handle when path == "/"
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
