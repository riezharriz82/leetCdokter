import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/sequential-digits/
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * <p>
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 * <p>
 * Input: low = 100, high = 300
 * Output: [123,234]
 * <p>
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 */
public class SequentialDigits {
    /**
     * Approach: As the problem statement talks about generating all the valid numbers in range, recursion comes into mind
     * We fix first digit and then recursively try to fix the next digit, keeping the current number in range of [low, high]
     * <p>
     * The critical thing to note is that digit 9 is banned because there is no possible digit after placing 9
     */
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 9; i++) { //9 is excluded
            DFS(list, low, high, i);
        }
        Collections.sort(list); //need to sort to match expected output
        return list;
    }

    private void DFS(List<Integer> list, int low, int high, int curNumber) {
        if (curNumber > high) { //out of bounds, can't reduce the current number
            return;
        }
        if (curNumber >= low && curNumber <= high) {
            list.add(curNumber);
        }
        //try to get a bigger number
        if (curNumber % 10 != 9) { //no next digit possible if the last digit is 9
            DFS(list, low, high, (curNumber * 10) + (curNumber % 10) + 1);
        }
    }
}
