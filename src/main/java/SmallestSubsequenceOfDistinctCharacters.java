import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 * https://leetcode.com/problems/remove-duplicate-letters/
 * <p>
 * Return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
 * <p>
 * Input: s = "bcabc"
 * Output: "abc"
 * <p>
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 */
public class SmallestSubsequenceOfDistinctCharacters {
    /**
     * Approach: Very tricky solution, was not able to come up to a clean solution on my own.
     * Idea is to trim characters that are greater than current character and occur on the right of current character
     * e.g {cac} when at a, we check that previous c is present on the right of a, hence can be removed
     * <p>
     * This builds up the result by fixing smallest distinct character of the subsequence one by one.
     */
    public String smallestSubsequence(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        boolean[] visited = new boolean[26];
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int index = 0;
        while (index < s.length()) {
            char val = s.charAt(index++);
            if (visited[val - 'a']) { //if the character is already present in the stack, we can skip processing it
                cnt[val - 'a']--;
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > val && cnt[stack.peek() - 'a'] > 0) {
                //if the current character is smaller than the previous character and previous character occurs on the right of current character
                // pop the previous character in order to create a lexicographically smaller subsequence
                visited[stack.peek() - 'a'] = false; //so that the duplicate character can be picked up again in the future
                stack.pop();
            }
            stack.push(val);
            visited[val - 'a'] = true;
            cnt[val - 'a']--;
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}
