import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-i/
 * <p>
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 * <p>
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
 * the range of real numbers x such that start <= x < end.
 * <p>
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
 * <p>
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without
 * causing a double booking. Otherwise, return false and do not add the event to the calendar.
 * <p>
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * <p>
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another event.
 * The third event can be booked, as the first event takes every time less than 20, but not including 20.
 */
public class MyCalendar1 {
    TreeMap<Integer, Integer> map = new TreeMap<>(); //key is start time, value is end time

    public MyCalendar1() {

    }

    /**
     * Approach: Keep the end time associated with the start time. Before booking an event, ensure that the first event starting before
     * the current event events should end before the current event starts
     * <p>
     * In my initial implementation, I implemented by incrementing +1 at start and -1 at end and by ensuring the first event present after start
     * either does not exist or only starts after current event has finished. It was an alright solution but this one is way simpler.
     */
    public boolean book(int start, int end) {
        Integer previousStart = map.lowerKey(end); //lookup by end time is important
        if (previousStart == null || map.get(previousStart) <= start) {
            map.put(start, end);
            return true;
        } else {
            return false;
        }
    }
}
