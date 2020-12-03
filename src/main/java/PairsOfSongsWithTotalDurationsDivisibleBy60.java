/**
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 * <p>
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 * <p>
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
 * Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 * <p>
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * <p>
 * Input: [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 * <p>
 * Note:
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    /**
     * Approach: A pair of integers are divisible by 60 when their individual mod sums are divisible by 60
     * So if current number mod is 20, we have to find numbers with mod 40, such that total mod sum becomes divisible by 60
     * Special case for 0 and 30 because for them, we can count the no of ways of choosing two pairs out of total n ie. nC2
     * <p>
     * Other way of solving this would be to think it similar to 2Sum
     * Initially I thought of solving it using BinarySearch but while writing the code, I thought of hashMap solution
     * <p>
     * {@link MakeSumDivisibleByP} {@link SubarraySumsDivisibleByK} {@link SmallestIntegerDivisibleByK} divisibility related problems
     */
    public int numPairsDivisibleBy60(int[] time) {
        int[] remainders = new int[60];
        for (int i = 0; i < time.length; i++) {
            time[i] = time[i] % 60;
            remainders[time[i]] += 1;
        }
        int result = 0;
        int cnt = remainders[0]; //special case for numbers with mod as 0
        result += (cnt * (cnt - 1)) / 2;
        cnt = remainders[30]; //special case for numbers with mod as 30, because of them falling in the same bucket
        result += (cnt * (cnt - 1)) / 2;
        for (int i = 0; i < time.length; i++) {
            if (time[i] > 0 && time[i] < 30) { //to ensure we don't double count ie. if we have included 40 from 20, we don't want to include 20 from 40
                //so always the smaller mod looks for larger mod
                result += (remainders[60 - time[i]]);
            }
        }
        return result;
    }
}
