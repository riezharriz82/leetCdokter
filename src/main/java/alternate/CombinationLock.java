package alternate;

import java.util.Scanner;

/**
 * https://codingcompetitions.withgoogle.com/kickstart/round/00000000001a0069/0000000000414a24#problem
 * <p>
 * A combination lock has W wheels, each of which has the integer values 1 through N on it, in ascending order.
 * <p>
 * At any moment, each wheel shows a specific value on it. Xi is the initial value shown on the i-th wheel.
 * <p>
 * You can use a single move to change a wheel from showing the value X to showing either X+1 or X-1, wrapping around between 1 and N.
 * For example, if a wheel currently shows the value 1, in one move you can change its value to 2 or N.
 * <p>
 * Given all wheels' initial values, what is the minimum number of moves to get all wheels to show the same value?
 * <p>
 * Input
 * The first line of the input gives the number of test cases, T. T test cases follow.
 * <p>
 * The first line of each test case contains the two integers W and N.
 * <p>
 * The second line contains W integers. The i-th integer is Xi.
 * <p>
 * Output
 * For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the minimum number of moves to get all wheels to show the same value.
 * Sample Input
 * 2
 * 3 5
 * 2 3 4
 * 4 10
 * 2 9 3 8
 * <p>
 * Output
 * Case #1: 2
 * Case #2: 8
 */
public class CombinationLock {
    /**
     * Approach: {@link OpenTheLock} is similar but the target state is defined there.
     * Here we don't have the target state defined,
     * Another related problem is {@link MinimumCostToHireKWorkers} where we choose each number as the smallest and then find the total cost
     * <p>
     * Here the minimum turns can be achieved after bringing the dial down to one of the initial states of the wheel,
     * so we consider each wheel as the final state and calculate the minimum turns possible
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int wheels = scanner.nextInt();
            int highestNumber = scanner.nextInt();
            int[] wheel = new int[wheels];
            for (int j = 0; j < wheels; j++) {
                wheel[j] = scanner.nextInt();
            }
            long minTurns = Long.MAX_VALUE; //should use long because int overflows
            for (int j = 0; j < wheel.length; j++) {
                long turns = 0; //turns required after setting each wheel to wheel[j]
                for (int k = 0; k < wheel.length; k++) {
                    int diff = Math.abs(wheel[k] - wheel[j]);
                    turns += Math.min(diff, highestNumber - diff); //another trick, either wrap around or simply wrap forward
                }
                minTurns = Math.min(minTurns, turns);
            }
            System.out.println("Case #" + (i + 1) + ": " + minTurns);
        }
    }
}
