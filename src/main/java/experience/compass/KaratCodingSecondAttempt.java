package experience.compass;

import java.util.ArrayList;
import java.util.List;

public class KaratCodingSecondAttempt {
    /*
    We are building a word processor and we would like to implement a "reflow" functionality that also applies full justification to the text.
    Given an array containing lines of text and a new maximum width, re-flow the text to fit the new width.
    Each line should have the exact specified width. If any line is too short, insert '-' (as stand-ins for spaces) between words as equally as possible until it fits.

    Note: we are using '-' instead of spaces between words to make testing and visual verification of the results easier.
    </pre>
    lines = [ "The day began as still as the",
              "night abruptly lighted with",
              "brilliant flame" ]

    reflowAndJustify(lines, 24) "reflow lines and justify to length 24" =>

            [ "The--day--began-as-still",
              "as--the--night--abruptly",
              "lighted--with--brilliant",
              "flame" ] // <--- a single word on a line is not padded with spaces

    reflowAndJustify(lines, 25) "reflow lines and justify to length 25" =>

            [ "The-day-began-as-still-as"
              "the-----night----abruptly"
              "lighted---with--brilliant"
              "flame" ]

    reflowAndJustify(lines, 26) "reflow lines and justify to length 26" =>

            [ "The--day-began-as-still-as",
              "the-night-abruptly-lighted",
              "with----brilliant----flame" ]

    reflowAndJustify(lines, 40) "reflow lines and justify to length 40" =>

            [ "The--day--began--as--still--as-the-night",
              "abruptly--lighted--with--brilliant-flame" ]

    reflowAndJustify(lines, 14) "reflow lines and justify to length 14" =>

            ['The--day-began',
             'as---still--as',
             'the------night',
             'abruptly',
             'lighted---with',
             'brilliant',
             'flame']

    n = number of words OR total characters
    </pre>
*/
    public static void main(String[] argv) {
        //This problem is exactly similar to TextJustification of LeetCode
        //I could not give correct output as I was stuck in filling the right amount of spaces between words
        String[] lines = {"The day began as still as the", "night abruptly lighted with", "brilliant flame"};
        int testReflowWidth1 = 24;
        int testReflowWidth2 = 25;
        int testReflowWidth3 = 26;
        int testReflowWidth4 = 40;
        int testReflowWidth5 = 14;
        System.out.println(centerJustify(lines, testReflowWidth1));
        System.out.println(centerJustify(lines, testReflowWidth2));
        System.out.println(centerJustify(lines, testReflowWidth3));
        System.out.println(centerJustify(lines, testReflowWidth4));
        System.out.println(centerJustify(lines, testReflowWidth5));
    }

    private static List<String> centerJustify(String[] lines, int reflowWidth) {
        List<String> leftJustified = wrapLines(lines, reflowWidth);
        List<String> result = new ArrayList<>();
        for (String line : leftJustified) {
            int extra = reflowWidth - line.length();
            System.out.println(extra + " space");
            String[] words = line.split("-");
            int spacePerWords = (words.length - 1) / extra;
            int remainder = (words.length - 1) % extra;
            String resultLine = "";
            for (String word : words) {
                resultLine += word;
                for (int i = 0; i < spacePerWords; i++) {
                    resultLine += '-';
                }
                for (int i = 0; i < remainder; i++) {
                    resultLine += '-';
                }
                remainder--;
            }
            result.add(resultLine.trim());
        }
        System.out.println(leftJustified);
        System.out.println(result);
        return result;
    }

    /**
     * This was my first question which I later reused in my second question.
     * This problem is a variant of {@link SentenceScreenFitting} where instead of repeating sentences multiple times,
     * We can only use it once. Solved it via greedy solution.
     * Struggled a bit during implementing edge cases.
     * All test cases passed.
     */
    private static List<String> wrapLines(String[] words, int lineLength) {
        String concat = "";
        for (String word : words) {
            String[] split = word.split(" ");
            concat += String.join("-", split);
            concat += "-";
        }
        concat = concat.substring(0, concat.length() - 1);
        System.out.println(concat + " " + concat.length());
        List<String> result = new ArrayList<>();
        int index = 0;
        while (true) {
            int max = Math.min(index + lineLength, concat.length());
            if (max == concat.length()) {
                result.add(concat.substring(index, max));
                break;
            } else {
                System.out.println(max);
                while (concat.charAt(max) != '-') {
                    max--;
                }
                System.out.println(concat.substring(index, max));
                result.add(concat.substring(index, max));
                index = max + 1;
            }
        }
        return result;
    }
}
