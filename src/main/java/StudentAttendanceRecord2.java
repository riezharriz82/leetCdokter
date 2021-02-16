/**
 * https://leetcode.com/problems/student-attendance-record-ii/
 * <p>
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable.
 * The answer may be very large, return it after mod 109 + 7.
 * <p>
 * A student attendance record is a string that only contains the following three characters:
 * <p>
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 * <p>
 * Input: n = 2
 * Output: 8
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" won't be regarded as rewardable owing to more than one absent times.
 * Note: The value of n won't exceed 100,000.
 */
public class StudentAttendanceRecord2 {
    int MOD = 1_000_000_007;

    /**
     * Approach: Recursion with memoization, key takeaway from this problem is to figure out the information required at each state,
     * this will help in figuring the parameters required in recursion. Here we need to know the count of P, A and consecutive L present
     * at each state
     * <p>
     * During my initial thought process, I started thinking of solving this using maths i.e result would be total no of ways to generate string -
     * invalid records. Generating invalid records was getting a bit tricky for counting more than 2 consecutive L's. Maybe I could have
     * used recursion to solve that
     * <p>
     * {@link KnightDialer} {@link TwoCityScheduling} {@link DiceRollSimulation} related recursive counting problem
     */
    public int checkRecord(int n) {
        int[][][] dp = new int[n][2][3];
        return recur(0, 0, 0, n, dp);
    }

    private int recur(int i, int A, int consecutiveL, int n, int[][][] dp) {
        if (i == n) {
            return 1;
        } else if (dp[i][A][consecutiveL] != 0) {
            return dp[i][A][consecutiveL];
        }
        //please note that we reset consecutiveL to 0, when choosing P and A, because we are not interested in count of L
        //but are interested in consecutively placed L, as we can have only max 2 consecutively placed L
        int count = recur(i + 1, A, 0, n, dp); //place P
        if (A == 0) { //only one A allowed, place only if A isn't placed
            count = (count + recur(i + 1, A + 1, 0, n, dp)) % MOD;
        }
        if (consecutiveL < 2) { //can't have more than 2 consecutive L's
            count = (count + recur(i + 1, A, consecutiveL + 1, n, dp)) % MOD;
        }
        return dp[i][A][consecutiveL] = count;
    }
}
