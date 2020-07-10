import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/making-file-names-unique/
 * <p>
 * Given an array of strings names of size n. You will create n folders in your file system such that, at the ith minute,
 * you will create a folder with the name names[i].
 * <p>
 * Since two files cannot have the same name, if you enter a folder name which is previously used,
 * the system will have a suffix addition to its name in the form of (k), where, k is the smallest positive integer such that the obtained name remains unique.
 * <p>
 * Return an array of strings of length n where ans[i] is the actual name the system will assign to the ith folder when you create it.
 * <p>
 * Input: names = ["gta","gta(1)","gta","avalon"]
 * <p>
 * Output: ["gta","gta(1)","gta(2)","avalon"]
 * <p>
 * Explanation: Let's see how the file system creates folder names:
 * <p>
 * "gta" --> not assigned before, remains "gta"
 * <p>
 * "gta(1)" --> not assigned before, remains "gta(1)"
 * <p>
 * "gta" --> the name is reserved, system adds (k), since "gta(1)" is also reserved, systems put k = 2. it becomes "gta(2)"
 * <p>
 * "avalon" --> not assigned before, remains "avalon"
 */
public class MakingFileNamesUnique {
    public String[] getFolderNames(String[] names) {
        String[] res = new String[names.length];
        //map of string with the next offset to directly use as suffix in case of conflicts
        // storing offsets directly will avoid recomputation of the same thing again and again
        // e.g. in case of {gta, gta, gta}, after processing two items map will look like {gta, 3} and we can directly
        //use gta(3) for the third index
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (String name : names) {
            if (!map.containsKey(name)) {
                map.put(name, 1);
                res[index++] = name;
            } else {
                int val = map.get(name);
                while (true) {
                    String newName = name + "(" + val++ + ")";
                    if (!map.containsKey(newName)) {
                        map.put(name, val);
                        map.put(newName, 1);
                        res[index++] = newName;
                        break;
                    }
                }
            }
        }
        return res;
    }
}
