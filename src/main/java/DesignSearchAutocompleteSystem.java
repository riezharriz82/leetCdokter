import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/design-search-autocomplete-system/
 * <p>
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 * For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 * Here are the specific rules:
 * <p>
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot,
 * you need to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Your job is to implement the following functions:
 * <p>
 * The constructor function:
 * <p>
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data.
 * Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed.
 * Your system should record these historical data.
 * <p>
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 * <p>
 * List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'),
 * blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system.
 * The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 * <p>
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 * <p>
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree.
 * Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman".
 * Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * <p>
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 * <p>
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 * <p>
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 */
public class DesignSearchAutocompleteSystem {
    Trie root = new Trie();
    String currentSentence = "";

    /**
     * Approach: Store useful information directly in trie nodes ie. the entire sentence or the information problem is asking for.
     * Here it's asking for the hottest sentences starting with a prefix.
     * We can always do a graph traversal from the prefix, to find all the valid sentences and then get the top 3 hottest sentences but it's a lot of repetitive work
     * performed at each node
     * ie. if the sentences are "apple", "apple sucks" and the prefix is "a", first graph traversal will lead "apple" and "apple sucks"
     * now if the prefix is "ap", you have to do a graph traversal again to find valid sentences
     * <p>
     * Instead an optimized solution would be to store the hot sentences directly in trie nodes
     * <p>
     * I went with the first approach and preformed DFS for each input char in my initial solution and it was slow
     * <p>
     * {@link StreamChecker} {@link WordSearch2} for related hard trie problems
     */
    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            insertIntoTrie(sentences[i], times[i]);
        }
    }

    private void insertIntoTrie(String sentence, int times) {
        Trie temp = root;
        for (char c : sentence.toCharArray()) {
            if (temp.letters[c] == null) {
                temp.letters[c] = new Trie();
            }
            temp = temp.letters[c];
            //directly store the prefix information at this node
            temp.hotnessOfSentences.put(sentence, temp.hotnessOfSentences.getOrDefault(sentence, 0) + times);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            //store the current sentence
            insertIntoTrie(currentSentence, 1);
            currentSentence = "";
            return new ArrayList<>();
        } else {
            currentSentence += c;
            return getAllSentencesStartingWith(currentSentence);
        }
    }

    private List<String> getAllSentencesStartingWith(String prefix) {
        Trie temp = root;
        for (char c : prefix.toCharArray()) {
            if (temp.letters[c] == null) {
                return new ArrayList<>();
            }
            temp = temp.letters[c];
        }
        //pq of fixed size == 3 and is of increasing hotness
        //logic similar to find top k elements in array
        PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                return o2.getKey().compareTo(o1.getKey()); //store the lexicographically greater sentences first so they can be popped first
            } else {
                return Integer.compare(o1.getValue(), o2.getValue()); //store the sentences with min hotness first so they can be popped first
            }
        });
        for (Map.Entry<String, Integer> entry : temp.hotnessOfSentences.entrySet()) {
            int hotness = entry.getValue();
            String sentence = entry.getKey();
            if (pq.size() < 3) {
                pq.add(new Pair<>(sentence, hotness));
            } else if (pq.peek().getValue() < hotness) {
                //if the smallest hotness present in the pq is less than hotness of current sentence, need to make space in pq for current sentence
                pq.poll();
                pq.add(new Pair<>(sentence, hotness));
            } else if (pq.peek().getValue() == hotness && pq.peek().getKey().compareTo(sentence) > 0) {
                //if the sentence at the front of the pq has the same hotness but is lexicographically greater than current sentence, pop it
                pq.poll();
                pq.add(new Pair<>(sentence, hotness));
            }
        }
        ArrayList<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().getKey());
        }
        Collections.reverse(result); //pq returns sentences in increasing hotness, but we need to return them in order of decreasing hotness
        return result;
    }

    private static class Trie {
        Map<String, Integer> hotnessOfSentences = new HashMap<>(); //to avoid performing DFS at each node to extract out hot sentences for this prefix
        Trie[] letters = new Trie[128];
    }
}
