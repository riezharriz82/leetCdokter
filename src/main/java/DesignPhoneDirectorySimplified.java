/**
 * https://leetcode.com/problems/design-phone-directory/
 */
public class DesignPhoneDirectorySimplified {
    int[] next; //keeps track of the next node in the chain, if next[i] == -1, i is unavailable
    int pos; //keeps track of the current head of the linked list

    /**
     * Initialize your data structure here
     * <p>
     * {@link DesignPhoneDirectory} followup to my original implementation
     * Alternate way to solve this using set in O(1) time would be to not maintain set of assigned numbers, but instead maintain set of
     * recycled numbers
     * https://leetcode.com/problems/design-phone-directory/discuss/183383/c%2B%2B-Why-don't-we-simply-use-unordered_set
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public DesignPhoneDirectorySimplified(int maxNumbers) {
        next = new int[maxNumbers];
        for (int i = 0; i < maxNumbers; i++) {
            next[i] = (i + 1) % maxNumbers; //notice the circular array
        }
    }

    /**
     * Provide a number which is not assigned to anyone.
     * Each node points to next unassigned node.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (next[pos] == -1) { //if head is also assigned, no nodes are left
            return -1;
        } else {
            //move head to node which is the next of current head
            int currentHead = pos;
            int nextHead = next[pos];
            next[pos] = -1; //mark current head as assigned
            pos = nextHead;
            return currentHead;
        }
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return next[number] != -1;
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        //visualize it as adding a node to the current head, next time get() is invoked, head() will be checked
        if (next[number] == -1) { //this ensures that head is only updated if the number is assigned
            int currentHead = pos;
            next[number] = currentHead;
            pos = number;
        }
    }
}
