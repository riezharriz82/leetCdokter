/**
 * https://leetcode.com/problems/reverse-bits/
 * <p>
 * Reverse bits of a given 32 bits unsigned integer.
 * <p>
 * Input: 00000010100101000001111010011100
 * Output: 00111001011110000010100101000000
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
 * so return 964176192 which its binary representation is 00111001011110000010100101000000.
 */
public class ReverseBits {
    public int reverseBitsSimplified(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= (n & 1);
            n >>= 1;
        }
        return res;
    }

    public int reverseBits(int n) {
        long res = 0;
        int power = 31;
        while (n != 0 && power >= 0) { //both conditions are important
            int digit = n & 1;
            if (digit == 1) {
                res += Math.pow(2, power);
            }
            n >>= 1;
            power--;
        }
        return (int) res;
    }
}
