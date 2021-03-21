package experience.meesho;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.locks.ReentrantLock;

public class Typeahead {
    Trie root = new Trie();
    int maxResultSize = 10;

    /**
     * Provide a HLD and LLD for Typeahead system that provides auto suggestion based on previous user searches
     * e.g. if user has searched for "silk saree" and "saree", if user types "s", show suggestions that starts with "si"
     * Only prefix matching is required. The system should support feedback loop i.e. new searches should update the suggestions as well.
     * <p>
     * During the HLD part, I tried to solve this problem using Redis as persistent storage and in-memory trie for auto suggestions
     * But then the interviewer asked how to handle inconsistency between Redis and in-memory trie. I mentioned another service will keep them in
     * sync. Then we have to ensure sharding of trie nodes to ensure high availability.
     * <p>
     * Interviewer mentioned that it's going in a complicated direction. So I proposed inverted indexes in Redis.
     * e.g for saree, store s->{saree}, sa->{saree}, sar->{saree}, sare->{saree}, saree->{saree}
     * For each key maintain a sorted set based on frequency. Interviewer seems to be happy with this solution.
     * <p>
     * Asked to write a thread safe code for trie that supports update() and get().
     * <p>
     * After interview, discussed with Priyansh who suggested to maintain a heap for each key
     * <p>
     * {@link DesignSearchAutocompleteSystem} {@link MaxFrequencyStack}
     */
    public static void main(String[] args) {
        Typeahead typeahead = new Typeahead();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> typeahead.updatePopularity("saree", 10)).start();
        }
        System.out.println(typeahead.get("s"));
        System.out.println(typeahead.get("sa"));
        typeahead.updatePopularity("silk", 20);
        System.out.println(typeahead.get("s"));
        System.out.println(typeahead.get("sa"));
    }

    public void updatePopularity(String query, int frequency) {
        Trie node = root;
        for (char c : query.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new Trie();
            }
            node = node.children[c - 'a'];
            try {
                //before updating, try to acquire a lock
                node.lock.lock();
                Integer curCount = node.frequency.get(query);
                if (curCount == null) {
                    node.frequency.put(query, frequency);
                    node.frequencyReverseMap.computeIfAbsent(frequency, __ -> new TreeSet<>()).add(query);
                } else {
                    node.frequency.put(query, curCount + frequency);
                    //remove previous frequency
                    Set<String> previousFrequency = node.frequencyReverseMap.get(curCount);
                    previousFrequency.remove(query);
                    //update new frequency
                    node.frequencyReverseMap.computeIfAbsent(curCount + frequency, __ -> new TreeSet<>()).add(query);
                }
            } finally {
                node.lock.unlock();
            }
        }
    }

    public List<String> get(String prefix) {
        Trie node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                //no results found
                return new ArrayList<>();
            }
            node = node.children[c - 'a'];
        }
        List<String> result = new ArrayList<>();
        int count = 0;
        for (Map.Entry<Integer, Set<String>> entry : node.frequencyReverseMap.entrySet()) {
            for (String query : entry.getValue()) {
                if (count > maxResultSize) {
                    //need to return only a fixed amount of suggestions
                    return result;
                }
                result.add(query);
                count++;
            }
        }
        return result;
    }

    private static class Trie {
        Trie[] children = new Trie[26];
        Map<String, Integer> frequency = new HashMap<>(); //map of string to frequency
        TreeMap<Integer, Set<String>> frequencyReverseMap = new TreeMap<>(Comparator.reverseOrder()); //descending order
        ReentrantLock lock = new ReentrantLock(); //ideally should have taken a read lock during read and write lock during write
    }
}
