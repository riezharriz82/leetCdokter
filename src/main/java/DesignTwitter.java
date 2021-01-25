import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/design-twitter/
 * <p>
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user
 * and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 * <p>
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users
 * who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * <p>
 * Twitter twitter = new Twitter();
 * <p>
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 * <p>
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 * <p>
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 * <p>
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 * <p>
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 * <p>
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 * <p>
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 */
public class DesignTwitter {
    Map<Integer, ArrayDeque<Pair<Integer, Integer>>> tweets = new HashMap<>(); //userId -> {timeStampId, tweetId}
    int timeStamp; //global timestamp
    Map<Integer, Set<Integer>> fans = new HashMap<>(); //userId is a fan of set of {userIds}

    /**
     * Approach: Since we need to display the top 10 tweets for a person which includes his tweets + tweets of the person he follows
     * there is no point in storing > 10 tweets per person. We simply iterate over all the person he follows, get their tweet
     * sort them by timestamp and get the 10 most recent ones. Use priority queue of size 10 to improve time/space complexity
     * <p>
     * Alternate way of solving this would be fan-out approach i.e in which we push the tweets to the news feed of the followers of
     * a person (push mechanism) rather than pulling the tweets (current implementation)
     * <p>
     * {@link DesignPhoneDirectory} {@link DesignALeaderboard} {@link DesignBrowserHistory} design problems
     */
    public DesignTwitter() {

    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, __ -> new ArrayDeque<>()).addLast(new Pair<>(timeStamp++, tweetId));
        ArrayDeque<Pair<Integer, Integer>> tweets = this.tweets.get(userId);
        if (tweets.size() > 10) { //keep only latest 10 tweets by maintaining a queue of max size of 10
            tweets.removeFirst();
        }
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user
     * followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        //pq of {timeStampId, tweetId}, sorted on timeStamp, fixed size of 10
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.getKey(), o2.getKey()));
        //push all the tweets of the person the user follows in the pq
        for (int followeeId : fans.getOrDefault(userId, new HashSet<>())) {
            ArrayDeque<Pair<Integer, Integer>> followeeTweets = tweets.getOrDefault(followeeId, new ArrayDeque<>());
            for (Pair<Integer, Integer> tweet : followeeTweets) {
                pq.add(tweet);
                if (pq.size() > 10) {
                    pq.poll();
                }
            }
        }
        //push all tweets of the person to the pq
        for (Pair<Integer, Integer> userTweet : tweets.getOrDefault(userId, new ArrayDeque<>())) {
            pq.add(userTweet);
            if (pq.size() > 10) {
                pq.poll();
            }
        }
        //get the 10 most recent tweets
        List<Integer> tweets = new ArrayList<>();
        while (!pq.isEmpty()) {
            tweets.add(pq.poll().getValue());
        }
        Collections.reverse(tweets); //reverse because pq is sorted on timestamp
        return tweets;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followeeId != followerId) {
            fans.computeIfAbsent(followerId, __ -> new HashSet<>()).add(followeeId);
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followeeId != followerId) {
            fans.getOrDefault(followerId, new HashSet<>()).remove(followeeId);
        }
    }
}
