import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/
 * Given a collection of intervals, merge all overlapping intervals.
 * <pre>
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * </pre>
 */
public class MergeIntervals {
    public int[][] mergeSimplified(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (i == 0 || res.get(res.size() - 1)[1] < intervals[i][0]) { //if there is no overlap between the last merged element and the current
                res.add(intervals[i]);
            } else {
                int[] lastElement = res.get(res.size() - 1);
                res.set(res.size() - 1, new int[]{lastElement[0], Math.max(lastElement[1], intervals[i][1])});
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public int[][] mergeMyOriginalSolution(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[o1.length - 1], o2[o2.length - 1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        }); //sort input based on the start of the interval
        ArrayList<Pair<Integer, Integer>> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; ) {
            int end = intervals[i][1];
            int j = i + 1;
            while (j < intervals.length) {
                if (end >= intervals[j][0] && end <= intervals[j][1]) {
                    end = intervals[j][1];
                    j++;
                } else if (end >= intervals[j][0] && end >= intervals[j][1]) {
                    j++;
                } else {
                    break;
                }
            }
            //merge everything between i and j
            res.add(new Pair<>(intervals[i][0], end));
            i = j;
        }
        int[][] resultArray = new int[res.size()][2];
        int k = 0;
        for (Pair<Integer, Integer> items : res) {
            resultArray[k][0] = items.getKey();
            resultArray[k++][1] = items.getValue();
        }

        return resultArray;
    }
}
