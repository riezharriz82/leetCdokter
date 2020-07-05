/**
 * https://leetcode.com/problems/arranging-coins/
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * <p>
 * Given n, find the total number of full staircase rows that can be formed.
 * <p>
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 * <p>
 * n = 5
 * <p>
 * The coins can form the following rows:
 * <pre>
 * ¤
 * ¤ ¤
 * ¤ ¤
 * </pre>
 * Because the 3rd row is incomplete, we return 2.
 */
public class ArrangingCoins {
    //Can also be solved by doing a binary search between 1 and n and comparing the mid
    public int arrangeCoins(int n) {
        //PS: Since we are doing 8 * n which can go out of bounds of int, we need to switch to long by using 8L
        return (int) ((Math.sqrt(8L * n + 1) - 1) / 2);
    }
}
