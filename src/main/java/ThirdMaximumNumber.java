import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://leetcode.com/problems/third-maximum-number/
 */
public class ThirdMaximumNumber {

    public int thirdMaxConcise(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.add(n)) {
                pq.offer(n);
                if (pq.size() > 3) pq.poll();
            }
        }
        if (pq.size() == 2) pq.poll();
        return pq.peek();
    }


    public int thirdMax(int[] nums) {
        int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE, thirdMax = Integer.MIN_VALUE;
        int numberOfDistinctElements = 0;
        boolean minValuePresent = false;
        for (int num : nums) {
            if (num > max) {
                numberOfDistinctElements++;
                thirdMax = secondMax;
                secondMax = max;
                max = num;
            } else if (num == max) {
                if (num == Integer.MIN_VALUE && !minValuePresent) {
                    numberOfDistinctElements++;
                    minValuePresent = true;
                }
            } else if (num > secondMax) {
                numberOfDistinctElements++;
                thirdMax = secondMax;
                secondMax = num;
            } else if (num == secondMax) {
                if (num == Integer.MIN_VALUE && !minValuePresent) {
                    numberOfDistinctElements++;
                    minValuePresent = true;
                }
            } else if (num > thirdMax) {
                numberOfDistinctElements++;
                thirdMax = num;
            } else if (num == thirdMax) {
                if (num == Integer.MIN_VALUE && !minValuePresent) {
                    numberOfDistinctElements++;
                    minValuePresent = true;
                }
            }
        }
        if (numberOfDistinctElements < 3) {
            return max;
        }
        return thirdMax;
    }


}
