import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/number-of-atoms/
 * <p>
 * Given a chemical formula (given as a string), return the count of each atom.
 * <p>
 * The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
 * <p>
 * One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.
 * For example, H2O and H2O2 are possible, but H1O2 is impossible.
 * <p>
 * Two formulas concatenated together to produce another formula. For example, H2O2He3Mg4 is also a formula.
 * <p>
 * A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
 * <p>
 * Given a formula, return the count of all elements as a string in the following form: the first name (in sorted order),
 * followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 */
public class NumberOfAtoms {
    /**
     * Approach: Very similar to {@link DecodeString}
     * <p>
     * Result of each expression is a map, whenever ( is encountered, a new expression is started so push a new map to the stack
     * When ) is encountered, expression is finished, so get the multiplier behind the expression, if any
     * Multiply the atom count of the current expression and merge this with the current top of the stack
     * This merging reduces the total size of the stack, in the end stack will contain only one element
     * <p>
     * Lastly, iterate over the sorted map and generate the result
     * <p>
     * In my initial implementation, I tried to solve it via recursion but incorrectly implemented handling the (
     * ie. I thought that expression would be completely balanced with brackets i.e (H)2(O)2
     * when i saw the first opening bracket, i recursed for H)2(O and multiplied the results with 2
     * but the correct recursion would be to recurse for substring that is balanced by incrementing +1 for ( and decrementing -1 for )
     * when the balance is 0, recurse for that substring and multiply with the preceding multiplier.
     * This can handle cases like ((H2O)2)3 as well
     * <p>
     * Well, lesson learned the hard way, would have been awesome though if I could have implemented the recursive solution on my own. Was very close.
     */
    public String countOfAtoms(String formula) {
        Stack<Map<String, Integer>> result = new Stack<>();
        //remember for nested string expressions, first see what is required to solve for string without nested bracket, we would required Map<String, Integer>
        //now for nested expression, we need a stack of whatever was required for single expression i.e Stack<Map<String, Integer>>
        result.push(new HashMap<>());
        int index = 0;
        while (index < formula.length()) {
            char c = formula.charAt(index);
            if (Character.isUpperCase(c)) {//If a new element is found
                String element = "" + c;
                index++;
                //iterate till we find lower case characters
                while (index < formula.length() && Character.isLowerCase(formula.charAt(index))) {
                    element += formula.charAt(index);
                    index++;
                }
                int multiplier;
                int digit = 0;
                //iterate till we find digits
                while (index < formula.length() && Character.isDigit(formula.charAt(index))) {
                    digit = 10 * digit + (formula.charAt(index) - '0');
                    index++;
                }
                multiplier = digit == 0 ? 1 : digit;
                Map<String, Integer> pop = result.pop();
                //merge this atom count with the current expression
                pop.put(element, pop.getOrDefault(element, 0) + multiplier);
                result.push(pop);
            } else if ('(' == c) {
                //start a new expression
                result.push(new HashMap<>());
                index++;
            } else if (')' == c) {
                //current expression needs to be multiplied and merged with the previous expression
                //eg Mg((H)2(O)2)2
                //when at end of (O), need to merge O=2 with H=2
                //when at end of ), need to merge {H=2, O=2} with {Mg=1}
                //in the end only one map will remain {Mg=1, H=2, O=2}
                index++;
                int digit = 0;
                int multiplier;
                //iterate till we find all digits
                while (index < formula.length() && Character.isDigit(formula.charAt(index))) {
                    digit = 10 * digit + (formula.charAt(index) - '0');
                    index++;
                }
                multiplier = digit == 0 ? 1 : digit;
                Map<String, Integer> pop = result.pop();
                //multiply the atom count of the current expression
                for (Map.Entry<String, Integer> entry : pop.entrySet()) {
                    pop.put(entry.getKey(), entry.getValue() * multiplier);
                }
                //merge the current atom count with the previous expression
                Map<String, Integer> previousTop = result.pop();
                for (Map.Entry<String, Integer> entry : pop.entrySet()) {
                    previousTop.put(entry.getKey(), previousTop.getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
                result.push(previousTop);
            }
        }
        TreeMap<String, Integer> sortedMap = new TreeMap<>(result.pop()); //sort the atoms lexicographically
        String res = "";
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            if (entry.getValue() == 1) {
                res += entry.getKey();
            } else {
                res += entry.getKey();
                res += entry.getValue();
            }
        }
        return res;
    }
}
