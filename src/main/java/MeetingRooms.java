import java.util.TreeMap;

/**
 * https://leetcode.com/problems/meeting-rooms/ Premium
 * <p>
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 * <p>
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * <p>
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 */
public class MeetingRooms {
    /**
     * Approach: Exactly similar to {@link MyCalendar1}.
     * Find the event starting just before the current interval ends and check whether it ends before the current interval starts
     * If there is a conflict, return false
     */
    public boolean canAttendMeetings(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>(); //map of start time, end time
        for (int i = 0; i < intervals.length; i++) {
            Integer lowerStartTime = map.lowerKey(intervals[i][1]);
            if (lowerStartTime != null && map.get(lowerStartTime) > intervals[i][0]) {
                //if the event starting before the current intervals ends, ends after the current interval starts, there is an conflict
                return false;
            }
            map.put(intervals[i][0], intervals[i][1]);
        }
        return true;
    }
}
