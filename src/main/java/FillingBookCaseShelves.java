/**
 * https://leetcode.com/problems/filling-bookcase-shelves/
 * <p>
 * We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
 * <p>
 * We want to place these books in order onto bookcase shelves that have total width shelf_width.
 * <p>
 * We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width),
 * then build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down.
 * We repeat this process until there are no more books to place.
 * <p>
 * Note again that at each step of the above process, the order of the books we place is the same order as the given sequence of books.
 * For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf,
 * the third book on the second shelf, and the fourth and fifth book on the last shelf.
 * <p>
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 * <p>
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * Explanation:
 * The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 */
public class FillingBookCaseShelves {
    /**
     * Approach: Since the order of the books need to be maintained, it's a subarray problem.
     * Every book has two options, either be on the same shelf or on the next shelf.
     * Keep placing books on the same shelf until the max width isn't reached.
     * <p>
     * Leveraged previously solved problem {@link SplitArrayLargestSum} to create the recursion
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        int[] memoized = new int[books.length];
        return recur(books, shelf_width, 0, memoized);
    }

    private int recur(int[][] books, int shelf_width, int index, int[] memoized) {
        if (index == books.length) {
            return 0;
        }
        if (memoized[index] != 0) {
            return memoized[index];
        }
        int currentMaxHeight = Integer.MIN_VALUE;
        int totalMinHeight = Integer.MAX_VALUE;
        int curWidth = 0;
        //every index has two options, either be part of the same shelf (be part of the loop)
        // or part of the next shelf (be part of next recursion)
        for (int i = index; i < books.length; i++) {
            if (curWidth + books[i][0] <= shelf_width) {
                curWidth += books[i][0];
                currentMaxHeight = Math.max(currentMaxHeight, books[i][1]);
                int remainingHeight = recur(books, shelf_width, i + 1, memoized);
                totalMinHeight = Math.min(totalMinHeight, currentMaxHeight + remainingHeight);
            } else {
                //not possible to expand
                break;
            }
        }
        return memoized[index] = totalMinHeight;
    }
}
