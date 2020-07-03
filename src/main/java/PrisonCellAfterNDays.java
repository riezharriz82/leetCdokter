import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/prison-cells-after-n-days/
 * <p>
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 * <p>
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 * <p>
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 * <p>
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 * <p>
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 * <p>
 * Example 1:
 * <p>
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * <p>
 * Note:
 * <p>
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 */
public class PrisonCellAfterNDays {
    /**
     * Here's a generalizable way to approach this problem w/o having to have a ton of insight:
     * <p>
     * there are 8 cells so there are 2 ^ 8 = 256 possible cell configurations
     * therefore, after 256 transformations you are guaranteed to observe a cycle by the pigeonhole principle: https://en.wikipedia.org/wiki/Pigeonhole_principle
     * In the future when you see questions where you have to make a large amount of state transitions over a state space
     * that seems small, just compare the size of the state space to the # of transitions to determine if there's a cycle
     * so you can bound complexity to the size of the state space.
     */
    public int[] prisonAfterNDays(int[] cells, int N) {
        Set<String> states = new HashSet<>();
        boolean cycleFound = false;
        for (int i = 0; i < N; i++) {
            int[] temp = getNextState(cells);
            String state = Arrays.toString(temp);
            if (!states.contains(state)) {
                //new state found
                states.add(state);
            } else {
                //cycle found, now states will start to repeat
                cycleFound = true;
                break;
            }
            cells = temp;
        }
        if (cycleFound) {
            int size = states.size();
            //shorten the iterations
            N %= size;
            for (int i = 0; i < N; i++) {
                cells = getNextState(cells);
            }
        }
        return cells;
    }

    private int[] getNextState(int[] cells) {
        int[] temp = new int[cells.length];
        for (int j = 1; j < cells.length - 1; j++) {
            if (cells[j - 1] == cells[j + 1]) {
                temp[j] = 1;
            } else {
                temp[j] = 0;
            }
        }
        return temp;
    }
}
