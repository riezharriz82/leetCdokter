import java.util.Arrays;

/**
 * https://leetcode.com/problems/h-index/
 * <p>
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * <p>
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining
 * two with no more than 3 citations each, her h-index is 3.
 */
public class HIndex {

    /**
     * Count no of values greater than current value. Values here are 0 to n as answer can't exceed total no of citations
     * So count of buckets is limited
     */
    public int hIndexBucketSort(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        for (int citation : citations) {
            if (citation >= n) {
                buckets[n]++;
            } else {
                buckets[citation]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) { //iterate from reverse because need to find the largest hIndex
            count += buckets[i];
            if (count >= i) { //if no of citations greater than current index
                return i;
            }
        }
        return 0;
    }

    /**
     * Implemented from Wikipedia pseudocode
     * reverse sort the array and then return
     * max (min (f(i), i))
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            citations[i] *= -1; //in order to reverse sort
        }
        Arrays.sort(citations);
        int maxHIndex = 0;
        for (int i = 0; i < n; i++) {
            int greaterOrEqualCitations = Math.min(i + 1, -1 * citations[i]);
            maxHIndex = Math.max(maxHIndex, greaterOrEqualCitations);
        }
        return maxHIndex;
    }
}
