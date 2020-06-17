import java.util.Arrays;

/**
 * https://leetcode.com/problems/buddy-strings/
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 * <p>
 * Example 1:
 * <p>
 * Input: A = "ab", B = "ba"
 * Output: true
 * Example 2:
 * <p>
 * Input: A = "ab", B = "ab"
 * Output: false
 */
public class BuddyStrings {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        for (int i = 0; i < A.length(); i++) {
            char character = A.charAt(i);
            char toCompare = B.charAt(i);
            if (character != toCompare) {
                for (int j = i + 1; j < A.length(); j++) {
                    char newChar = A.charAt(j);
                    if (newChar == toCompare) {
                        char[] charsA = A.toCharArray();
                        charsA[i] = newChar;
                        charsA[j] = character;

                        char[] charsB = B.toCharArray();
                        return Arrays.equals(charsA, charsB);
                    }
                }
            }
        }
        //check for duplicate characters in input
        for (int i = 0; i < A.length() - 1; i++) {
            for (int j = i + 1; j < A.length(); j++) {
                if (A.charAt(i) == A.charAt(j) && B.charAt(i) == A.charAt(i) && B.charAt(j) == A.charAt(j)) {
                    //check whether same chars are present in b as well
                    return true;
                }
            }
        }
        return false;
    }
}
