/**
 * https://leetcode.com/problems/can-place-flowers/
 * <p>
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not.
 * However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
 * <p>
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
 * and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 * <p>
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 */
public class CanPlaceFlowers {
    public boolean canPlaceFlowersCleaner(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length && count < n; i++) {
            if (flowerbed[i] == 0) {
                //get next and prev flower bed slot values. If i lies at the ends the next and prev are considered as 0.
                int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                int prev = (i == 0) ? 0 : flowerbed[i - 1];
                if (next == 0 && prev == 0) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }
        return count == n;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int currentlyPlaced = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if (i == 0) {
                    if (flowerbed.length == 1) {
                        flowerbed[i] = 1;
                        currentlyPlaced++;
                    } else if (flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1;
                        currentlyPlaced++;
                    }
                } else if (i == flowerbed.length - 1) {
                    if (flowerbed[i - 1] == 0) {
                        flowerbed[i] = 1;
                        currentlyPlaced++;
                    }
                } else {
                    if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1;
                        currentlyPlaced++;
                    }
                }
                if (currentlyPlaced >= n) {
                    return true;
                }
            }
        }
        return currentlyPlaced >= n;
    }
}
