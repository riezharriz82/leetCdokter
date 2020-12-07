import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/smallest-string-with-swaps/
 * <p>
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * <p>
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 * <p>
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 * <p>
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * <p>
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * <p>
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 */
public class SmallestStringWithSwaps {
    /**
     * Approach: Use Union-find to find connected components, each component can be sorted individually
     * e.g if abc are part of one component and xyz are part of another component and input string is czbyax
     * then we can sort each component individually ie. result will be axbycz
     * <p>
     * During my thought process, I tried a lot of greedy approaches ie sorted the entire array and see whether I can greedily
     * get each characters from the sorted sequence to their place from input string but couldn't solve it.
     * Also tried divide and conquer approach similar to finding inversion count but it didn't work because we can swap multiple times
     * <p>
     * {@link SmallestSubsequenceOfDistinctCharacters}
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1), parent);
        }
        List<PriorityQueue<Character>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) { //initialize with empty pq
            list.add(new PriorityQueue<>());
        }
        for (int i = 0; i < n; i++) { //for each index, find the idx of the parent and add the current character to parent idx
            int parentIdx = find(i, parent);
            list.get(parentIdx).add(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int parentIdx = find(i, parent); //find the parentIdx and get the smallest character of the component
            res.append(list.get(parentIdx).poll());
        }
        return res.toString();
    }

    private int find(int idx, int[] parent) {
        if (parent[idx] == idx) {
            return idx;
        } else {
            return parent[idx] = find(parent[idx], parent);
        }
    }

    private void union(int idx1, int idx2, int[] parent) {
        int root1 = find(idx1, parent);
        int root2 = find(idx2, parent);
        if (root1 != root2) {
            parent[root1] = root2;
        }
    }
}
