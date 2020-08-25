import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-latest-group-of-size-m/
 * <p>
 * Given an array arr that represents a permutation of numbers from 1 to n. You have a binary string of size n that initially has all its bits set to zero.
 * <p>
 * At each step i (assuming both the binary string and arr are 1-indexed) from 1 to n, the bit at position arr[i] is set to 1.
 * You are given an integer m and you need to find the latest step at which there exists a group of ones of length m.
 * A group of ones is a contiguous substring of 1s such that it cannot be extended in either direction.
 * <p>
 * Return the latest step at which there exists a group of ones of length exactly m. If no such group exists, return -1.
 * <p>
 * Input: arr = [3,5,1,2,4], m = 1
 * Output: 4
 * Explanation:
 * Step 1: "00100", groups: ["1"]
 * Step 2: "00101", groups: ["1", "1"]
 * Step 3: "10101", groups: ["1", "1", "1"]
 * Step 4: "11101", groups: ["111", "1"]
 * Step 5: "11111", groups: ["11111"]
 * The latest step at which there exists a group of size 1 is step 4.
 */
public class FindGroupWithSizeM {
    /**
     * Approach: This problem is asking to find whether a contiguous block of 1 with size m exists
     * So something like 11101111 has currently two sets {1,1,1} and {1,1,1,1}
     * If I flip 0 to 1, set size will change to 3 + 1 + 4
     * After every flip, we need to update the set size and keep track of counters of set sizes i.e how many sets of sizes 1, 2, 3, 4 .. occurs
     * If a set size of m exists, update the latest step
     * <p>
     * https://leetcode.com/problems/find-latest-group-of-size-m/discuss/806716/C%2B%2B-Union-Find-(Count-groups-of-size-Reverse-mapping)
     * https://cp-algorithms.com/data_structures/disjoint_set_union.html
     */
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        //Input is 1 based
        int[] parent = new int[n + 1]; //Required for Union find
        int[] rank = new int[n + 1]; //Required for Union find
        int[] countOfGroupSize = new int[n + 1]; //Required to count group sizes
        int[] bits = new int[n + 1]; //Represents actual input
        int[] groupSize = new int[n + 1]; //Required to represent current set size of parent
        Arrays.fill(groupSize, 1); //
        int res = -1; //represents the valid latest step
        for (int i = 1; i <= n; i++) {
            make_set(i, parent, rank); //Init union find
        }
        for (int i = 0; i < n; i++) {
            countOfGroupSize[1]++; //atleast a group of size 1 exists
            int bit = arr[i];
            bits[bit]++; //change the bit from 0 to 1
            if (bit - 1 >= 0 && bits[bit - 1] == 1) { // if adjacent left bit is 1 then need to perform union of left set with current set
                union_sets(bit, bit - 1, rank, parent, groupSize, countOfGroupSize);
            }
            if (bit + 1 <= n && bits[bit + 1] == 1) { //if adjacent right bit is 1 then need to perform union of right set with current set
                union_sets(bit, bit + 1, rank, parent, groupSize, countOfGroupSize);
            }
            if (countOfGroupSize[m] > 0) { //if a set of size m is present update the latest step
                res = i + 1;
            }
        }
        return res;
    }

    //standard boiler plate union find
    void make_set(int v, int[] parent, int[] rank) {
        parent[v] = v;
        rank[v] = 0;
    }

    //standard boiler plate
    int find_set(int v, int[] parent) {
        if (v == parent[v])
            return v;
        return parent[v] = find_set(parent[v], parent);
    }

    void union_sets(int a, int b, int[] rank, int[] parent, int[] setSize, int[] countOfSetSize) {
        a = find_set(a, parent);
        b = find_set(b, parent);
        if (a != b) { //if both sets have different parent, then they belong to different set
            //whatever the set size of a and b was, need to decrement the count of that set size, because that set size is going to change
            countOfSetSize[setSize[a]]--;
            countOfSetSize[setSize[b]]--;
            int newGroupSize = setSize[a] + setSize[b];
            countOfSetSize[newGroupSize]++; //new set size found, increment the counter
            if (rank[a] < rank[b]) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent[b] = a;
            setSize[a] = newGroupSize; //update the set size of the new parent
            if (rank[a] == rank[b]) {
                rank[a]++;
            }
        }
    }
}
