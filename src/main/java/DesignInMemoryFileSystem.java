import java.util.*;

/**
 * https://leetcode.com/problems/design-in-memory-file-system/
 * <p>
 * Design an in-memory file system to simulate the following functions:
 * <pre>
 * ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
 * If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.
 *
 * mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
 * If the middle directories in the path don't exist either, you should create them as well. This function has void return type.
 *
 * addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content.
 * If the file already exists, you need to append given content to original content. This function has void return type.
 *
 * readContentFromFile: Given a file path, return its content in string format.
 * </pre>
 * Input:
 * ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 * [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 * <p>
 * Output:
 * [null,[],null,null,["a"],"hello"]
 * <p>
 * Note:
 * You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
 * You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
 * You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
 */
public class DesignInMemoryFileSystem {
    /**
     * Approach: Model the file system as a N-Ary Tree
     * <p>
     * {@link DesignSearchAutocompleteSystem} {@link LRUCache}
     */
    Node root = new Node();

    public DesignInMemoryFileSystem() {

    }

    public List<String> ls(String path) {
        if (path.equals("/")) {
            return listDirectory(root);
        }
        String[] split = path.split("/");
        Node temp = root;
        int n = split.length;
        //traverse the intermediate directories, problem statement guarantees that the path exists
        for (int i = 1; i < n - 1; i++) {
            temp = temp.directories.get(split[i]);
        }
        //last name can be a directory or a path
        if (temp.directories.containsKey(split[n - 1])) {
            return listDirectory(temp.directories.get(split[n - 1]));
        } else {
            return Arrays.asList(split[n - 1]); //return only the file name
        }
    }

    private List<String> listDirectory(Node node) {
        List<String> names = new ArrayList<>();
        names.addAll(node.directories.keySet());
        names.addAll(node.files.keySet());
        //in actual interview, we have to clarify the frequency of ls vs insert
        //if ls() is higher, then we can use TreeMap to avoid repeated sorting
        Collections.sort(names);
        return names;
    }

    public void mkdir(String path) {
        String[] split = path.split("/");
        Node temp = root;
        for (int i = 1; i < split.length; i++) {
            String directoryName = split[i];
            temp.directories.putIfAbsent(directoryName, new Node());
            temp = temp.directories.get(directoryName);
        }
    }

    public void addContentToFile(String filePath, String content) {
        String[] split = filePath.split("/");
        Node temp = root;
        int n = split.length;
        //traverse the intermediate directories
        for (int i = 1; i < n - 1; i++) {
            temp = temp.directories.get(split[i]);
        }
        temp.files.computeIfAbsent(split[n - 1], __ -> new StringBuilder()).append(content);
    }

    public String readContentFromFile(String filePath) {
        String[] split = filePath.split("/");
        Node temp = root;
        int n = split.length;
        //traverse the intermediate directories
        for (int i = 1; i < n - 1; i++) {
            temp = temp.directories.get(split[i]);
        }
        return temp.files.get(split[n - 1]).toString();
    }

    /**
     * Another way of designing the class would be to unify the files and directories by maintaining a single Map<String, Node>
     * Add another attribute like content which would be non-empty in case the Node is a file.
     * TreeMap could be used to avoid repeated sorting in ls()
     */
    private static class Node {
        Map<String, StringBuilder> files = new HashMap<>();
        Map<String, Node> directories = new HashMap<>();
    }
}
