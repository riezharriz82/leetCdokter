import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 * <p>
 * Given a sorted array arr, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order.
 * If there is a tie, the smaller elements are always preferred.
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 */
public class FindKClosestElements {
    /**
     * Approach: Binary Search with two pointer
     * Time Complexity: logn + k
     * <p>
     * Use binary search to find the first element >= x, then use two pointers technique (similar to merge two sorted arrays)
     * to pick up k closest elements
     * In the end iterate from [ptr1 + 1, ptr2 - 1] and include all elements in result
     * <p>
     * In my initial implementation I added elements to the result while incrementing/decrementing ptr1/ptr2 and in the end
     * used sort to return the list, however sorting is unnecessary.
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int low = 0, high = arr.length - 1, result = arr.length - 1;
        //result initialized with a valid index
        //required when all elements in array are < x, so the closest element is the last element
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        List<Integer> resultElements = new ArrayList<>();
        int ptr1 = result - 1, ptr2 = result, picked = 0;
        while (ptr1 >= 0 && ptr2 < arr.length && picked < k) { //similar to merge two sorted arrays
            int diff1 = Math.abs(arr[ptr1] - x);
            int diff2 = Math.abs(arr[ptr2] - x);
            if (diff1 < diff2) {
                ptr1--;
            } else if (diff1 > diff2) {
                ptr2++;
            } else {
                //gotcha, if two elements have the same diff, the smaller element is preferred as per problem statement
                if (arr[ptr1] < arr[ptr2]) {
                    ptr1--;
                } else {
                    ptr2++;
                }
            }
            picked++;
        }
        while (ptr1 >= 0 && picked < k) {
            ptr1--;
            picked++;
        }
        while (ptr2 < arr.length && picked < k) {
            ptr2++;
            picked++;
        }
        //add to the result by iterating from smaller to larger element, this would prevent explicit sorting of the result
        for (int i = ptr1 + 1; i < ptr2; i++) {
            resultElements.add(arr[i]);
        }
        return resultElements;
    }
}
