/**
 * https://leetcode.com/problems/number-complement/
 * <p>
 * Given a positive integer num, output its complement number. The complement strategy is to flip the bits of its binary representation.
 * <p>
 * Input: num = 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 * <p>
 * Input: num = 1
 * Output: 0
 * Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 */
public class TwosComplement {
    /**
     * Approach: Number + Two's complement = Number with all bits set (111111) 3, 7, 15, 31.
     * So for [4,7] the target number would sum up to 7.
     * For [8,15] the target number would sum up to 15
     * <p>
     * Once we find out the least number with all bits set > current number, two's complement will be the difference.
     */
    public int findComplement(int num) {
        int x = 1;
        while (num > x) { //need to find the first number greater than num with all bits set
            x = x * 2 + 1;
        }
        return x - num;
    }

    /**
     * Simple
     * Problem is similar to https://leetcode.com/problems/complement-of-base-10-integer/
     */
    public int bitwiseComplement(int N) {
        String binary = Integer.toBinaryString(N); //shortcut to create a binary string
        char[] chars = binary.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = chars[i] == '1' ? '0' : '1';
        }
        return Integer.parseUnsignedInt(new String(chars), 2); //shortcut to create a number from binary string
    }
}
