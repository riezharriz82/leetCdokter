import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-frequency-stack/
 * <p>
 * An simplified solution to {@link MaxFrequencyStack}
 */
public class MaxFrequencyStackHashMap {
    Map<Integer, Integer> frequency = new HashMap<>(); //number -> frequency
    Map<Integer, ArrayDeque<Integer>> frequencyStack = new HashMap<>(); //frequency -> stack of numbers
    int maxFrequency = 0;

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
     * Time Complexity: O(1) for both push() and pop()
     */
    public void push(int x) {
        //please note that x is present in multiple frequencies i.e. if x appears 10 times
        //it will be present in frequencyStack.get(1), frequencyStack.get(2), frequencyStack.get(3) ..... frequencyStack.get(10)
        frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        int currentFreq = frequency.get(x);
        frequencyStack.computeIfAbsent(currentFreq, __ -> new ArrayDeque<>()).push(x);
        maxFrequency = Math.max(maxFrequency, currentFreq);
    }

    public int pop() {
        ArrayDeque<Integer> mostFrequentElements = frequencyStack.get(maxFrequency); // problem statement guarantees that there will be at least one element before calling pop()
        int latestMostFrequentElement = mostFrequentElements.pop();
        //housekeeping gotcha, decrement the frequency and remove from frequencyStack, if no elements belong to that frequency.
        frequency.put(latestMostFrequentElement, frequency.get(latestMostFrequentElement) - 1);
        if (mostFrequentElements.isEmpty()) {
            //this is because if an element occurs 10 times, then it must have occurred 9 times, 8 times... 1
            //maxFrequency is a continuous function
            maxFrequency--;
        }
        return latestMostFrequentElement;
    }
}
