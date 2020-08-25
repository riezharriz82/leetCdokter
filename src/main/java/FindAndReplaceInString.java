import java.util.HashMap;

/**
 * https://leetcode.com/problems/find-and-replace-in-string/
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
 * <p>
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.
 * The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
 * <p>
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff",
 * then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 * <p>
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee",
 * as well as another replacement operation i = 2, x = "ec", y = "ffff",
 * this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
 * <p>
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement:
 * for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 * <p>
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 */
public class FindAndReplaceInString {
    /**
     * Approach: Since all the transformations are instantaneous and there is no overlap, we can just iterate the indices
     * of the original string, check if that index is present in the indexes array, then check if the target substring equals the actual substring
     * if yes, then append the target transformation to the result string
     * else append the original character
     */
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder res = new StringBuilder();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            map.put(indexes[i], i); //this map is required to find the corresponding value of sources and targets at ith index
        }
        int i = 0;
        while (i < S.length()) {
            boolean transformed = false;
            if (map.containsKey(i)) {
                int index = map.get(i); //required to find the sources and targets at that index
                String actual = S.substring(i, i + sources[index].length());
                if (actual.equals(sources[index])) {
                    //expected string matches actual
                    res.append(targets[index]);
                    i += sources[index].length(); // critical to skip all the characters in the actual string to avoid appending them to result
                    transformed = true;
                }
            }
            if (!transformed) {
                res.append(S.charAt(i));
                i++;
            }
        }
        return res.toString();
    }
}
