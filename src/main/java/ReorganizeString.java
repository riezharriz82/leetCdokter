import javafx.util.Pair;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/reorganize-string/
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 * <p>
 * If possible, output any possible result.  If not possible, return the empty string.
 * <p>
 * Input: S = "aab"
 * Output: "aba"
 * <p>
 * Input: S = "aaab"
 * Output: ""
 */
public class ReorganizeString {
    /**
     * Approach: Very similar to {@link TaskScheduler}
     */
    public String reorganizeString(String S) {
        //Max heap required to minimize holes
        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        map.forEach((k, v) -> pq.add(new Pair<>(k, v)));
        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) {
            Pair<Character, Integer> head = pq.poll(); //Fix one character
            result.append(head.getKey());
            Pair<Character, Integer> next;
            if (!pq.isEmpty()) { //check if different character available
                next = pq.poll();
                result.append(next.getKey());
            } else {
                //if no different character available, then this should be the last character, need to break
                // otherwise will add the same character again as an adjacent character
                break;
            }
            if (head.getValue() - 1 > 0) { //add back to heap if still remaining
                pq.add(new Pair<>(head.getKey(), head.getValue() - 1));
            }
            if (next.getValue() - 1 > 0) {
                pq.add(new Pair<>(next.getKey(), next.getValue() - 1));
            }
        }
        //check if the length matches, if yes, then we have placed all the characters
        return result.length() == S.length() ? result.toString() : "";
    }
}
