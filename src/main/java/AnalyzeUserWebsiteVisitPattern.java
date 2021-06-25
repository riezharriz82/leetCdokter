import javafx.util.Pair;

import java.util.*;

/**
 * <pre>
 * https://leetcode.com/problems/analyze-user-website-visit-pattern/ Premium
 *
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].
 *
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)
 *
 * Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.
 *
 * Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"]
 * Explanation:
 * The tuples in this example are:
 * ["joe", 1, "home"]
 * ["joe", 2, "about"]
 * ["joe", 3, "career"]
 * ["james", 4, "home"]
 * ["james", 5, "cart"]
 * ["james", 6, "maps"]
 * ["james", 7, "home"]
 * ["mary", 8, "home"]
 * ["mary", 9, "about"]
 * ["mary", 10, "career"]
 * The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 *
 *
 * Note:
 * 3 <= N = username.length = timestamp.length = website.length <= 50
 * 1 <= username[i].length <= 10
 * 0 <= timestamp[i] <= 10^9
 * 1 <= website[i].length <= 10
 * Both username[i] and website[i] contain only lowercase characters.
 * It is guaranteed that there is at least one user who visited at least 3 websites.
 * No user visits two websites at the same time.
 * </pre>
 */
public class AnalyzeUserWebsiteVisitPattern {
    /**
     * Approach: Ad-hoc problem, for each user, sort the websites based on timestamp. Generate all the 3-sequence website using n^3 time complexity.
     * Increment the counter of each unique 3sequence per user in a map
     */
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair<Integer, String>>> userToWebsiteMapping = new HashMap<>(); //user -> {timestamp, website}
        int n = username.length;
        for (int i = 0; i < n; i++) {
            userToWebsiteMapping.computeIfAbsent(username[i], __ -> new ArrayList<>()).add(new Pair<>(timestamp[i], website[i]));
        }
        for (List<Pair<Integer, String>> websites : userToWebsiteMapping.values()) {
            websites.sort((o1, o2) -> Integer.compare(o1.getKey(), o2.getKey())); //sort based on timestamp
        }
        Map<String, Integer> threeSequenceCounter = new HashMap<>();
        int maxVisit = 0;
        String result = "";
        for (List<Pair<Integer, String>> websites : userToWebsiteMapping.values()) {
            Set<String> threeSequences = new HashSet<>();
            //n^3 time complexity
            for (int i = 0; i <= websites.size() - 3; i++) {
                for (int j = i + 1; j <= websites.size() - 2; j++) {
                    for (int k = j + 1; k <= websites.size() - 1; k++) {
                        String t = websites.get(i).getValue() + ":" + websites.get(j).getValue() + ":" + websites.get(k).getValue();
                        threeSequences.add(t);
                    }
                }
            }
            //for each unique sequence per user, increment it's counter in the map
            for (String threeSequence : threeSequences) {
                int curCount = threeSequenceCounter.getOrDefault(threeSequence, 0);
                threeSequenceCounter.put(threeSequence, curCount + 1);
                if (curCount + 1 > maxVisit) {
                    maxVisit = curCount + 1;
                    result = threeSequence;
                } else if (curCount + 1 == maxVisit && threeSequence.compareTo(result) < 0) { //need to keep track of lexicographically smallest sequence
                    result = threeSequence;
                }
            }
        }
        String[] split = result.split(":");
        return List.of(split[0], split[1], split[2]);
    }
}
