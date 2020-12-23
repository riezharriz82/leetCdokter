import java.util.*;

/**
 * https://leetcode.com/problems/sentence-similarity-ii/
 * <p>
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs,
 * determine if two sentences are similar.
 * <p>
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar,
 * if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 * <p>
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar,
 * then "great" and "fine" are similar.
 * <p>
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
 * <p>
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar,
 * even though there are no specified similar word pairs.
 * <p>
 * Finally, sentences can only be similar if they have the same number of words.
 * So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 * <p>
 * Note:
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class SentenceSimilarity2 {
    /**
     * Approach: Use union find on strings to find whether source and target string are part of the same component
     * It can also help when no of queries are too high, this will avoid performing repeated DFS on each word
     * <p>
     * To represent the parent array I have used Map<String, String>, another way would be to give an unique id to each
     * word and create an int[] for the union find
     * <p>
     * {@link AccountsMerge} {@link alternate.FriendGroups} {@link FindEventualSafeStates} related problems
     */
    public boolean areSentencesSimilarTwoUnionFind(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        HashMap<String, String> parent = new HashMap<>();
        for (List<String> pair : pairs) {
            parent.putIfAbsent(pair.get(0), pair.get(0));
            parent.putIfAbsent(pair.get(1), pair.get(1));
            union(pair.get(0), pair.get(1), parent);
        }
        for (int i = 0; i < words1.length; i++) {
            String root1 = find(words1[i], parent);
            String root2 = find(words2[i], parent);
            if (!root1.equals(root2)) {
                return false;
            }
        }
        return true;
    }

    private String find(String word, HashMap<String, String> parent) {
        if (!parent.containsKey(word)) {
            parent.put(word, word);
            return word;
        } else if (parent.get(word).equals(word)) {
            return word;
        } else {
            parent.put(word, find(parent.get(word), parent));
            return parent.get(word);
        }
    }

    private void union(String word1, String word2, HashMap<String, String> parent) {
        String root1 = find(word1, parent);
        String root2 = find(word2, parent);
        if (!root1.equals(root2)) {
            parent.put(root1, root2);
        }
    }

    /**
     * Approach: Create a graph and for each word in words1, traverse the graph to see if respective word in word2 can be reached or not
     */
    public boolean areSentencesSimilarTwoDFS(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> pair : pairs) {
            graph.computeIfAbsent(pair.get(0), __ -> new ArrayList<>()).add(pair.get(1)); //undirected graph
            graph.computeIfAbsent(pair.get(1), __ -> new ArrayList<>()).add(pair.get(0));
        }
        for (int i = 0; i < words1.length; i++) {
            String source = words1[i], target = words2[i];
            Set<String> visited = new HashSet<>();
            if (!DFS(graph, source, target, visited)) { //perform DFS from source to see if target can be reached or not
                return false;
            }
        }
        return true;
    }

    private boolean DFS(Map<String, List<String>> graph, String source, String target, Set<String> visited) {
        if (source.equals(target)) {
            return true;
        }
        visited.add(source);
        for (String similarWord : graph.getOrDefault(source, new ArrayList<>())) {
            if (!visited.contains(similarWord) && DFS(graph, similarWord, target, visited)) {
                return true;
            }
        }
        return false;
    }
}
