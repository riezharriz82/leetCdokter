/**
 * https://leetcode.com/problems/compare-version-numbers/
 * <p>
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * <p>
 * The . character does not represent a decimal point and is used to separate number sequences.
 * <p>
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * <p>
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 * <p>
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 * <p>
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
 * <p>
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"
 */
public class CompareVersionNumbers {
    /**
     * Although the problem statement never mentions that the input can fit inside an int, I assumed otherwise, which makes the code longer
     * Goal is to compare each version numerically rather than lexicographically.
     * Edge case is to remove leading zeroes before comparing the versions.
     */
    public int compareVersion(String version1, String version2) {
        String[] first = version1.split("\\.");
        String[] second = version2.split("\\.");
        for (int i = 0; i < Math.min(first.length, second.length); i++) {
            String trimmedFirst = trimLeadingZeroes(first[i]);
            String trimmedSecond = trimLeadingZeroes(second[i]);
            if (trimmedFirst.length() != trimmedSecond.length()) {
                //length mismatch
                return Integer.compare(trimmedFirst.length(), trimmedSecond.length());
            } else {
                for (int j = 0; j < trimmedFirst.length(); j++) {
                    if (trimmedFirst.charAt(j) != trimmedSecond.charAt(j)) {
                        //value mismatch
                        return Integer.compare(trimmedFirst.charAt(j), trimmedSecond.charAt(j));
                    }
                }
            }
        }
        //no mismatch so far, now only need to check if the trailing versions of the longer string are
        //1. all zeroes, then the two versions are equal
        //2. has a non-zero version, then that version is larger.
        int multiplier = -1;
        if (first.length > second.length) {
            String[] temp = first;
            first = second;
            second = temp;
            multiplier = 1; //indicates whether swap happened
        }
        for (int i = first.length; i < second.length; i++) {
            String trimmedSecond = trimLeadingZeroes(second[i]);
            if (trimmedSecond.length() > 0) {
                return multiplier;
                //if swap happened, then this means first version > second version
                //if swap didn't happen, then first version < second version
            }
        }
        //versions are equal
        return 0;
    }

    private String trimLeadingZeroes(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '0') {
                return input.substring(i);
            }
        }
        return "";
    }
}
