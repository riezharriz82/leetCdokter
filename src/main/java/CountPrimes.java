/**
 * https://leetcode.com/problems/count-primes/
 * <p>
 * Count the number of prime numbers less than a non-negative number, n.
 * <p>
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * <p>
 * Input: n = 0
 * Output: 0
 * <p>
 * Input: n = 1
 * Output: 0
 * <p>
 * Constraints:
 * 0 <= n <= 5 * 106
 */
public class CountPrimes {
    /**
     * Approach: Sieve of eratosthenes
     */
    public int countPrimes(int n) {
        if (n == 1) {
            return 0;
        }
        boolean[] primes = new boolean[n];
        for (int i = 2; i < n; i++) {
            primes[i] = true;
        }
        int max = (int) Math.sqrt(n);
        for (int i = 2; i <= max; i++) {
            if (primes[i]) { //pick the number that is not marked, and mark all it's multiples
                //ie. pick 3 and mark 6,9,12,15... as not prime
                for (int j = 2; j * i < n; j++) {
                    primes[j * i] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (primes[i]) {
                count++;
            }
        }
        return count;
    }
}
