public class MakeParenthesesValid2 {
    /**
     * ans represent the number of parentheses already added.
     * cnt represent the number of right parentheses needed.
     */
    public int minInsertions(String s) {
        int ans = 0;
        int cnt = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                cnt += 2;
                if (cnt % 2 != 0) {
                    ans++;
                    cnt--;
                }
            } else {
                cnt -= 1;
                if (cnt < 0) {
                    ans++;
                    cnt += 2;
                }
            }
        }
        return ans + cnt;
    }
}
