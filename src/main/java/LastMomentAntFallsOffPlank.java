/**
 * https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/
 */
public class LastMomentAntFallsOffPlank {
    public int getLastMoment(int n, int[] left, int[] right) {
        int maxTime = 0;
        for (int i = 0; i < left.length; i++) {
            maxTime = Math.max(maxTime, left[i]); //alternatively just pick the max value in the left array
        }
        for (int i = 0; i < right.length; i++) {
            maxTime = Math.max(maxTime, n - right[i]); //alternatively pick the min value in the right array
        }
        return maxTime;
    }
}
