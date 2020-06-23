import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/daily-temperatures/
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 * <p>
 * For example, given the list of temperatures T = <pre> [73, 74, 75, 71, 69, 72, 76, 73] </pre>,
 * your output should be <pre> [1, 1, 4, 2, 1, 1, 0, 0] </pre>
 */
public class NextGreaterElement {
    public int[] dailyTemperaturesUsingCustomStack(int[] T) {
        int[] stack = new int[T.length];
        int top = -1;
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (top > -1 && T[i] > T[stack[top]]) {
                res[stack[top]] = i - stack[top];
                top--;
            }
            stack[++top] = i;
        }
        return res;
    }

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];

        //ArrayDeque should be preferred over the Stack implementation as Stack is thread-safe
        Deque<Integer> stack = new ArrayDeque<>(); //store the indexes in the stack, not the actual value
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                Integer poppedIndex = stack.pop();
                res[poppedIndex] = i - poppedIndex;
            }
            stack.push(i);
        }

        return res;
    }
}
