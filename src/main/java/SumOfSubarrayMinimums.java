import java.util.ArrayDeque;

/**
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 * <p>
 * Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
 * <p>
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 * <p>
 * Input: [3,1,2,4]
 * Output: 17
 * Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 */
public class SumOfSubarrayMinimums {
    /**
     * Approach: Sometimes I feel like a genius to solve problems like this on my own as this is a hard problem but then I start struggling with other problems.
     * <p>
     * Anyways there are two tricks to solve this problem
     * 1. To solve this in linear time, you need to find and store the contribution of each index
     * 2. To do that you need to find how many time an index can contribute, for that you need a monotonic stack
     * <p>
     * I broke the problem down into smaller problems, like first I tried to solve the problem for single element array, then 2 sized array
     * for 2 sized array the second number can be greater or smaller than the first element
     * if greater, then the second number can contribute only once, and we need to reuse the result of first element when considering contribution for second number
     * if smaller, then the first number can contribute only once, but the second number contributes twice.
     * <p>
     * {@link LargestRectangleInHistogram} for similar problem
     */
    public int sumSubarrayMins(int[] A) {
        int mod = 1000000007;
        int n = A.length;
        int[] result = new int[n]; //result[i] is the min subarray sum contributed by index i
        ArrayDeque<Integer> stack = new ArrayDeque<>(); //monotonic increasing stack of indices
        //why increasing? because we are interested in minimum elements
        for (int i = 0; i < A.length; i++) {
            while (!stack.isEmpty() && A[stack.peek()] > A[i]) {
                stack.pop(); //pop all the greater elements
            }
            if (stack.isEmpty()) { //if we found the smallest element
                result[i] = (i + 1) * A[i];
                //crucial to understand this, as ith index is the smallest element, it will contribute i+1 times to the final result
                //3, 2, 1
                //3 contributes one time
                //2 contributes 2 times
                //1 contributes 3 times
            } else {
                //fresh contribution of ith index + contribution of previous smaller element
                result[i] = (i - stack.peek()) * A[i] + result[stack.peek()];
                //if ith index is not the smallest then we find out how many index is it greater than?
                //this will help in finding the number of times ith index can contributes
                //1, 10, 20, 15
                //1 contributes 4 time
                //10 contributes 3 time
                //20 contributes 1 time
                //15 contributes 2 times here
                //how can 1 contribute 4 times? because we append the result of previous smaller element
                //do it step by step, you can figure it out
            }
            stack.push(i);
        }
        int sum = 0;
        for (int res : result) { //sum up the contributions of all the indices
            sum = (sum + res) % mod;
        }
        return sum;
    }
}
