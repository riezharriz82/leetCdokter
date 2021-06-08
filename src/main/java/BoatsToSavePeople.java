import java.util.Arrays;

/**
 * https://leetcode.com/problems/boats-to-save-people/
 * <p>
 * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
 * <p>
 * Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most limit.
 * <p>
 * Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried by a boat.)
 * <p>
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 * <p>
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 * <p>
 * Input: people = [3,5,3,4], limit = 5
 * Output: 4
 * Explanation: 4 boats (3), (3), (4), (5)
 * <p>
 * Note:
 * 1 <= people.length <= 50000
 * 1 <= people[i] <= limit <= 30000
 */
public class BoatsToSavePeople {
    /**
     * Approach: Since we have to choose only two people in a boat, we don't have to worry about using knapsack because of constraints
     * and have to rely on greedy. We have to pair smaller weight people with higher weight people in order to do an optimal fit i.e reduce wastage
     * <p>
     * {@link TwoCityScheduling} {@link OnesAndZeroes} {@link FurthestBuildingYouCanReach} {@link SplitArrayIntoConsecutiveSubsequences}
     * {@link CarFleet} {@link FerrisWheel} {@link ArrayPartition1}
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int low = 0, high = n - 1, boats = 0;
        while (low < high) {
            if (people[low] + people[high] <= limit) { //if two people can sit on the boat
                low++;
                high--;
            } else { //two people can't sit on the boat, now the optimal strategy is to place the higher weight person on a boat because the smaller weight
                //person can pair up with the next highest weight person e.g {1,8,9} and max weight of 9
                //1 and 9 can't sit on the boat together, so place 9 on the boat
                //this will allow 1 to pair up with 8 on a single boat.
                high--;
            }
            boats++;
        }
        if (low == high) { //single person remains
            boats++;
        }
        return boats;
    }
}
