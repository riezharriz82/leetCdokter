import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/partition-labels/
 * <p>
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible
 * so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 * <p>
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 */
public class PartitionLabels {
    /**
     * Approach: Keep track of last occurrences of each character, if last occurrence of current character > end of current interval, extend the interval
     * If the current interval is completed, start a new interval and repeat the process
     */
    public List<Integer> partitionLabelsSimplified(String S) {
        int[] lastOccurrence = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastOccurrence[S.charAt(i) - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();
        int curEnd = -1;
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            //update end of current interval to the last occurrence of this character which may lie before or after curEnd
            curEnd = Math.max(curEnd, lastOccurrence[S.charAt(i) - 'a']);
            if (i == curEnd) {
                //current interval is done, need to start a new interval
                result.add(curEnd - start + 1);
                start = curEnd + 1; //new interval will start just after end of current interval
            }
        }
        return result;
    }

    /**
     * My initial approach by transforming the problem into merge overlapping intervals
     * {@link MergeIntervals} for related problem
     * Track the {start,end} index for each character that will act like {start, end} for each interval
     */
    public List<Integer> partitionLabels(String S) {
        //list of {first, last} occurrence of each character
        List<Pair<Integer, Integer>> lst = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            lst.add(new Pair<>(-1, -1));
        }
        for (int i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            int currentStart = lst.get(index).getKey();
            if (currentStart == -1) {
                //first time seeing this character
                lst.set(index, new Pair<>(i, i));
            } else {
                //update the last occurrence of the character
                lst.set(index, new Pair<>(currentStart, i));
            }
        }
        Collections.sort(lst, Comparator.comparingInt(Pair::getKey));
        List<Pair<Integer, Integer>> merged = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            Pair<Integer, Integer> candidate = lst.get(i);
            if (candidate.getKey() != -1) {
                if (merged.isEmpty() || merged.get(merged.size() - 1).getValue() < candidate.getKey()) {
                    //if this interval is non-overlapping
                    merged.add(candidate);
                } else if (merged.get(merged.size() - 1).getValue() < candidate.getValue()) {
                    //overlapping interval, extend the previous interval
                    merged.set(merged.size() - 1, new Pair<>(merged.get(merged.size() - 1).getKey(), candidate.getValue()));
                }
            }
        }
        //transform to match the expected output
        return merged.stream()
                .map(pair -> pair.getValue() - pair.getKey() + 1)
                .collect(Collectors.toList());
    }
}
