/**
 * https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
 * <p>
 * Given two positive integers n and k, the binary string  Sn is formed as follows:
 * <p>
 * S1 = "0"
 * Si = Si-1 + "1" + reverse(invert(Si-1)) for i > 1
 * Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).
 * <p>
 * For example, the first 4 strings in the above sequence are:
 * <p>
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 * <p>
 * Input: n = 3, k = 1
 * Output: "0"
 * Explanation: S3 is "0111001". The first bit is "0".
 */
public class FindKthBitInNthBinaryString {
    public char findKthBitRecursive(int n, int k) {
        if (n == 1) {
            return '0';
        }
        int total = (1 << n) - 1;
        int mid = total / 2;
        if (k - 1 == mid) { // mid is always 1
            return '1';
        } else if (k - 1 < mid) { // it's similar to going left side which is n - 1 value
            return findKthBitRecursive(n - 1, k);
        } else {
            int offset = (k - 1) - mid; // how far from mid
            //if k would have been 0 based, offset would be k - mid, but k is 1 based so need to subtract 1
            //since right half is reverse of left half, new index will be mid - offset
            //need to add 1 to the new k because k is 1 based
            //invert the result
            return findKthBitRecursive(n - 1, mid - offset + 1) == '1' ? '0' : '1';
        }
    }

    public char findKthBitBruteForce(int n, int k) {
        String[] dp = new String[21];
        dp[0] = "0";
        for (int i = 1; i < n; i++) {
            String prev = dp[i - 1];
            String plusOne = prev + "1";
            String inverted = invert(prev);
            String reversed = reverse(inverted);
            dp[i] = plusOne + reversed;
        }

        return dp[n - 1].charAt(k - 1);
    }

    private String reverse(String inverted) {
        StringBuilder res = new StringBuilder();
        for (int i = inverted.length() - 1; i >= 0; i--) {
            res.append(inverted.charAt(i));
        }
        return res.toString();
    }

    private String invert(String prev) {
        StringBuilder res = new StringBuilder();
        for (char c : prev.toCharArray()) {
            if (c == '1') {
                res.append('0');
            } else {
                res.append('1');
            }
        }
        return res.toString();
    }
}
