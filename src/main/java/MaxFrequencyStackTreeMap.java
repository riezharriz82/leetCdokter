import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/maximum-frequency-stack/
 * <p>
 * For an even more optimized solution see {@link MaxFrequencyStackHashMap}
 */
public class MaxFrequencyStackTreeMap {
    Map<Integer, Integer> frequency = new HashMap<>(); //number -> frequency
    TreeMap<Integer, ArrayDeque<Integer>> frequencyStack = new TreeMap<>(); //frequency -> stack of numbers

    /**
     * Create a frequency map of key being pushed. Use that frequency to create a reverse mapping of frequency to key
     * That reverse mapping will help identify the key that is most frequently pushed.
     * <p>
     * Multiple keys can have the same frequency. Since we need to break tie based on the key which was recently pushed, we can create a
     * mapping of frequency to list of integers
     * <p>
     * Since we are interested only in the latest key inserted, we can use stack to simplify the operation ie. instead of list.get(list.size()-1)
     * it becomes list.pop()
     * <p>
     * Time complexity: O(logn) for both push() and pop()
     * <p>
     * {@link DesignALeaderboard}
     */
    public void push(int x) {
        //please note that x is present in multiple frequencies i.e. if x appears 10 times
        //it will be present in frequencyStack.get(1), frequencyStack.get(2), frequencyStack.get(3) ..... frequencyStack.get(10)
        frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        int currentFreq = frequency.get(x);
        frequencyStack.computeIfAbsent(currentFreq, __ -> new ArrayDeque<>()).push(x);
    }

    public int pop() {
        ArrayDeque<Integer> mostFrequentElements = frequencyStack.lastEntry().getValue();
        int latestMostFrequentElement = mostFrequentElements.pop();
        //housekeeping gotcha, decrement the frequency and remove from frequencyStack, if no elements belong to that frequency.
        frequency.put(latestMostFrequentElement, frequency.get(latestMostFrequentElement) - 1);
        frequency.remove(latestMostFrequentElement, 0); //remove if frequency of key is 0
        if (mostFrequentElements.isEmpty()) {
            frequencyStack.pollLastEntry();
        }
        return latestMostFrequentElement;
    }
}
