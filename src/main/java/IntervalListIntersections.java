import java.util.*;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 * <p>
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 * <p>
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 */
public class IntervalListIntersections {
    /**
     * Approach: Line sweep algorithm by incrementing / decrementing counters.
     * Start of common intervals would have a counter == 2
     * {@link EmployeeFreeTime} related problem
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] a_interval : A) {
            map.put(a_interval[0], map.getOrDefault(a_interval[0], 0) + 1);
            map.put(a_interval[1], map.getOrDefault(a_interval[1], 0) - 1);
        }
        for (int[] b_interval : B) {
            map.put(b_interval[0], map.getOrDefault(b_interval[0], 0) + 1);
            map.put(b_interval[1], map.getOrDefault(b_interval[1], 0) - 1);
        }
        int runningCounter = 0, start = -1;
        ArrayList<int[]> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                //edge case to consider single points where one interval ends and another interval starts
                result.add(new int[]{entry.getKey(), entry.getKey()});
            }
            if (start != -1) {
                //if we have already found a start of the common interval, this would signal the end of the common interval
                result.add(new int[]{start, entry.getKey()});
                start = -1; //reset the start
            }
            runningCounter += entry.getValue();
            if (runningCounter == 2) {
                //if an interval is common between A and B, counter == 2, we need to mark the start of the common interval
                start = entry.getKey();
            }
        }
        return result.toArray(new int[result.size()][2]);
    }

    /**
     * Approach: Use priority queue to merge intervals
     * <p>
     * Using priority queue here is an overkill since we can use two pointers strategy to merge two sorted intervals
     * and find the overlapping intervals
     */
    public int[][] intervalIntersectionUsingPriorityQueue(int[][] A, int[][] B) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.addAll(Arrays.asList(A));
        pq.addAll(Arrays.asList(B));
        if (pq.isEmpty()) {
            return new int[][]{};
        }
        int[] pre = pq.poll();
        ArrayList<int[]> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (Math.max(pre[0], cur[0]) <= Math.min(pre[1], cur[1])) {
                //intersection found, this intersection logic is similar to the logic used in RectangleOverlap
                result.add(new int[]{Math.max(pre[0], cur[0]), Math.min(pre[1], cur[1])});
                //tricky portion, current interval has now merged previous and current interval
                //this is required to correctly identify the overlapping interval in future
                //Sample example: A= [[8,15]]   B = [[2,6],[8,10],[12,20]]
                cur = new int[]{Math.min(pre[0], cur[0]), Math.max(pre[1], cur[1])};
            }
            pre = cur;
        }

        return result.toArray(new int[result.size()][2]);
    }
}
