import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/most-visited-sector-in-a-circular-track/
 * <p>
 * Given an integer n and an integer array rounds. We have a circular track which consists of n sectors labeled from 1 to n.
 * A marathon will be held on this track, the marathon consists of m rounds.
 * The ith round starts at sector rounds[i - 1] and ends at sector rounds[i]. For example, round 1 starts at sector rounds[0] and ends at sector rounds[1]
 * <p>
 * Return an array of the most visited sectors sorted in ascending order.
 * <p>
 * Notice that you circulate the track in ascending order of sector numbers in the counter-clockwise direction (See the first example).
 * <p>
 * Input: n = 4, rounds = [1,3,1,2]
 * Output: [1,2]
 * Explanation: The marathon starts at sector 1. The order of the visited sectors is as follows:
 * 1 --> 2 --> 3 (end of round 1) --> 4 --> 1 (end of round 2) --> 2 (end of round 3 and the marathon)
 * We can see that both sectors 1 and 2 are visited twice and they are the most visited sectors. Sectors 3 and 4 are visited only once.
 */
public class MostVisitedSectorsInCircularTrack {

    /**
     * This is simulating the marathon rounds by incrementing the visits to each sector per round
     * Key implementation detail is to handle wrap around as it's a circle. So if total sectors are 4 and we are at 4th, next one should be 1st
     * Wrapping is done by modulo
     */
    public List<Integer> mostVisited(int n, int[] rounds) {
        //input is 1 based, hence -1 everywhere
        int[] sectors = new int[n];
        sectors[rounds[0] - 1]++; // done to avoid double counting
        int start = rounds[0] - 1;
        for (int i = 1; i < rounds.length; i++) {
            //critical, keep moving until we reach target sector of this round
            while (start != rounds[i] - 1) {
                start = (start + 1) % n; //handles wrapping around
                sectors[start]++; //increment the visit count
            }
        }
        int max = 0;
        List<Integer> res = new ArrayList<>();
        for (int num : sectors) {
            if (num > max) {
                max = num;
            }
        }
        //need to return multiple sectors with max visit
        for (int i = 0; i < n; i++) {
            if (sectors[i] == max) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
