import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * <p>
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 * <p>
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 */
public class SubarraySumsDivisibleByK {
    /**
     * Approach: {@link MakeSumDivisibleByP} for related divisibility and subarray sum problem
     * A subarray would be divisible by K if the mod is 0 so if we keep track of prefix sum of mod then the prefix sum should repeat.
     * i.e     {4 5 0 -2 -3 1}
     * modsum  {4,4,4,2,-1,0}
     * +ve modsum {4,4,4,2,4,0}
     * in order to have a subarray that is divisible by k, you need to find a subarray with repeating modsums, why?
     * <p>
     * in order to have subarray with sum divisible by k
     * a - b = n*k (a, b are prefix sum)
     * a%k - b%k = (n*k)%k
     * a%k - b%k = 0
     * a%k = b%k
     * prefix sum should get repeated
     * <p>
     * if the modsum is repeated, this means the subarray between the repeated modsum has sum = 0, since this is modsum, if modsum is 0 then this
     * subarray is divisible by k
     */
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        int curSum = 0;
        map.put(0, 1); //to handle prefix sum starting from 0th index
        int res = 0;
        for (int num : A) {
            curSum = (curSum + num) % K;
            if (curSum < 0) { //make it positive so that we only deal with positive numbers, otherwise we would miss some subarray
                curSum += K;
            }
            if (map.containsKey(curSum)) {
                res += map.get(curSum);
            }
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return res;
    }
}
