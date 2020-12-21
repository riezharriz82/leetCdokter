import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 * <p>
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 * <p>
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructionByHeight {
    /**
     * Approach: Tricky greedy problem, till now I have seen in reconstruction problem, need to start fixing from greater to smaller elements.
     * <p>
     * Google asked a similar constructive problem,
     * Ref https://leetcode.com/discuss/interview-experience/932653/google-fresh-grad-2021-bangloreindia-nov-2020-reject
     * Let's say we have a permutation P of length n(n = 5 here) = [3, 5, 1, 4, 2]
     * Now we delete elements from this permutation P from 1 to n in order and write their index to
     * another array Q. When an element is deleted, remaining elements are shifted to left by 1.
     * Initial: P = [3, 5, 1, 4, 2], Q = []
     * delete 1, P = [3, 5, 4, 2], Q = [3] (index of 1 was 3 so write 3(bcz it's index of 1) in Q)
     * delete 2, P = [3, 5, 4], Q = [3, 4]
     * delete 3, P = [5, 4], Q = [3,4,1]
     * delete 4, P = [5], Q = [3, 4, 1, 2]
     * delete 5, P = [], Q = [3, 4, 1, 2, 1]
     * <p>
     * Now given Q, we have to restore P.
     * <p>
     * We can do that question as Shashwat suggested by first fixing the largest number 5 on 1st index (1 based indexing)
     * Then fix 4 at 2nd index, then 3rd is at 1st index but something is already present on 1st index,
     * So shift all element to the right.
     * Repeat this process for all numbers
     * <p>
     * Similarly for this problem we will fix the people with largest height first and then fix the smaller height people.
     * In case of multiple people with same height, first fix the people that have fewer taller people in front of them, because duh !
     * While adding a smaller height people, if you see some people on that index, shift everything to the right
     * <p>
     * The reason I can think of why this works is we always try to fix the first index that can be fixed e.g [7,0] is obviously the first
     * index that needs to be fixed because that's the tallest person and no taller person stands in front of him
     * Similarly in permutation we know that 5 was deleted last and it's index would definitely be 1, so fix 5 first.
     * <p>
     * There is another approach in which we first fix the smallest height person
     * https://leetcode.com/problems/queue-reconstruction-by-height/discuss/427157/Three-different-C%2B%2B-solutions.-from-O(n2)-to-O(nlogn).-faster-than-99.
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]); //if height matches, the people with less no of taller people in front of them, should be picked first
            } else {
                return Integer.compare(o2[0], o1[0]); //descending order of height
            }
        });
        LinkedList<int[]> list = new LinkedList<>();
        for (int[] person : people) {
            list.add(person[1], person); //add person
        }
        return list.toArray(new int[list.size()][2]);
    }
}
