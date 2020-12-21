import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/cracking-the-safe/
 * <p>
 * There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.
 * <p>
 * While entering a password, the last n digits entered will automatically be matched against the correct password.
 * <p>
 * For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.
 * <p>
 * Return any password of minimum length that is guaranteed to open the box at some point of entering it.
 * <p>
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * <p>
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 * <p>
 * Note:
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 */
public class CrackingTheSafe {
    /**
     * Approach: This is a well known mathematical problem known as De Bruijn Sequence. There are multiple ways of doing it by
     * constructing euler path or hamiltonian path
     * <p>
     * Here I am going to code the euler path. This can be modelled as graph problem, where two nodes are connected 0123 -> 1234
     * if first k-1 chars is a substring of the last k-1 chars of previous node
     * So previous node and current node differs just by two character, one less at the front and one new char at the end
     * So if we start traversing from any node and greedily walk the graph, we can traverse the entire graph
     * <p>
     * In my initial thought process, I thought of solving this by tries, but it needs to be modelled as graph instead
     * <p>
     * {@link ReconstructItinerary} related euler path problem
     */
    public String crackSafe(int n, int k) {
        String initial = "";
        for (int i = 0; i < n - 1; i++) { //initial string length of n-1
            initial += "0";
        }
        HashSet<String> visited = new HashSet<>(); //visited contains unique n letter words
        StringBuilder sb = new StringBuilder();
        DFS(initial, k, visited, sb);
        sb.append(initial); //append it after the traversal is finished
        return sb.toString();
    }

    private void DFS(String current, int k, HashSet<String> visited, StringBuilder sb) {
        for (int i = 0; i < k; i++) {
            String candidate = current + i;
            if (!visited.contains(candidate)) {
                visited.add(candidate);
                DFS(candidate.substring(1), k, visited, sb); //pass a new string after removing the first char, so that the string length always remains n-1
                sb.append(i); //append after all the children are visited (postorder traversal)
            }
        }
    }

    /**
     * Approach: This is exactly similar to the way we wrote the backtracking solution in {@link ReconstructItinerary}
     * Hierholzer algo for generating euler path is an optimization to the backtracking algorithm as it does not discard already computed path
     * even though it did not visited all nodes
     */
    public String crackSafeBacktracking(int n, int k) {
        String initial = "";
        for (int i = 0; i < n - 1; i++) {
            initial += "0";
        }
        HashSet<String> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        sb.append(initial);
        int targetSize = (int) Math.pow(k, n);
        DFS(sb, k, visited, initial, targetSize);
        return sb.toString();
    }

    private boolean DFS(StringBuilder sb, int k, Set<String> visited, String current, int targetSize) {
        if (visited.size() == targetSize) {
            return true;
        }
        for (int i = 0; i < k; i++) {
            String candidate = current + i;
            if (!visited.contains(candidate)) {
                visited.add(candidate);
                sb.append(i);
                if (DFS(sb, k, visited, candidate.substring(1), targetSize)) {
                    return true;
                }
                visited.remove(candidate);
                sb.setLength(sb.length() - 1); //need to be a bit careful while backtracking on stringbuilder because i can be of multiple digits not just 1 digit
                //but since input restricts k to 10, i can be from 0 to 9 only, so only 1 digit
            }
        }
        return false;
    }
}
