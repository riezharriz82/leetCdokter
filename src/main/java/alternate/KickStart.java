package alternate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Ksenia is very fond of reading so she kicks off each day by reading a fragment from her favourite book before
 * starting with the rest of her morning routine. A fragment is simply a substring of the text.
 * Ksenia is somewhat superstitious and believes that her day will be lucky if the fragment she reads starts with the string KICK,
 * then goes on with 0 or more characters, and eventually ends with the string START, even if the overall fragment makes little sense.
 * <p>
 * Given the text of the book, count the number of different lucky fragments that Ksenia can read before the book gets old
 * and she needs to buy another one. Two fragments are considered to be different if they start or end at different positions in the text,
 * even if the fragments read the same. Also note that different lucky fragments may overlap.
 * <p>
 * Input
 * The first line of the input gives the number of test cases T. T lines follow, each containing a single string S consisting of upper case English letters only.
 * <p>
 * Output
 * For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the number of different
 * lucky fragments in the text of this test case.
 */
public class KickStart {
    /**
     * Approach: Count no of occurrences of KICK occurring before START
     * Could be optimized by keeping track of occurrences of KICK and incrementing the result by that number when START is first seen.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        for (int test = 0; test < t; test++) {
            String input = scanner.nextLine();
            List<Integer> kickIndices = new ArrayList<>(); //store all the occurrences of KICK
            List<Integer> startIndices = new ArrayList<>(); //all occurrences of START
            int searchFrom = 0;
            while (true) {
                int kick = input.indexOf("KICK", searchFrom);
                if (kick == -1) {
                    break;
                }
                kickIndices.add(kick);
                searchFrom = kick + 3; // not kick + 4 because of input like KICKICK
                //this mistake cost me successful submission in the contest
            }
            searchFrom = 0;
            while (true) {
                int start = input.indexOf("START", searchFrom);
                if (start == -1) {
                    break;
                }
                startIndices.add(start);
                searchFrom = start + 5;
            }
            int result = 0;
            for (int startIndex : startIndices) {
                int kickIndex = Collections.binarySearch(kickIndices, startIndex); //get first kickIndex > startIndex
                kickIndex *= -1;
                kickIndex -= 1;
                result += (kickIndex);
            }
            System.out.println("Case #" + (test + 1) + ": " + result);
        }
    }
}
