import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
 * <p>
 * Given a list of folders, remove all sub-folders in those folders and return in any order the folders after removing.
 * <p>
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 * <p>
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
 * For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.
 * <p>
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b/" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 */
public class RemoveSubFoldersFromFileSystem {
    /**
     * Approach: Problem boils down to filtering out all sub-folders present under a folder
     * Folder hierarchy can be represented as trie. So we stop adding to trie if we find a directory during insertion in trie.
     * In the end just traverse the trie and return all the paths by keeping the current path as a prefix.
     */
    public List<String> removeSubfolders(String[] folders) {
        Folder root = new Folder();
        for (String folder : folders) {
            String[] names = folder.split("/");
            insert(names, root);
        }
        return DFS(root, "/");
    }

    private List<String> DFS(Folder root, String prefix) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Folder> entry : root.subFolders.entrySet()) {
            if (entry.getValue().isEnd) {
                //no need to recurse as we are are not interested in subfolders
                result.add(prefix + entry.getKey());
            } else {
                //update the prefix and recurse
                result.addAll(DFS(entry.getValue(), prefix + entry.getKey() + "/"));
            }
        }
        return result;
    }

    private void insert(String[] names, Folder root) {
        for (String name : names) {
            if (!name.isEmpty()) {//split() function can give empty strings
                if (!root.subFolders.containsKey(name)) {
                    //new subfolder found
                    root.subFolders.put(name, new Folder());
                } else if (root.subFolders.get(name).isEnd) {
                    //ohhoo, a directory already exists in the path of the current folder, no need of continuing further
                    return;
                }
                root = root.subFolders.get(name);
            }
        }
        root.isEnd = true;
    }

    private static class Folder {
        Map<String, Folder> subFolders = new HashMap<>();
        boolean isEnd;
    }
}
