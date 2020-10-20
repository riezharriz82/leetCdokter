import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/longest-absolute-file-path/
 * <p>
 * We will represent the file system as a string where "\n\t" mean a subdirectory of the main directory, "\n\t\t" means a
 * subdirectory of the subdirectory of the main directory and so on. Each folder will be represented as a string of letters and/or digits.
 * Each file will be in the form "s1.s2" where s1 and s2 are strings of letters and/or digits.
 * <p>
 * For example, the file system above is represented as "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext".
 * <p>
 * Given a string input representing the file system in the explained format, return the length of the longest absolute path to a file
 * in the abstracted file system. If there is no file in the system, return 0.
 * <p>
 * Input: input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 * Output: 20
 * Explanation: We have only one file and its path is "dir/subdir2/file.ext" of length 20.
 * The path "dir/subdir1" doesn't contain any files.
 */
public class LongestAbsoluteFilePath {
    /**
     * Approach: File System can be modelled as tree, longest file path can be the sum of length of parent directories
     * Here the problem is how to model the solution as tree? How to know what level a filename is present in.
     * If you look at the diagram, you will notice a pattern by looking at the no of \t present in the filename
     * e.g \tabc is at level 1, \t\t\tabc is at level 3, here level indicates the nesting level.
     * <p>
     * But how to backtrack? if you observe \t\t\t\tabc.txt followed by \tdir2, you will notice that dir2 is at level1, so
     * we need to pop all directories present in the stack with greater level. Here level of a directory is similar to finding the
     * current stack size. So we just need to pop all elements until the stack size() becomes equal to current level
     * <p>
     * I was able to solve this problem on my own but it was a bit long, i was manually parsing everything including extracting the file name
     * and counting no of \t and skipping \n. Core logic was still same but due to string parsing it became long.
     * <p>
     * Trick to shorten the code length was to leverage \n to split the input to retrieve all the files directly.
     * Also lastIndexOf() can be used to quickly count numbers of \t present in the current file name.
     */
    public int lengthLongestPath(String input) {
        String[] split = input.split("\n");
        ArrayDeque<Integer> stack = new ArrayDeque<>(); //stack representing prefix sum of length of path
        //e.g if string is {abc\n\tdefg\n\t\thijkl}
        //stack will contain {3, 7, 13}
        //prefix sum allows us to directly compute the length of current level given previous level
        if (split.length == 1) {
            if (split[0].contains(".")) {
                return split[0].length();
            }
        }
        int longestPath = 0;
        stack.push(split[0].length());
        for (int i = 1; i < split.length; i++) {
            String name = split[i];
            int level = name.lastIndexOf('\t') + 1; //+1 because we need count
            while (stack.size() != level) { //assign the file to correct level, if current level < level of the last added directory in the stack
                stack.pop();
            }
            int curLength = name.length() - level;
            if (name.contains(".")) { //if file, compute the length of path
                longestPath = Math.max(longestPath, curLength + (stack.isEmpty() ? 0 : stack.peek() + stack.size()));
                // + stack.size() because we need to count '/' in the resultant file path.
            } else { //if directory, push it
                stack.push(curLength + (stack.isEmpty() ? 0 : stack.peek()));
            }
        }
        return longestPath;
    }
}
