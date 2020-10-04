import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/largest-values-from-labels/
 * <p>
 * We have a set of items: the i-th item has value values[i] and label labels[i].
 * <p>
 * Then, we choose a subset S of these items, such that:
 * <p>
 * |S| <= num_wanted
 * For every label L, the number of items in S with label L is <= use_limit.
 * Return the largest possible sum of the subset S.
 * <p>
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 * <p>
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 */
public class LargestValuesFromLabels {
    /**
     * Approach: Initially I thought of it as a knapsack but then I figured out that it's simple greedy
     * Need to pick up the largest values first if the counts at the labels permit, otherwise move on to next largest value.
     * Repeat the process until we pick the required number of values
     */
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        ArrayList<Pair<Integer, Integer>> list = new ArrayList<>(); //pair of {values, labels}
        for (int i = 0; i < labels.length; i++) {
            list.add(new Pair<>(values[i], labels[i]));
        }
        list.sort((o1, o2) -> Integer.compare(o2.getKey(), o1.getKey())); //descending order of values
        int sum = 0;
        int picked = 0;
        HashMap<Integer, Integer> labelsUsed = new HashMap<>();
        for (Pair<Integer, Integer> current : list) {
            int currentUtilization = labelsUsed.getOrDefault(current.getValue(), 0);
            if (currentUtilization < use_limit) { //if picking this label is allowed
                sum += current.getKey();
                picked++;
                if (picked == num_wanted) { //required number of values have been picked
                    return sum;
                }
                labelsUsed.put(current.getValue(), currentUtilization + 1);
            }
        }
        return sum;
    }
}
