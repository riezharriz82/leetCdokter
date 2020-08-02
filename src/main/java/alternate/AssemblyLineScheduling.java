package alternate;

/**
 * https://www.geeksforgeeks.org/assembly-line-scheduling-dp-34/
 * <p>
 * There are two assembly lines, product can switch between assembly lines but it introduces a penalty
 * Each junction at a assembly line processes items in certain time
 * <p>
 * Find minimum amount of time required for the product to get processed
 * <pre>
 * 0 -----X ----X ---- X --- X
 *      |     |      |
 *      |     |      |
 * 1 -----Y ----Y ---- Y ----Y
 * </pre>
 */
public class AssemblyLineScheduling {
    /**
     * If we know the cost of the previous junction, we can build upon that to find the cost of the current junction
     * Cost at ith assembly line / jth junction = Min of either we continue down the same assembly line or we switch from another assembly line
     * 1. If we are at 0th assembly line and we switch -- dp[1][j-1] + cost to switch + cost[0][j]
     * 2. if we don't switch -- dp[0][j-1] + cost[0][j]
     */
}
