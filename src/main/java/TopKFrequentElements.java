import java.util.*;

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

    /**
     * {@link HIndex} for bucket sort related problem
     * Whenever you are asked to deal with count of number, think about bucket sort
     */
    public int[] topKFrequentUsingBucketSort(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); //nums as key and their count as value
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        //buckets here signify how many times a number has been repeated, if a number has been repeated 6 times, it falls in 6th bucket
        //iterate from last bucket ie. most frequent bucket, check if it's not empty
        //and keep on adding elements present in that bucket to the result array.
        //keep in mind that we need only k top elements
        List<List<Integer>> buckets = new ArrayList<>(nums.length + 1);
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }
        map.forEach((num, count) -> {
            buckets.get(count).add(num); //add num to appropriate bucket
        });
        int[] res = new int[k];
        int index = 0;
        for (int i = nums.length; i >= 0; i--) { //iterate from most frequent bucket
            if (!buckets.get(i).isEmpty()) {
                Iterator<Integer> iter = buckets.get(i).iterator();
                while (index < k && iter.hasNext()) { //add elements keeping in mind the constraint k
                    res[index++] = iter.next();
                }
            }
        }
        return res;
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
