import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/advantage-shuffle/
 * <p>
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
 * <p>
 * Return any permutation of A that maximizes its advantage with respect to B.
 * <p>
 * Input: A = [2,7,11,15], B = [1,10,4,11]
 * Output: [2,11,7,15]
 * <p>
 * Input: A = [12,24,8,32], B = [13,25,32,11]
 * Output: [24,32,8,12]
 * <p>
 * Note:
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 */
public class AdvantageShuffle {
    /**
     * Approach: Greedy, for each element in B[i], try to find the smallest element in A[] > B[i].
     * If no such element found, place the smallest element, as it will have the smallest penalty.
     * <p>
     * Saw this line of discuss, instead of trying to maximise the gain, think about minimizing the loss. Placing the smallest unplaced element in case no larger
     * element found will minimize the loss.
     * <p>
     * My initial solution is exactly similar to lee215@
     * <p>
     * {@link FurthestBuildingYouCanReach}
     */
    public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int val : A) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int key = B[i];
            Integer ceiling = map.higherKey(key); //find a number in A[] > B[i]
            if (ceiling == null) {
                //if no greater number found, place the smallest element to minimise loss
                Map.Entry<Integer, Integer> smallestEntry = map.firstEntry();
                A[i] = smallestEntry.getKey();
                map.put(smallestEntry.getKey(), smallestEntry.getValue() - 1);
                map.remove(smallestEntry.getKey(), 0);
            } else {
                //put the greater element at ith index of A[]
                A[i] = ceiling;
                map.put(ceiling, map.get(ceiling) - 1);
                map.remove(ceiling, 0);
            }
        }
        return A;
    }
}
