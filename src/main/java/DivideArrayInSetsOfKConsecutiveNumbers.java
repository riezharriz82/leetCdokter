import java.util.TreeMap;

/**
 * https://leetcode.com/problems/hand-of-straights/
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 * <p>
 * Alice has a hand of cards, given as an array of integers.
 * <p>
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 * <p>
 * Return true if and only if she can.
 * <p>
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * <p>
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {
    /**
     * Approach: Using TreeMap is important, allows us to give the smallest number in log(n) and find in O(1) and remove in log(n)
     * Using PriorityQueue will increase the time complexity of remove to O(n)
     * Remember to use TreeMap in place of priority queue where find and remove are required
     * <p>
     * Another approach would be to maintain a hashmap of key -> frequency and then sort the input array.
     * Use the sorted array to traverse in an ordered manner, just like we are leveraging firstKey() of treeMap to traverse in sorted manner.
     * <p>
     * {@link DesignALeaderboard}
     */
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : hand) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }

        while (treeMap.size() > 0) {
            int key = treeMap.firstKey(); //get the smallest element
            for (int i = key; i < key + W; i++) { //check if k consecutive numbers are present in the map
                if (!treeMap.containsKey(i)) { //contains is log(n)
                    return false;
                }
                int val = treeMap.get(i);
                if (val - 1 == 0) {
                    treeMap.remove(i); //PS: can't remove from a map while iterating over the keyset / entryset
                } else {
                    treeMap.put(i, val - 1);
                }
            }
        }

        return true;
    }
}
