import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * https://leetcode.com/problems/guess-the-word/
 * <p>
 * This problem is an interactive problem new to the LeetCode platform.
 * <p>
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 * <p>
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
 * <p>
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.
 * Also, if your guess is not in the given wordlist, it will return -1 instead.
 * <p>
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls,
 * if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 * <p>
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.
 * The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
 * <p>
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
 * <p>
 * Explanation:
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 * <p>
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 */
public class GuessTheWord {
    /**
     * 1. Brute Force Approach:
     * Randomly pick a word from the wordList, call guess() and check if it's the secret,
     * If no, then remove it from the wordList and guess again
     * <p>
     * 2. Optimized brute force approach:
     * Randomly pick a word from the wordlist, call guess() and check how many characters matches = x
     * Then check in the wordList for words with matches exactly similar to x, because we know that word1 and word2 have x matches
     * but we don't know which characters are in the secret, so we just pick all the words that have similarity to guess = x
     * <p>
     * e.g. guess word = cat, x = 0, so none of the characters match, so we will pick up words like {dog, bull} in next iteration
     * guess word = apple, x = 2, so two characters match, we don't know which two characters, so we will pick words like
     * ap***
     * a*p**
     * a**l*
     * a***e
     * *pp**
     * .....
     * <p>
     * This ensures we reduces the wordlist and will converge to the result.
     * However this does not ensure that we will guess the secret in minimum no of guesses, for that we have to use MiniMax algorithm
     */
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> words = Arrays.asList(wordlist);
        while (true) {
            int index = new Random().nextInt(words.size());
            String candidate = words.get(index);
            int correctPositions = master.guess(candidate);
            if (6 == correctPositions) { //words are exactly 6 characters long, so if all characters match, we guessed the secret
                break;
            } else {
                List<String> matchingWords = new ArrayList<>();
                for (int i = 0; i < words.size(); i++) {
                    if (i == index) { //exclude the guessed word
                        continue;
                    }
                    String word = words.get(i);
                    int match = findSimilarity(word, candidate);
                    if (match == correctPositions) { //pick up words whose similarity equals correctPositions
                        matchingWords.add(word);
                    }
                }
                words = matchingWords;
            }
        }
    }

    private int findSimilarity(String word, String candidate) {
        int matches = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == candidate.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }

    /**
     * Approach: Extension of optimized brute force algorithm, Uses MiniMax algorithm instead of randomly picking up the word
     * Pick up the word that minimizes the set of words in the next round
     * e.g. if word is cattle and it can have possibly 6 similarities with remaining words
     * similarity = 0, 30 words
     * similarity = 1 cupooo and 40 words
     * similarity = 2 capaaa and 50 words
     * .....
     * lets assume cattle results in max 51 similar words with similarity = 2
     * <p>
     * lets do the same thing for another word = apples
     * similarity = 0, 3 words
     * similarity = 1, 20 words
     * similarity = 2, 5 words
     * ....
     * lets assume apples results in max 20 words with similarity = 1
     * <p>
     * So its prudent to first choose apples as the candidate because it will greatly reduce the no of candidates in next iteration
     */
    public void findSecretWordMiniMax(String[] wordlist, Master master) {
        List<String> words = Arrays.asList(wordlist);
        while (true) {
            int index = minimax(words);
            String candidate = words.get(index);
            int correctPositions = master.guess(candidate);
            if (6 == correctPositions) { //words are exactly 6 characters long, so if all characters match, we guessed the secret
                break;
            } else {
                List<String> matchingWords = new ArrayList<>();
                for (int i = 0; i < words.size(); i++) {
                    if (i == index) { //exclude the guessed word
                        continue;
                    }
                    String word = words.get(i);
                    int match = findSimilarity(word, candidate);
                    if (match == correctPositions) { //pick up words whose similarity equals correctPositions
                        matchingWords.add(word);
                    }
                }
                words = matchingWords;
            }
        }
    }

    private int minimax(List<String> words) {
        int minimumMaximumSimilarity = Integer.MAX_VALUE; //need to minimize the maximum no of similar words
        int resultIndex = -1;
        for (int i = 0; i < words.size(); i++) {
            String candidate = words.get(i);
            int[] similaritiesBucket = new int[7];
            int maxSimilarity = Integer.MIN_VALUE; //what's the max no of words similar to candidate
            for (int j = 0; j < words.size(); j++) {
                if (j == i) {
                    continue;
                }
                int curSimilarity = findSimilarity(words.get(j), candidate);
                similaritiesBucket[curSimilarity]++;
                maxSimilarity = Math.max(maxSimilarity, similaritiesBucket[curSimilarity]);
            }
            if (maxSimilarity < minimumMaximumSimilarity) {
                minimumMaximumSimilarity = maxSimilarity;
                resultIndex = i;
            }
        }
        return resultIndex;
    }

    private interface Master {
        int guess(String word);
    }
}
