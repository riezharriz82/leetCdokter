/**
 * https://leetcode.com/problems/maximize-distance-to-closest-person/
 * <p>
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0
 * represents that the ith seat is empty (0-indexed).
 * <p>
 * There is at least one empty seat, and at least one person sitting.
 * <p>
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * <p>
 * Return that maximum distance to the closest person.
 * <p>
 * Input: seats = [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 */
public class MaximiseDistanceToClosestPerson {
    /**
     * Approach: Similar to previously solved {@link ExamRoom}
     * Edge cases to consider: Distance between first seat and first occupied seat, distance between last seat and last occupied seats
     * For the remaining occupied seats, a candidate seat would be in the middle, keep track of distances between candidate and occupied seats.
     */
    public int maxDistToClosest(int[] seats) {
        int lastOccupiedSeat = -1;
        int maxDistance = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (lastOccupiedSeat == -1) {
                    maxDistance = i; //distance between the first seat and first occupied seat
                } else {
                    int mid = (lastOccupiedSeat + i) / 2; //candidate middle seat
                    int distance = mid - lastOccupiedSeat; //distance between middle seat and last occupied seat
                    maxDistance = Math.max(maxDistance, distance);
                }
                lastOccupiedSeat = i;
            }
        }
        int distanceFromLast = seats.length - 1 - lastOccupiedSeat; //distance between the last seat and last occupied seat
        maxDistance = Math.max(maxDistance, distanceFromLast);
        return maxDistance;
    }
}
