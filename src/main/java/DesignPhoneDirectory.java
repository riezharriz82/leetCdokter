import java.util.TreeSet;

/**
 * https://leetcode.com/problems/design-phone-directory/
 * <p>
 * Design a Phone Directory which supports the following operations:
 * <p>
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 * <p>
 * // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 * <p>
 * // It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 * <p>
 * // Assume it returns 1.
 * directory.get();
 * <p>
 * // The number 2 is available, so return true.
 * directory.check(2);
 * <p>
 * // It returns 2, the only number that is left.
 * directory.get();
 * <p>
 * // The number 2 is no longer available, so return false.
 * directory.check(2);
 * <p>
 * // Release number 2 back to the pool.
 * directory.release(2);
 * <p>
 * // Number 2 is available again, return true.
 * directory.check(2);
 */
public class DesignPhoneDirectory {
    int max;
    TreeSet<Integer> set; //keeps track of assigned numbers

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public DesignPhoneDirectory(int maxNumbers) {
        max = maxNumbers;
        set = new TreeSet<>();
    }

    /**
     * Provide a number which is not assigned to anyone.
     * Checks the set() for holes in front, between or in last and returns
     * TimeComplexity: O(n)
     * {@link ExamRoom} similar problem
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (set.isEmpty()) {
            set.add(0);
            return 0;
        } else {
            int last = set.last();
            if (set.size() == max) { //if all numbers are assigned, return -1
                return -1;
            } else if (last == max - 1) { //if no gap between last number assigned and max number possible, look for holes in between
                //need to find other holes
                int first = set.first();
                if (first != 0) { //gap found in the front
                    set.add(0);
                    return 0;
                }
                int prev = 0;
                for (int cur : set) {
                    if (cur > prev + 1) { //gap found between previous and current element
                        set.add(prev + 1); //fill the gap
                        return prev + 1;
                    }
                    prev = cur;
                }
                return -1; //not possible
            } else { //gap found, add a element in the gap
                set.add(last + 1);
                return last + 1;
            }
        }
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return !set.contains(number);
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        set.remove(number);
    }
}
