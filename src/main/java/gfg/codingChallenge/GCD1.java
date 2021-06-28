package gfg.codingChallenge;

import java.util.ArrayList;

/**
 * <pre>
 * Given two integers L and R, find two integers, A and B, L <= A , B <= R such that GCD(A, B) == 1
 * </pre>
 */
public class GCD1 {
    /**
     * Approach: Ad-hoc tricky question, the trick is to note that even and odd integers are always co-prime.
     * So if L is even, return L, L+1 as L+1 is guaranteed to be odd
     * and if L is odd, return L, L+1 as L+1 will be even.
     * However if L==R && L != 1, return -1
     * Missed the edge case of [1, 1] during the challenge, I was returning [-1,-1] instead of [1,1]
     */
    ArrayList<Long> solve(long L, long R) {
        ArrayList<Long> res = new ArrayList<>();
        if (L == 1) {
            res.add(1L);
            res.add(Math.min(L + 1, R));
        } else if (L == R) {
            res.add(-1L);
            res.add(-1L);
        } else {
            res.add(L);
            res.add(L + 1);
        }
        return res;
    }
}
