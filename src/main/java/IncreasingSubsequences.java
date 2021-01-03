import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/increasing-subsequences/
 * <p>
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.
 * <p>
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * <p>
 * Constraints:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */
public class IncreasingSubsequences {
    /**
     * Approach: Similar to finding LIS. Store the increasing subsequences at each index and try to create new subsequences by adding
     * current number to previous subsequences. Duplicate number needs to be handled with care
     * <p>
     * It can also be done by generating all subsets similar to {@link Subsets2}
     * <p>
     * {@link RussianDollEnvelopes} {@link NumberOfLongestIncreasingSubsequences}
     */
    public List<List<Integer>> findSubsequencesDP(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(); //map of value to their first occurrence index
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }
        List<List<List<Integer>>> increasingSeq = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            increasingSeq.add(new ArrayList<>());
        }
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> curSeq = increasingSeq.get(i);
            List<Integer> single = new ArrayList<>();
            single.add(nums[i]);
            if (map.get(nums[i]) == i) { //in case the current number is the first occurrence
                curSeq.add(single);
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        List<List<Integer>> previousSeqs = increasingSeq.get(j);
                        //get the results from the smaller number and add current number to all of their sequences
                        for (List<Integer> previousSeq : previousSeqs) {
                            ArrayList<Integer> copy = new ArrayList<>(previousSeq);
                            copy.add(nums[i]);
                            curSeq.add(copy);
                        }
                    }
                }
            } else { //if current number is the duplicate, we have to skip processing all the numbers till it's previous occurrence
                List<List<Integer>> previousSeqs = increasingSeq.get(map.get(nums[i])); //get the results from the duplicate index
                for (List<Integer> previousSeq : previousSeqs) {
                    ArrayList<Integer> copy = new ArrayList<>(previousSeq);
                    copy.add(nums[i]); //add the current value to the clone
                    curSeq.add(copy);
                }
                //now process elements in between the duplicate index and current index, e.g 10,9,10
                for (int j = map.get(nums[i]) + 1; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        previousSeqs = increasingSeq.get(j);
                        for (List<Integer> previousSeq : previousSeqs) {
                            ArrayList<Integer> copy = new ArrayList<>(previousSeq);
                            copy.add(nums[i]);
                            curSeq.add(copy);
                        }
                    }
                }
                map.put(nums[i], i); //very important to update the index, this handles situations where a number occurs > 2 times e.g. 1,3,3,3
            }
        }
        for (List<List<Integer>> seqs : increasingSeq) {
            for (List<Integer> seq : seqs) {
                if (seq.size() > 1) {
                    result.add(seq);
                }
            }
        }
        return result;
    }
}
