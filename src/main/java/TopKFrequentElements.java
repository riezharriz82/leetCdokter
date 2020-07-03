import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * <p>
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */
public class TopKFrequentElements {
    public int[] topKFrequentUsingQuickSelect(int[] nums, int k) {
        //TODO
        return new int[10];
    }

    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, Comparator.comparingInt(Map.Entry::getValue));

        //another approach would be to use treemap where frequency is the key and the list<integer> acts as a key
        //this will keep the frequencies in order
        HashMap<Integer, Integer> map = new HashMap<>(); //track the count of occurrences of each value
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //a faster approach that can work if the size of nums array is small would be to create another list<list<integer> frequency
        // that stores the actual keys as values and the indexes would be the frequency
        // iterate from the end (largest frequencies) and collect the list<integer> present at that index into the result

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //Remove the smallest element if the queue is at maximum capacity and the head is smaller than the current element
            if (queue.size() < k) {
                queue.add(entry);
            } else {
                if (queue.peek().getValue() < entry.getValue()) {
                    queue.poll();
                    queue.add(entry);
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll().getKey();
        }
        return res;
    }
}
