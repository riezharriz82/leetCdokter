import java.util.*;

/**
 * https://leetcode.com/problems/open-the-lock/
 * <p>
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
 * <p>
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * <p>
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
 * <p>
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 * <p>
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 */
public class OpenTheLock {
    /**
     * Since the problem asks about finding the min no of steps required to reach target, BFS is the way to go
     * I thought about doing BFS from end to start instead of start to end but it does not really matter in this case.
     * TODO: Use bidirectional BFS to reduce time complexity
     * <p>
     * Gotcha: Similar to the gotcha in {@link RottingOranges}, since a state can be added by multiple adjacent nodes during enumeration,
     * mark the state as visited before adding to the queue instead after removing it from the queue.
     * This helps in reducing duplicate entries in the same level.
     */
    public int openLock(String[] deadends, String target) {
        ArrayDeque<String> queue = new ArrayDeque<>();
        HashSet<String> blocked = new HashSet<>(Arrays.asList(deadends));
        HashSet<String> visited = new HashSet<>();
        if (!blocked.contains("0000")) { //edge cases
            queue.add("0000");
            visited.add("0000");
            if (target.equals("0000")) {
                return 0;
            }
        }
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String head = queue.remove();
                List<String> nextStates = enumerate(head);
                for (String state : nextStates) {
                    if (state.equals(target)) {
                        return level;
                    }
                    if (!visited.contains(state) && !blocked.contains(state)) {
                        visited.add(state); //GOTCHA, mark visited before adding to the queue to avoid adding duplicates in the same level
                        //forgot to do this during my first implementation, quickly caught this and fixed it.
                        queue.add(state);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Return all possible next 8 steps from the current string by doing +1 and -1 at each index
     */
    private List<String> enumerate(String initial) {
        List<String> states = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char[] chars = initial.toCharArray();
            if (chars[i] == '9') { //+1
                chars[i] = '0';
            } else {
                chars[i] += 1;
            }
            states.add(new String(chars));
            chars = initial.toCharArray();
            if (chars[i] == '0') { //-1
                chars[i] = '9';
            } else {
                chars[i] -= 1;
            }
            states.add(new String(chars));
        }
        return states;
    }
}
