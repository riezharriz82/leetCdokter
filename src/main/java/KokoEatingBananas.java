/**
 * https://leetcode.com/problems/koko-eating-bananas/ Premium
 * <p>
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.
 * If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
 * <p>
 * Return the minimum integer K such that she can eat all the bananas within H hours.
 * <p>
 * Input: piles = [3,6,7,11], H = 8
 * Output: 4
 * <p>
 * Input: piles = [30,11,23,4,20], H = 5
 * Output: 30
 * <p>
 * Input: piles = [30,11,23,4,20], H = 6
 * Output: 23
 * <p>
 * Constraints:
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 */
public class KokoEatingBananas {
    /**
     * Approach: So Koko can try eating at speed 1,2,3.. max(bananas) and see whether it can finish eating all the piles in <= H hours
     * The smallest speed that can suffice the time constraint is the result
     * [1,2,3...100] => [F,F,F,F,T,T,T]
     * We are looking for first T
     * <p>
     * {@link DivideChocolates} {@link MagneticForceBetweenTwoBalls} {@link MissingElementInSortedArray} related problems
     */
    public int minEatingSpeed(int[] piles, int H) {
        int min = Integer.MAX_VALUE;
        for (int pile : piles) {
            min = Math.min(pile, min);
        }
        int low = 1, high = min, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hoursRequiredToEatAtSpeed(mid, piles) <= H) { //T found, try eating at a lower speed
                ans = mid;
                high = mid - 1;
            } else { //can't finish eating before the guard comes, try eating at a faster speed
                low = mid + 1;
            }
        }
        return ans;
    }

    private int hoursRequiredToEatAtSpeed(int speed, int[] piles) {
        int hours = 0;
        for (int pile : piles) {
            hours += Math.ceil(pile / (double) speed); //apparently using double to compute is slower
        }
        return hours;
    }
}
