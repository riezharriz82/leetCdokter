import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/asteroid-collision/
 * <p>
 * We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right,
 * negative meaning left). Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * <p>
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * <p>
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 */
public class AsteroidCollision {
    /**
     * Approach: Similar to {@link LastStoneWeight}
     * Push elements on the stack, in case of collision (previous asteroid going right, current going left), if previous is bigger, do nothing.
     * otherwise need to inspect elements of the stack smaller than current element and pop them out
     */
    public int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        while (index < asteroids.length) {
            while (true) { //because we need to repeatedly pop out elements until no collision
                if (stack.isEmpty()) {
                    stack.push(asteroids[index]);
                    index++;
                    break;
                }
                boolean canCollide = (asteroids[index] < 0) && (stack.peek() > 0);
                if (canCollide) {
                    int previous = stack.pop();
                    if (Math.abs(previous) > Math.abs(asteroids[index])) {
                        //if previous is bigger, pop it back in and skip the current element
                        stack.push(previous);
                        index++;
                        break;
                    }
                    //else don't push the previous element back in
                } else {
                    //Just push the current element to the stack
                    stack.push(asteroids[index]);
                    index++;
                    break;
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
