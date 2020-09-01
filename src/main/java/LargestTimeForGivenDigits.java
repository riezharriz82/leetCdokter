import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/largest-time-for-given-digits/
 * <p>
 * Given an array of 4 digits, return the largest 24 hour time that can be made.
 * <p>
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
 * <p>
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 * <p>
 * Input: [1,2,3,4]
 * Output: "23:41"
 * <p>
 * Input: [2,0,6,6]
 * Output: "06:26"
 */
public class LargestTimeForGivenDigits {
    /**
     * Approach: Recursion with backtracking
     * Initially I tried greedy approach, in which I fixed the largest possible digit at first place and then moved on to other digits
     * But it led to wrong answer in {2,0,6,6} because greedy will fix hour at 20 and then 3rd digit would not be possible
     * Then I moved on to backtracking, try to place the larger digit, if no result possible, move on to other candidates
     * So the backtracking solution will try to generate 06:26 solution
     */
    public String largestTimeFromDigits(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int digit : A) {
            map.put(digit, map.getOrDefault(digit, 0) + 1);
        }
        int[] maxCandidate = new int[]{2, 9, 0, 5, 9}; //max number possible for each digit
        //for 2nd digit, max number can be 3 if the first digit is 2, else max number can be 9
        //3rd digit can be skipped due to :
        return recur(maxCandidate, new char[5], 0, map);
    }

    private String recur(int[] candidates, char[] curResult, int curIndex, Map<Integer, Integer> map) {
        if (curIndex == 5) { //all 5 digits have been filled, match found
            return new String(curResult);
        }
        if (curIndex == 2) { //handle output format
            curResult[curIndex] = ':';
            return recur(candidates, curResult, curIndex + 1, map);
        }
        int curCandidate = candidates[curIndex];
        if (curIndex == 1 && curResult[0] == '2') { //handle special case for second digit
            curCandidate = 3;
        }
        while (curCandidate >= 0) {
            if (map.getOrDefault(curCandidate, 0) > 0) {
                //if candidate is present in input, try to place it and recurse
                int count = map.get(curCandidate);
                map.put(curCandidate, count - 1);
                curResult[curIndex] = (char) (curCandidate + '0'); //place the candidate
                String result = recur(candidates, curResult, curIndex + 1, map);
                if (!result.isEmpty()) {
                    //no match was possible
                    return result;
                } else {
                    //backtrack
                    map.put(curCandidate, count + 1);
                }
            }
            //try other candidate at this digit
            curCandidate--;
        }
        //all possible candidates were tried, still no match was found
        return "";
    }
}
