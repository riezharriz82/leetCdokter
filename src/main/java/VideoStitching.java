/**
 * https://leetcode.com/problems/video-stitching/
 * <p>
 * You are given a series of video clips from a sporting event that lasted T seconds.
 * These video clips can be overlapping with each other and have varied lengths.
 * <p>
 * Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].
 * We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * <p>
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).
 * If the task is impossible, return -1.
 * <p>
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * Output: 3
 * Explanation:
 * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 */
public class VideoStitching {
    /**
     * Approach: Greedy logic, similar to {@link JumpGame2} {@link MinNumberOfTapsToOpenToWaterGarden}
     */
    public int videoStitching(int[][] clips, int T) {
        int[] jumps = new int[T + 1];
        for (int[] clip : clips) {
            if (clip[0] <= T) { //interested only in clips starting before T
                jumps[clip[0]] = Math.max(jumps[clip[0]], Math.min(clip[1], T));
                //max condition is required to handle input like [0,5], [0,10]
                //min condition to handle timestamps greater than T
            }
        }
        int levelEnd = 0, levels = 0, farthestIndex = 0;
        for (int i = 0; i <= levelEnd; i++) {
            farthestIndex = Math.max(farthestIndex, jumps[i]);
            if (i == T) { //if target timestamp reached
                return levels;
            }
            if (i == levelEnd) { //end of the current level, update the bounds of the next level
                levels++;
                levelEnd = farthestIndex;
            }
        }
        return -1;
    }
}
