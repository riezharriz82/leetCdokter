import java.util.Arrays;

public class BuddyStrings {
    /**
     * https://leetcode.com/problems/buddy-strings/
     */
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
