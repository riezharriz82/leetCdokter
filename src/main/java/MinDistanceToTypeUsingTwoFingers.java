import java.util.HashMap;

/**
 * https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/
 * <p>
 * You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate,
 * for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1),
 * the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).
 * <p>
 * Given the string word, return the minimum total distance to type such string using only two fingers.
 * The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|.
 * <p>
 * Note that the initial positions of your two fingers are considered free so don't count towards your total distance,
 * also your two fingers do not have to start at the first letter or the first two letters.
 * <p>
 * Input: word = "CAKE"
 * Output: 3
 * Explanation:
 * Using two fingers, one optimal way to type "CAKE" is:
 * Finger 1 on letter 'C' -> cost = 0
 * Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
 * Finger 2 on letter 'K' -> cost = 0
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
 * Total distance = 3
 */
public class MinDistanceToTypeUsingTwoFingers {

    /**
     * Approach: Solved using ghetto memoization technique, main issue was to find the recursion solution.
     * Initially I explored warshall floyd algorithm but found it not useful because we have two fingers.
     * <p>
     * For every character, we have two choices, move the first finger or move the second finger.
     * The trick to understand that each finger can be in a hover state i.e. unplaced state, so you have to consider two options
     * either place the finger at that character of if already placed, find the cost after moving that finger to that character
     */
    public int minimumDistance(String word) {
        HashMap<String, Integer> map = new HashMap<>();
        return recur(null, null, 0, word, map);
    }

    private int recur(Character finger1, Character finger2, int index, String word, HashMap<String, Integer> map) {
        if (index == word.length()) {
            return 0;
        }
        if (map.containsKey(finger1 + ":" + finger2 + ":" + index)) {
            return map.get(finger1 + ":" + finger2 + ":" + index);
        }
        int costAfterMovingFinger1 = Integer.MAX_VALUE, costAfterPlacingFinger1 = Integer.MAX_VALUE;
        if (finger1 == null) { //finger1 is unplaced
            costAfterPlacingFinger1 = recur(word.charAt(index), finger2, index + 1, word, map);
        } else {
            //get the cost of moving finger1 to index and recur for next character
            costAfterMovingFinger1 = getDistance(finger1, word.charAt(index)) + recur(word.charAt(index), finger2, index + 1, word, map);
        }
        //repeat the process for finger2
        int costAfterPlacingFinger2FirstTime = Integer.MAX_VALUE, costAfterMovingFinger2 = Integer.MAX_VALUE;
        if (finger2 == null) {
            costAfterPlacingFinger2FirstTime = recur(finger1, word.charAt(index), index + 1, word, map);
        } else {
            costAfterMovingFinger2 = getDistance(finger2, word.charAt(index)) + recur(finger1, word.charAt(index), index + 1, word, map);
        }
        int res = Math.min(costAfterMovingFinger1, Math.min(costAfterPlacingFinger1, Math.min(costAfterPlacingFinger2FirstTime, costAfterMovingFinger2)));
        map.put(finger1 + ":" + finger2 + ":" + index, res);
        return res;
    }

    private int getDistance(char source, char target) {
        return Math.abs((source - 'A') / 6 - (target - 'A') / 6) + Math.abs((source - 'A') % 6 - (target - 'A') % 6);
    }
}
