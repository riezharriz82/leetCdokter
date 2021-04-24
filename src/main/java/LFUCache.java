import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/
 * <pre>
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
 *
 * When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item.
 * For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 *
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
 * The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[3,4], cnt(4)=2, cnt(3)=3
 *
 * Constraints:
 * 0 <= capacity, key, value <= 10^4
 * At most 10^5 calls will be made to get and put.
 * </pre>
 * Follow up: Could you do both operations in O(1) time complexity?
 */
public class LFUCache {
    /**
     * Approach: HashMap + DLL, Implementation of paper http://dhruvbird.com/lfu.pdf
     * Maintain a hashmap that points to a node in DLL. The nodes in DLL will maintain the frequency and the nodes/keys which have the
     * corresponding frequency.
     * <pre>
     * head <----> frequency1 <-----> frequency3 <-----> frequency4 <-----> tail
     *                |                   |
     *                5                   1
     *                |                   |
     *                6                   3
     *                |
     *               10
     *
     * This DLL indicates that key 5,6,10 have frequency of 1 and key 1,3 have frequency of 3
     * Since we have to maintain the recency order for a specific frequency, need to use a data structure that preserves insertion order
     * Also data structure should delete deletion in O(1) time because we need to shuffle nodes across when it's frequency increases.
     * Doubly linked list and LinkedHashSet comes into mind.
     * Using LinkedHashSet would be easier because we don't have to implement deletion ourselves.
     *
     * If we get(5), then we need to bump the frequency of 5 to 2. How do we do that?
     * We check the right neighbour of 5's parent ie. we check the right of 1. If it's 2, then we add 5 to 2.
     * If it's not 2, we have to create a new node with frequency 2 and insert 5 there.
     *
     * When the capacity is full and we need to delete nodes, we just check the right of head. That node will have the smallest frequency.
     * After that we find the first element present in the chain and delete it. This is why preserving insertion order was important.
     *
     * Remember to delete nodes in DLL which has no nodes, in order to keep the data structure in a consistent state.
     * </pre>
     * {@link LRUCache} {@link MaxFrequencyStackTreeMap} {Implement MultiLevel Cache @link experience.Uber.MachineCoding}
     */
    Map<Integer, Node> map = new HashMap<>();
    DLL head, tail; //always maintain dummy pointers in DLL whenever insertion/deletion needs to be performed
    int capacity;

    public LFUCache(int capacity) {
        head = new DLL();
        tail = new DLL();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) { //increment the frequency of the node by assigning it to correct DLL node
            Node node = map.get(key);
            DLL frequency = node.parent;
            int curFrequency = frequency.freq;
            if (frequency.next == tail || frequency.next.freq != curFrequency + 1) { //if next node in DLL != curFrequency + 1
                //add a new node
                addNode(frequency, curFrequency + 1);
            }
            //remove the node from current DLL and assign it to next node
            frequency.keys.remove(node);
            if (frequency.keys.isEmpty()) { //don't forget to remove DLL nodes with no keys assigned
                deleteNode(frequency);
            }
            node.parent = frequency.next;
            node.key = key;
            frequency.next.keys.add(node);
            return node.value;
        } else {
            return -1;
        }
    }

    private void deleteNode(DLL node) {
        DLL prev = node.prev;
        prev.next = node.next;
        node.next.prev = prev;
    }

    private void addNode(DLL node, int newFrequency) {
        DLL newNode = new DLL();
        newNode.next = node.next;
        node.next = newNode;
        newNode.next.prev = newNode;
        newNode.prev = node;
        newNode.freq = newFrequency;
    }

    public void put(int key, int value) {
        if (capacity == 0) { //edge case
            return;
        } else if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; //update the value
            //increment the frequency
            get(key);
        } else if (map.size() < capacity) {
            //new key found, insert a frequency of 1 if it does not exists in DLL
            Node node = new Node();
            if (head.next == tail || head.next.freq != 1) { //frequency of 1 is bound to be present on the next node of head.
                addNode(head, 1);
            }
            node.parent = head.next;
            head.next.keys.add(node);
            node.key = key;
            node.value = value;
            map.put(key, node); //insert the node's reference in the map
        } else {
            //new key found, can't insert because map is full, need to delete the key with lowest frequency and least recently accessed
            LinkedHashSet<Node> queue = head.next.keys;
            Iterator<Node> iterator = queue.iterator();
            Node lruNode = iterator.next();
            iterator.remove();
            if (queue.isEmpty()) {
                deleteNode(head.next); //don't forget to remove DLL nodes with no key's assigned
            }
            map.remove(lruNode.key);
            put(key, value); //recur to avoid duplicate code
        }
    }

    private static class Node {
        DLL parent; //Points to the node in DLL, required to check frequency of next neighbour.
        int value;
        int key; //required to delete the key from hashmap when deleting the node with least frequency and least recently used.
    }

    private static class DLL {
        int freq;
        DLL prev, next;
        LinkedHashSet<Node> keys = new LinkedHashSet<>(); //insertion order required to enforce LRU + set required to perform quick deletion at any index
        //alternatively a DLL could be used but it would have resulted in even more custom code
    }
}
