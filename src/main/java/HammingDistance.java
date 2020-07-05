/**
 * https://leetcode.com/problems/hamming-distance/
 * <p>
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * <p>
 * Given two integers x and y, calculate the Hamming distance.
 * <p>
 * Note:
 * 0 ≤ x, y < 231.
 * <p>
 * Example:
 * <p>
 * Input: x = 1, y = 4
 * <p>
 * Output: 2
 * <pre>
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * </pre>
 * The above arrows point to positions where the corresponding bits are different.
 */
public class HammingDistance {
    //Using inbuilt functions
    public int hammingDistanceInBuilt(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        //xor has only those bit sets which are different in x & y
        //need to find the count of the set bits in xor
        int cnt = 0;
        while (xor > 0) {
            cnt += xor & 1; //checks whether the last bit is set or not
            xor >>= 1;
        }
        return cnt;
    }
}
