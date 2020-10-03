import java.util.*;

/**
 * https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
 * <p>
 * Leetcode company workers use key-cards to unlock office doors. Each time a worker uses their key-card,
 * the security system saves the worker's name and the time when it was used.
 * The system emits an alert if any worker uses the key-card three or more times in a one-hour period.
 * <p>
 * You are given a list of strings keyName and keyTime where [keyName[i], keyTime[i]] corresponds to a person's name
 * and the time when their key-card was used in a single day.
 * <p>
 * Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
 * <p>
 * Return a list of unique worker names who received an alert for frequent keycard use. Sort the names in ascending order alphabetically.
 * <p>
 * Notice that "10:00" - "11:00" is considered to be within a one-hour period, while "23:51" - "00:10" is not considered to be within a one-hour period.
 * <p>
 * Input: keyName = ["daniel","daniel","daniel","luis","luis","luis","luis"], keyTime = ["10:00","10:40","11:00","09:00","11:00","13:00","15:00"]
 * Output: ["daniel"]
 * Explanation: "daniel" used the keycard 3 times in a one-hour period ("10:00","10:40", "11:00").
 */
public class TrackUsageInLastHour {
    /**
     * Approach: Sliding window. As the problem talks about keeping track of usage in the last 1 hour, sliding window fits perfectly.
     * <p>
     * Create a map of names with the sorted logged in time, keep increasing the window if the diff between first element and last element <= 60
     * Keep track of the current window count.
     */
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            int hour = Integer.parseInt(keyTime[i].substring(0, 2));
            int min = Integer.parseInt(keyTime[i].substring(3));
            int timeInMinute = 60 * hour + min;
            map.computeIfAbsent(keyName[i], __ -> new ArrayList<>()).add(timeInMinute);
        }
        List<String> flagged = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            List<Integer> times = entry.getValue();
            Collections.sort(times);
            int begin = 0, end = 0;
            while (end < times.size()) {
                int diff = times.get(end) - times.get(begin);
                if (diff <= 60 && diff >= 0) { //diff >= 0 to avoid wrapping around, because we are not interested in cases like {23:58, 23:59, 00:01}
                    if (end - begin + 1 == 3) { //keep increasing the window until the window is valid
                        flagged.add(entry.getKey());
                        break;
                    }
                    end++;
                } else {
                    begin++;
                }
            }
        }
        Collections.sort(flagged); //to match output
        return flagged;
    }
}
