package experience;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * After catching your classroom students cheating before, you realize your students are getting craftier and hiding words in 2D grids of letters.
 * The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.
 * <p>
 * Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.
 * <pre>
 * grid1 = [
 * ['c', 'c', 'c', 't', 'i', 'b'],
 * ['c', 'c', 'a', 't', 'n', 'i'],
 * ['a', 'c', 'n', 'n', 't', 't'],
 * ['t', 'c', 's', 'i', 'p', 't'],
 * ['a', 'o', 'o', 'o', 'a', 'a'],
 * ['o', 'a', 'a', 'a', 'o', 'o'],
 * ['k', 'a', 'i', 'c', 'k', 'i'],
 * ]
 * word1 = "catnip"
 * word2 = "cccc"
 * word3 = "s"
 * word4 = "bit"
 * word5 = "aoi"
 * word6 = "ki"
 * word7 = "aaa"
 * word8 = "ooo"
 *
 * grid2 = [['a']]
 * word9 = "a"
 *
 * find_word_location(grid1, word1) => [ (0, 2), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
 * find_word_location(grid1, word2) =>
 * [(0, 1), (1, 1), (2, 1), (3, 1)]
 * OR [(0, 0), (1, 0), (1, 1), (2, 1)]
 * OR [(0, 0), (0, 1), (1, 1), (2, 1)]
 * OR [(1, 0), (1, 1), (2, 1), (3, 1)]
 * find_word_location(grid1, word3) => [(3, 2)]
 * find_word_location(grid1, word4) => [(0, 5), (1, 5), (2, 5)]
 * find_word_location(grid1, word5) => [(4, 5), (5, 5), (6, 5)]
 * find_word_location(grid1, word6) => [(6, 4), (6, 5)]
 * find_word_location(grid1, word7) => [(5, 1), (5, 2), (5, 3)]
 * find_word_location(grid1, word8) => [(4, 1), (4, 2), (4, 3)]
 * find_word_location(grid2, word9) => [(0, 0)]
 * </pre>
 */
public class CompassKaratCodingFirstAttempt {
    public static void main(String[] args) {
        char[][] grid1 = {
                {'c', 'c', 'c', 't', 'i', 'b'},
                {'c', 'c', 'a', 't', 'n', 'i'},
                {'a', 'c', 'n', 'n', 't', 't'},
                {'t', 'c', 's', 'i', 'p', 't'},
                {'a', 'o', 'o', 'o', 'a', 'a'},
                {'o', 'a', 'a', 'a', 'o', 'o'},
                {'k', 'a', 'i', 'c', 'k', 'i'}
        };
        String word1 = "catnip";
        String word2 = "cccc";
        String word3 = "s";
        String word4 = "bit";
        String word5 = "aoi";
        String word6 = "ki";
        String word7 = "aaa";
        String word8 = "ooo";

        char[][] grid2 = {{'a'}};
        String word9 = "a";
        System.out.println(find_word_location(grid1, word1));
        System.out.println(find_word_location(grid1, word2));
        System.out.println(find_word_location(grid1, word3));
        System.out.println(find_word_location(grid1, word4));
        System.out.println(find_word_location(grid1, word5));
        System.out.println(find_word_location(grid1, word6));
        System.out.println(find_word_location(grid1, word7));
        System.out.println(find_word_location(grid1, word8));
        System.out.println(find_word_location(grid2, word9));
    }

    private static List<Pair<Integer, Integer>> find_word_location(char[][] grid, String word) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == word.charAt(0)) {
                    List<Pair<Integer, Integer>> coordinates = new ArrayList<>();
                    boolean isPresent = DFS(grid, i, j, word, 0, coordinates);
                    if (isPresent) {
                        return coordinates;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    private static boolean DFS(char[][] grid, int i, int j, String word, int index, List<Pair<Integer, Integer>> coordinates) {
        if (index == word.length()) {
            return true;
        } else if (i >= grid.length || j >= grid[0].length) {
            return false;
        } else {
            if (grid[i][j] == word.charAt(index)) {
                coordinates.add(new Pair<>(i, j));
                boolean isPresentOnRight = DFS(grid, i, j + 1, word, index + 1, coordinates);
                if (isPresentOnRight) {
                    return true;
                } else {
                    boolean isPresentOnBottom = DFS(grid, i + 1, j, word, index + 1, coordinates);
                    if (isPresentOnBottom) {
                        return true;
                    } else {
                        coordinates.remove(coordinates.get(coordinates.size() - 1));
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
    }

    private static Optional<String> find_embedded_word(String[] words, String key) {
        int[] providedFreqCount = new int[26];
        for (char c : key.toCharArray()) {
            providedFreqCount[c - 'a']++;
        }
        for (String word : words) {
            int[] requiredFreqCount = new int[26];
            for (char c : word.toCharArray()) {
                requiredFreqCount[c - 'a']++;
            }
            int validChars = 0;
            for (int i = 0; i < 26; i++) {
                if (requiredFreqCount[i] > 0 && providedFreqCount[i] > 0 && requiredFreqCount[i] <= providedFreqCount[i]) {
                    validChars++;
                }
            }
            int requiredChars = 0;
            for (int i = 0; i < 26; i++) {
                if (requiredFreqCount[i] > 0) {
                    requiredChars++;
                }
            }
            if (validChars == requiredChars) {
                return Optional.of(word);
            }
        }
        return Optional.empty();
    }
}
