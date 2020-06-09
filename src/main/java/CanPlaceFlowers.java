/**
 * https://leetcode.com/problems/can-place-flowers/
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
