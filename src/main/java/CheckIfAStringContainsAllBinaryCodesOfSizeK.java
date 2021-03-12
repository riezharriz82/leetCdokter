import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/
 * <p>
 * Given a binary string s and an integer k.
 * <p>
 * Return True if every binary code of length k is a substring of s. Otherwise, return False.
 * <p>
 * Input: s = "00110110", k = 2
 * Output: true
 * Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
 * <p>
 * Input: s = "00110", k = 2
 * Output: true
 * <p>
 * Input: s = "0110", k = 1
 * Output: true
 * Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.
 * <p>
 * Input: s = "0110", k = 2
 * Output: false
 * Explanation: The binary code "00" is of length 2 and doesn't exist in the array.
 * <p>
 * Input: s = "0000000001011100", k = 4
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s.length <= 5 * 10^5
 * s consists of 0's and 1's only.
 * 1 <= k <= 20
 */
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {
    /**
     * Approach: Generate all rolling hash using bitmask. Check if all hashes from [0, 2^k - 1] are present
     * Challenge was to generate a rolling mask of fixed size. This is done by &'ing with a mask with all ones
     * e.g. current mask is 101 and we need to roll to next mask, where next character is 1
     * We need to discard the left most digit 1, so if we left shift by 1, mask would be 1010 and then & with 111, mask would be 010
     * Here the crucial step is to perform & with a mask with all ones to discard any set bits which have fallen off
     * <p>
     * After that we simply perform | with current character, 010 | 1 = 011
     * <p>
     * Time: 8 ms
     * {@link ShortestPathVisitingAllNodes} related bitmasking problem
     */
    public boolean hasAllCodesBitmask(String s, int k) {
        if (k > s.length()) {
            return false;
        }
        boolean[] hashes = new boolean[1 << k];
        int hash = 0, mask = (1 << k) - 1;
        for (int i = 0; i < k; i++) {
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0'); //crucial part, check the comment above
        }
        hashes[hash] = true;
        for (int i = k; i < s.length(); i++) {
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');
            hashes[hash] = true;
        }
        //check whether all the required hashes are present or not
        for (boolean isPresent : hashes) {
            if (!isPresent) {
                return false;
            }
        }
        return true;
    }

    /**
     * Approach: Generate Rolling Hash of all substrings of size k and check whether no of unique substrings == 2^k
     * Since it's a binary string and k <= 20, max value of rolling hash in base 2 would be 2^20 which should fit in integer
     * <p>
     * Time ~50 ms
     * {@link RepeatedDNASequence} related rolling hash problems
     */
    public boolean hasAllCodesRollingHash(String s, int k) {
        if (k > s.length()) {
            return false;
        }
        Set<Integer> hashes = new HashSet<>();
        int hash = 0, base = 2;
        for (int i = 0; i < k; i++) {
            hash = base * hash + (s.charAt(i) - '0');
        }
        hashes.add(hash);
        int multiplier = (int) Math.pow(base, k);
        for (int i = k; i < s.length(); i++) {
            //generate rolling hash by discarding the most significant digit
            hash *= base;
            hash -= ((s.charAt(i - k) - '0') * multiplier);
            hash += (s.charAt(i) - '0');
            hashes.add(hash);
        }
        int expectedSize = 1 << k;
        return hashes.size() == expectedSize;
    }

    /**
     * Approach: Generate all substrings of size k in the string and check whether the no of unique substrings == 2^k
     * There are distinct 2^k binary codes of size k
     * <p>
     * Time ~150 ms
     * Initially I thought this solution should give memory limit exceeded but I was pleasantly surprised to see AC
     */
    public boolean hasAllCodesBruteForce(String s, int k) {
        if (k > s.length()) {
            return false;
        }
        Set<String> allSubstrings = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append(s.charAt(i));
        }
        allSubstrings.add(sb.toString());
        for (int i = k; i < s.length(); i++) {
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            allSubstrings.add(sb.toString());
        }
        int expectedSize = 1 << k;
        return allSubstrings.size() == expectedSize;
    }
}
