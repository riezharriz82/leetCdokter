/**
 * <pre>
 * https://leetcode.com/problems/merge-triplets-to-form-target-triplet/
 *
 * A triplet is an array of three integers. You are given a 2D integer array triplets, where triplets[i] = [ai, bi, ci] describes the ith triplet.
 * You are also given an integer array target = [x, y, z] that describes the triplet you want to obtain.
 *
 * To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
 *
 * Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to become [max(ai, aj), max(bi, bj), max(ci, cj)].
 * For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element of triplets, or false otherwise.
 *
 * Input: triplets = [[2,5,3],[1,8,4],[1,7,5]], target = [2,7,5]
 * Output: true
 * Explanation: Perform the following operations:
 * - Choose the first and last triplets [[2,5,3],[1,8,4],[1,7,5]]. Update the last triplet to be [max(2,1), max(5,7), max(3,5)] = [2,7,5]. triplets = [[2,5,3],[1,8,4],[2,7,5]]
 * The target triplet [2,7,5] is now an element of triplets.
 *
 * Input: triplets = [[1,3,4],[2,5,8]], target = [2,5,8]
 * Output: true
 * Explanation: The target triplet [2,5,8] is already an element of triplets.
 *
 * Input: triplets = [[2,5,3],[2,3,4],[1,2,5],[5,2,3]], target = [5,5,5]
 * Output: true
 * Explanation: Perform the following operations:
 * - Choose the first and third triplets [[2,5,3],[2,3,4],[1,2,5],[5,2,3]]. Update the third triplet to be [max(2,1), max(5,2), max(3,5)] = [2,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,2,3]].
 * - Choose the third and fourth triplets [[2,5,3],[2,3,4],[2,5,5],[5,2,3]]. Update the fourth triplet to be [max(2,5), max(5,2), max(5,3)] = [5,5,5]. triplets = [[2,5,3],[2,3,4],[2,5,5],[5,5,5]].
 * The target triplet [5,5,5] is now an element of triplets.
 *
 * Input: triplets = [[3,4,5],[4,5,6]], target = [3,2,5]
 * Output: false
 * Explanation: It is impossible to have [3,2,5] as an element because there is no 2 in any of the triplets.
 *
 * Constraints:
 * 1 <= triplets.length <= 10^5
 * triplets[i].length == target.length == 3
 * 1 <= ai, bi, ci, x, y, z <= 1000
 * </pre>
 */
public class MergeTripletsToFormTargetTriplet {
    /**
     * Approach: Greedy, in order to form the target triplet, we need to consider only those triplets where any value does not exceeds target in any dimension.
     * This will avoid picking up invalid triplets. This is the most important step.
     *
     * Now with the remaining valid triplets, we need to ensure that the maximum value of any dimension matches the required target value.
     * This is required to handle cases where the maximum value < required value
     *
     * Was not able to solve this question during the contest :(
     * Tried multiple approaches but was getting WA in test cases.
     * 1. Pick up triplets such that any value in those triplets matches required value of target triplet.
     *
     * 2. Modify the comparator in sort function to simulate merging of triplets. This can still potentially work but could not get the code quite right.
     *
     * 3. Sort the array. Perform 3 passes. In first pass, find the max index where first value of triplet <= first value of target triplet.
     * In second and third pass repeat the same for second and third triplet.
     * In the end, check if you find indices with any value equal to any value of target triplet.
     *
     * 1 & 3 does not avoid picking up invalid triplets where any value exceeds target triplet hence got WA.
     *
     * {@link CarFleet} {@link BulbSwitcher4}
     */
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int maxA = 0, maxB = 0, maxC = 0;
        for (int[] triplet : triplets) {
            if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
                //if we pick this triplet, we can never reach target as the value in this triplet already exceeds the required value
                continue;
            }
            maxA = Math.max(maxA, triplet[0]);
            maxB = Math.max(maxB, triplet[1]);
            maxC = Math.max(maxC, triplet[2]);
        }
        return maxA == target[0] && maxB == target[1] && maxC == target[2];
    }
}