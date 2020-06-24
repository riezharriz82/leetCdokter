import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/check-if-n-and-its-double-exist/
 * Given an array arr of integers, check if there exists two integers N and M such that N is the double of M ( i.e. N = 2 * M).
 * <p>
 * More formally check if there exists two indices i and j such that :
 * <p>
 * i != j
 * 0 <= i, j < arr.length
 * arr[i] == 2 * arr[j]
 * <p>
 * Input: arr = [10,2,5,3]
 * Output: true
 * Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
 */
public class CheckIfNAndItsDoubleExist {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int value : arr) {
            if (set.contains(2 * value) || (value % 2 == 0 && set.contains(value / 2))) {
                return true;
            }
            set.add(value);
        }
        return false;
    }
}
