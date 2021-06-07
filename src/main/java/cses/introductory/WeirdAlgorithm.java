package cses.introductory;

import java.util.Scanner;

/**
 * <pre>
 * https://cses.fi/problemset/task/1068
 *
 * Consider an algorithm that takes as input a positive integer n. If n is even, the algorithm divides it by two, and if n is odd,
 * the algorithm multiplies it by three and adds one. The algorithm repeats this, until n is one. For example, the sequence for n=3 is as follows:
 * 3→10→5→16→8→4→2→1
 *
 * Your task is to simulate the execution of the algorithm for a given value of n.
 *
 * Input
 * The only input line contains an integer n.
 *
 * Output
 * Print a line that contains all values of n during the algorithm.
 *
 * Constraints
 * 1≤n≤10^6
 *
 * Input:
 * 3
 * Output:
 * 3 10 5 16 8 4 2 1
 * </pre>
 */
public class WeirdAlgorithm {
    /**
     * Approach: Simulation.
     * Gotchas: Use long to avoid overflow which in turn leads to TLE.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" ");
        while (n != 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = (n * 3) + 1;
            }
            sb.append(n).append(" ");
        }
        System.out.print(sb);
    }
}
