/**
 * https://leetcode.com/problems/repeated-string-match/
 */
public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if (sb.toString().contains(B)) {
            return count;
        } else if (sb.append(A).toString().contains(B)) {
            return count + 1;
        }
        return -1;
    }
}
