import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/optimal-account-balancing/ Premium
 * <p>
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10.
 * Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z.
 * Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 * <p>
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 * <p>
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 * <p>
 * Input:
 * [[0,1,10], [2,0,5]]
 * Output:
 * 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * <p>
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 * Output:
 * 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 */
public class OptimalAccountBalancing {
    /**
     * Approach: Backtracking, consider each individuals debt and try to pick another individual with opposite debt (sign)
     * and then transfer all current debt to him. This way we have now n-1 people to deal with. Try out all combinations and see which
     * leads to the smallest no of transactions
     * TimeComplexity: O(n!)
     * <p>
     * {@link TilingARectangleWithFewestSquares} {@link CrackingTheSafe} {@link PathWithMaximumGold} related google hard problems
     */
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>(); //personId -> final debt
        for (int[] transaction : transactions) {
            map.put(transaction[0], map.getOrDefault(transaction[0], 0) - transaction[2]);
            map.put(transaction[1], map.getOrDefault(transaction[1], 0) + transaction[2]);
        }
        ArrayList<Integer> debts = new ArrayList<>();
        for (int debt : map.values()) {
            if (debt != 0) {
                debts.add(debt);
            }
        }
        //System.out.println(debts);
        return recur(0, debts);
    }

    private int recur(int index, ArrayList<Integer> debts) {
        if (index == debts.size()) { //all debts have been settled
            return 0;
        } else if (debts.get(index) == 0) { //if current debt is already settled, skip it
            return recur(index + 1, debts);
        }
        int minTransactions = Integer.MAX_VALUE;
        for (int i = index + 1; i < debts.size(); i++) {
            //need to find a debt of opposing sign to settle, otherwise we can never minimize the no of transactions
            //e.g. if current debt is 500 and opposing debt is -300, settle current debt by transferring entire money to other person
            //this way one person is satisfied and we are left with n-1 persons, other person debt will be 200
            if (debts.get(index) * debts.get(i) < 0) {
                debts.set(i, debts.get(i) + debts.get(index));
                minTransactions = Math.min(minTransactions, 1 + recur(index + 1, debts)); //notice we didn't recur for i + 1
                //because we settled debt for index, not i
                debts.set(i, debts.get(i) - debts.get(index));
            }
        }
        return minTransactions == Integer.MAX_VALUE ? 0 : minTransactions;
    }
}
