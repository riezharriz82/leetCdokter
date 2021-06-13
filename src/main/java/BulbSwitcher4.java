/**
 * https://leetcode.com/problems/bulb-switcher-iv/
 * <p>
 * There is a room with n bulbs, numbered from 0 to n-1, arranged in a row from left to right. Initially all the bulbs are turned off.
 * <p>
 * Your task is to obtain the configuration represented by target where target[i] is '1' if the i-th bulb is turned on and is '0' if it is turned off.
 * <p>
 * You have a switch to flip the state of the bulb, a flip operation is defined as follows:
 * <p>
 * Choose any bulb (index i) of your current configuration.
 * Flip each bulb from index i to n-1.
 * When any bulb is flipped it means that if it is 0 it changes to 1 and if it is 1 it changes to 0.
 * <p>
 * Return the minimum number of flips required to form target.
 * <p>
 * Input: target = "10111"
 * <p>
 * Output: 3
 * <p>
 * Explanation: Initial configuration "00000".
 * <p>
 * flip from the third bulb:  "00000" -> "00111"
 * <p>
 * flip from the first bulb:  "00111" -> "11000"
 * <p>
 * flip from the second bulb:  "11000" -> "10111"
 * <p>
 * We need at least 3 flip operations to form target.
 */
public class BulbSwitcher4 {
    /**
     * Approach: Start visualizing the problem from small sized input ie. 1 or 2 or 3 sized array
     * Initially all the bulbs are 0 and if the 1st char at target is 1, then it's mandatory to turn this bulb on
     * If the next bulb is also 1, then no need to perform any additional operation as it has been handled by previous operation
     * However if this bulb is 0, then we need to flip this and perform an additional operation.
     * keep repeating this for the remaining indices.
     * <p>
     * {@link MinimumNumberOfOperationsToReachTargetArray} {@link MergeTripletsToFormTargetTriplet}
     */
    public int minFlips(String target) {
        int res = 0;
        for (int i = 0; i < target.length(); i++) {
            if (i == 0 && target.charAt(i) == '1') {
                res++;
            } else if (i > 0) {
                if (target.charAt(i) != target.charAt(i - 1)) {
                    res++;
                }
            }
        }
        return res;
    }
}
