import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 * Given an array w of positive integers, where w[i] describes the weight of index i(0-indexed),
 * write a function pickIndex which randomly picks an index in proportion to its weight.
 * <p>
 * For example, given an input list of values w = [2, 8], when we pick up a number out of it,
 * the chance is that 8 times out of 10 we should pick the number 1 as the answer since it's the second element of the array (w[1] = 8).
 */
public class RandomPickWithWeight {
    int[] cumulativeWeights;
    int totalSum;

    public RandomPickWithWeight(int[] w) {
        for (int i = 1; i < w.length; i++) {
            w[i] += w[i - 1];
        }
        cumulativeWeights = w;
        totalSum = cumulativeWeights[w.length - 1];
    }

    public int pickIndex() {
        //if the original weights were {1,4,5}, cumulative weights will be {1, 5, 10}
        //now perform binary search with the key as a random value between [1, 10)
        // actually used random.nextInt( wSums[len-1] + 1), and I know why it failed. For wsum[] = {2,7,10,14},
        // it generates a random value in range [0, 14], totally 15 numbers. If the random number is 0, 1, 2, our code will return index 0,
        // so the probability for selecting the first one is 3/15.
        //However, random.nextInt(wSums[len-1]) +1 gives us random values in [1, 14], totally 14 numbers. Only when the number is 1, 2,
        // our code return index 0. The probability for selecting the first one is 2/14.
        int index = Arrays.binarySearch(cumulativeWeights, new Random().nextInt(totalSum) + 1);
        if (index >= 0) {
            return index;
        } else {
            return -index - 1;
        }
    }
}
