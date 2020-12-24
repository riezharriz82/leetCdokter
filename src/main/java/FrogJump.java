import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/frog-jump/
 * <p>
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 * <p>
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 * <p>
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
 * <p>
 * Note:
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 * <p>
 * [0,1,3,5,6,8,12,17]
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 * Return true. The frog can jump to the last stone by jumping
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 * 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 * <p>
 * [0,1,2,3,4,8,9,11]
 * Return false. There is no way to jump to the last stone as
 * the gap between the 5th and 6th stone is too large.
 */
public class FrogJump {
    /**
     * Approach: Recursion with memoization, at each step try to recur to all the places it can jump to and see if we can reach the end
     * <p>
     * {@link LongestStringChain} {@link MaximumLengthOfPairChain} related problems
     */
    public boolean canCross(int[] stones) {
        //creates a mapping of stones value to the index, this is required to see if we can jump to a stone or land in water
        Map<Integer, Integer> map = new HashMap<>();
        Map<Pair<Integer, Integer>, Boolean> memo = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], i); //update the mapping with stones location
        }
        return recur(stones, 0, 0, map, memo);
    }

    private boolean recur(int[] stones, int index, int lastJump, Map<Integer, Integer> map, Map<Pair<Integer, Integer>, Boolean> memo) {
        if (index == stones.length - 1) {
            return true;
        }
        Pair<Integer, Integer> key = new Pair<>(index, lastJump);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int curStone = stones[index];
        for (int jump = lastJump - 1; jump <= lastJump + 1; jump++) { //try making all 3 jumps
            if (map.getOrDefault(curStone + jump, 0) > index) { //check if you can move forward or land on a stone
                if (recur(stones, map.get(curStone + jump), jump, map, memo)) { //if reached end, short circuit
                    memo.put(key, true);
                    return true;
                }
            }
        }
        memo.put(key, false); //unable to reach end
        return false;
    }
}
