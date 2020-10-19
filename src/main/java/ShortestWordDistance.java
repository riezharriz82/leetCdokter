/**
 * https://leetcode.com/problems/shortest-word-distance/
 * <p>
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * <p>
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 */
public class ShortestWordDistance {
    /**
     * Approach: Similar to Google Kickstart {@link alternate.KickStart}
     * <p>
     * Keep track of occurrences of word1 and word2, whenever both indices are present and updated, try to update the minDistance
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int word1_index = -1, word2_index = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                word1_index = i;
                if (word2_index != -1) {
                    minDistance = Math.min(minDistance, Math.abs(word1_index - word2_index));
                }
            } else if (words[i].equals(word2)) {
                word2_index = i;
                if (word1_index != -1) {
                    minDistance = Math.min(minDistance, Math.abs(word1_index - word2_index));
                }
            }
        }
        return minDistance;
    }
}
