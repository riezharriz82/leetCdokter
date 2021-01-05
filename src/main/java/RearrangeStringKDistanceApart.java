import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/ Premium
 * <p>
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * <p>
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 * <p>
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least distance 3 from each other.
 * <p>
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 * <p>
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 */
public class RearrangeStringKDistanceApart {
    /**
     * Approach: Greedy, I tried to simulate the placement similar to {@link TaskScheduler} but got WA. On close inspection, I noticed
     * that my code in task scheduler is different as it only requires the min time required and not the string. Here we are more strict
     * as we need to generate the actual string.
     * <p>
     * Need to make sure that the character popped out of PQ is not placed before k characters are placed, we can ensure that we maintaining
     * another queue in which we add back the head of the queue back to pq only if the queue size is k. This ensures that we don't place characters
     * too early
     * <p>
     * {@link TaskScheduler} {@link ReorganizeString} {@link FindPermutation} related greedy problems
     */
    public String rearrangeString(String s, int k) {
        if (k == 0) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //priority queue based on decreasing frequency as the characters that occurs the most, should be placed first
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        map.forEach((key, value) -> {
            pq.add(new Pair<>(key, value));
        });
        ArrayDeque<Pair<Character, Integer>> waitingQueue = new ArrayDeque<>(); //queue that simulates the character that are waiting to get placed
        //this queue ensures that we don't place any character before k distance
        StringBuilder res = new StringBuilder();
        while (!pq.isEmpty()) {
            Pair<Character, Integer> head = pq.remove();
            res.append(head.getKey());
            Pair<Character, Integer> next = new Pair<>(head.getKey(), head.getValue() - 1);
            //decrement the frequency and add it to the waiting queue
            waitingQueue.add(next);
            if (waitingQueue.size() == k) { //if there are k elements waiting, this means the first element can be placed back in pq if frequency > 0
                Pair<Character, Integer> first = waitingQueue.removeFirst();
                if (first.getValue() > 0) {
                    pq.add(first); //important to add it back to the pq if count remaining
                }
            }
        }
        return res.length() == s.length() ? res.toString() : "";
    }
}
