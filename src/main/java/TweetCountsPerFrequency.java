import java.util.*;

/**
 * https://leetcode.com/problems/tweet-counts-per-frequency/
 * <p>
 * Implement the TweetCounts class:
 * <p>
 * TweetCounts() initializes the object.
 * void recordTweet(String tweetName, int time) stores the tweetName at the recorded time (in seconds).
 * List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) returns the total number of occurrences
 * for the given tweetName per minute, hour, or day (depending on freq) starting from the startTime (in seconds) and ending at the endTime (in seconds).
 * freq is always minute, hour, or day, representing the time interval to get the total number of occurrences for the given tweetName.
 * The first time interval always starts from the startTime, so the time intervals are
 * [startTime, startTime + delta*1>, [startTime + delta*1, startTime + delta*2>, [startTime + delta*2, startTime + delta*3>, ... ,
 * ........, [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)> for some non-negative number i and delta (which depends on freq).
 * <p>
 * Input
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 * <p>
 * Output
 * [null,null,null,null,[2],[2,1],null,[4]]
 * <p>
 * Explanation
 * TweetCounts tweetCounts = new TweetCounts();
 * tweetCounts.recordTweet("tweet3", 0);
 * tweetCounts.recordTweet("tweet3", 60);
 * tweetCounts.recordTweet("tweet3", 10);                             // All tweets correspond to "tweet3" with recorded times at 0, 10 and 60.
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
 * tweetCounts.recordTweet("tweet3", 120);                            // All tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211> - > 4 tweets.
 * <p>
 * Constraints:
 * There will be at most 10000 operations considering both recordTweet and getTweetCountsPerFrequency.
 * 0 <= time, startTime, endTime <= 109
 * 0 <= endTime - startTime <= 104
 */
public class TweetCountsPerFrequency {
    /**
     * Approach: Maintain a mapping for tweetName to the frequencies associated with each timeStamp.
     * <p>
     * Twitter PhoneScreen Interview question
     * <p>
     * {@link DesignTwitter} {@link AdvantageShuffle}
     */
    Map<String, TreeMap<Integer, Integer>> map = new HashMap<>(); //tweetName -> {{timeStamp -> frequency}, {timeStamp -> frequency}...}

    public TweetCountsPerFrequency() {

    }

    public void recordTweet(String tweetName, int time) {
        TreeMap<Integer, Integer> times = map.computeIfAbsent(tweetName, __ -> new TreeMap<>());
        times.put(time, times.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> result = new ArrayList<>();
        TreeMap<Integer, Integer> times = map.getOrDefault(tweetName, new TreeMap<>());
        if (times.isEmpty()) {
            return result;
        } else {
            int delta;
            //In Twitter interview I created a enum for representing granularity which contains offset as an attribute
            if (freq.equals("minute")) {
                delta = 60;
            } else if (freq.equals("hour")) {
                delta = 60 * 60;
            } else {
                delta = 60 * 60 * 24;
            }
            int temp = startTime;
            while (temp <= endTime) {
                int total = 0;
                if (temp == endTime) {
                    //subMap() returns empty map when start and end key are same, similar to substring()
                    //if condition can be avoided by using Math.min(temp + delta, endTime + 1)
                    //this +1 will ensure correct handling of terminal edge cases e.g {60->1}
                    //subMap(60, min(60+60, 60+1)) => subMap(60, 61) => {60->1}
                    total += times.getOrDefault(temp, 0);
                } else {
                    SortedMap<Integer, Integer> subMap = times.subMap(temp, Math.min(temp + delta, endTime));
                    for (Integer count : subMap.values()) {
                        total += count;
                    }
                }
                result.add(total);
                temp += delta;
            }
        }

        return result;
    }
}
