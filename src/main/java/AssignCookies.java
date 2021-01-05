import java.util.Arrays;

/**
 * https://leetcode.com/problems/assign-cookies/
 * <p>
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
 * <p>
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j].
 * If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
 * <p>
 * Input: g = [1,2,3], s = [1,1]
 * Output: 1
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
 * You need to output 1.
 * <p>
 * Input: g = [1,2], s = [1,2,3]
 * Output: 2
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * You have 3 cookies and their sizes are big enough to gratify all of the children,
 * You need to output 2.
 * <p>
 * Constraints:
 * 1 <= g.length <= 3 * 104
 * 0 <= s.length <= 3 * 104
 * 1 <= g[i], s[j] <= 231 - 1
 */
public class AssignCookies {
    /**
     * Approach: Greedy, sort the greed and cookies array and try to assign the smallest cookie that can satisfy a person's greed
     * If current cookie can't satisfy, move on to next cookie
     */
    public int findContentChildren(int[] greed, int[] cookies) {
        Arrays.sort(greed);
        Arrays.sort(cookies);
        int idx = Arrays.binarySearch(cookies, greed[0]); //this is an overkill, because the linear search below overshadows this optimization
        if (idx < 0) {
            idx *= -1;
            idx -= 1;
        }
        //idx now points to first cookie that can satisfy the smallest greed
        if (idx == cookies.length) { //if no cookie can satisfy any greed
            return 0;
        } else {
            int satisfied = 0, greed_idx = 0;
            for (int cookie_idx = idx; cookie_idx < cookies.length && greed_idx < greed.length; cookie_idx++) {
                if (cookies[cookie_idx] >= greed[greed_idx]) { //if current cookie can satisfy current person's greed, move on to other person
                    greed_idx++;
                    satisfied++;
                }
            }
            return satisfied;
        }
    }
}
