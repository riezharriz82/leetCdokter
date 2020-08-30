import java.util.*;

/**
 * https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
 * Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k, whether a Fibonacci number could be used multiple times.
 * <p>
 * The Fibonacci numbers are defined as:
 * <p>
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 , for n > 2.
 * It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.
 * <p>
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 */
public class MinimumNumberOfFibonacciRequiredToReachSumK {

    /**
     * Greedy approach: Find the biggest fibonacci number <= k, reduce k by that number and repeat the process
     * Use treeSet to find the floor of n
     */
    public int findMinimumNumberOfFibonacciGreedy(int k) {
        List<Integer> fibonacci = generateFibonacci(k);
        TreeSet<Integer> sortedFibonacci = new TreeSet<>(fibonacci);
        int n = k, hops = 0;
        while (n != 0) {
            int floor = sortedFibonacci.floor(n);
            n -= floor;
            hops++;
        }
        return hops;
    }

    /**
     * Since the DP approach was unfeasible, thought of creating a tree but stupid me, didn't thought of how many nodes could be present in such tree
     * Every node has 44 children (worst case), and can act like a complete binary tree. After just 5 levels, no of nodes increases by a huge number
     */
    public int findMinFibonacciNumbersTree(int k) {
        List<Integer> numbers = generateFibonacci(k);
        Map<Integer, Integer> map = new HashMap<>(); //key is the number and value is the min hops required to reach that number
        map.put(0, 0);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        int cnt = 1;
        while (true) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int head = queue.remove();
                for (int number : numbers) {
                    if (head + number <= k) {
                        int newDistance = map.get(head) + 1;
                        int curDistance = map.getOrDefault(head + number, Integer.MAX_VALUE);
                        if (newDistance < curDistance) {
                            map.put(head + number, newDistance);
                        }
                        queue.add(head + number);
                    }
                }
                if (map.containsKey(k)) {
                    return map.get(k);
                }
            }
        }
    }

    /**
     * Standard BFS search, due to large value of k, can't allocate the dp array
     * {@link PerfectSquares} for similar implementation
     */
    public int findMinFibonacciNumbers(int k) {
        List<Integer> numbers = generateFibonacci(k);
        int[] dp = new int[k + 1];
        for (int number : numbers) {
            dp[number] = 1;
        }
        if (dp[k] == 1) {
            return 1;
        }
        for (int i = 1; i <= k; i++) {
            if (dp[i] > 0) {
                for (int number : numbers) {
                    if (i + number <= k) {
                        dp[i + number] = Math.min(dp[i + number], 1 + dp[i]);
                    }
                }
            }
        }
        return dp[k];
    }

    private List<Integer> generateFibonacci(int k) {
        List<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(1);
        fibonacci.add(2);
        int candidate = 3;
        while (candidate <= k) {
            fibonacci.add(candidate);
            candidate = fibonacci.get(fibonacci.size() - 1) + fibonacci.get(fibonacci.size() - 2);
        }
        return fibonacci;
    }
}
