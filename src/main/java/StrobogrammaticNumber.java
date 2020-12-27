/**
 * https://leetcode.com/problems/strobogrammatic-number/
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * <p>
 * Input: num = "69"
 * Output: true
 * <p>
 * Input: num = "88"
 * Output: true
 * <p>
 * Input: num = "962"
 * Output: false
 * <p>
 * Input: num = "1"
 * Output: true
 */
public class StrobogrammaticNumber {
    /**
     * Approach: Only digits 0,1,6,8,9 can contribute to a strobogrammatic number, and they must exist in pair
     * {0,0}, {1,1}, {6,9}, {8,8}, {9,6}
     * So use two pointers just like detecting a palindrome
     */
    public boolean isStrobogrammatic(String num) {
        int low = 0, high = num.length() - 1;
        while (low <= high) {
            if (low == high) { //special case for the num in center, it must only be 0,1,8
                char ch = num.charAt(low);
                return ch == '0' || ch == '1' || ch == '8';
            } else {
                char front = num.charAt(low);
                char back = num.charAt(high);
                //check whether the pair of front, back is valid pair or not
                if ((front == '0' && back == '0') || (front == '1' && back == '1') || (front == '6' && back == '9')
                        || (front == '8' && back == '8') || (front == '9' && back == '6')) {
                    low++;
                    high--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
