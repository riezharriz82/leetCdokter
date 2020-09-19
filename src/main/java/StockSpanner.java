import javafx.util.Pair;

import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/online-stock-span/
 * <p>
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.
 * <p>
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards)
 * for which the price of the stock was less than or equal to today's price.
 * <p>
 * For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].
 */
public class StockSpanner {
    ArrayDeque<Pair<Integer, Integer>> stack = new ArrayDeque<>(); //decreasing stack of pair of number and index
    //index required to calculate the difference between current index and the index of the previous greater element

    int curIndex;

    public StockSpanner() {

    }

    /**
     * {@link NextGreaterElement} for similar problem related to monotonic stack
     */
    public int next(int price) {
        curIndex++;
        while (!stack.isEmpty() && stack.peek().getKey() <= price) { //pop all smaller elements so that we can find out the span
            stack.pop();
        }
        if (stack.isEmpty()) { //largest price found so far
            stack.push(new Pair<>(price, curIndex));
            return curIndex;
        } else {
            int res = curIndex - stack.peek().getValue(); //find the difference in curIndex and the previous greater element
            stack.push(new Pair<>(price, curIndex));
            return res;
        }
    }
}
