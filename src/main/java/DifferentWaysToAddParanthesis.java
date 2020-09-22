import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 * <p>
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators.
 * The valid operators are +, - and *.
 * <p>
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class DifferentWaysToAddParanthesis {
    /**
     * {@link UniqueBST2} is related problem. There each node was selected as root and left and right subtrees were
     * recursively created, after which results from left and right subtree were merged with the root to create a new tree
     * <p>
     * Approach: Solve it on paper e.g a*b*c*d
     * 1. Consider left as a and right as b*c*d -> recursively solve for b*c*d -> c*d -> c -> d
     * 2. Consider left as a*b and right as c*d
     * 3. Consider left as a*b*c and right as d
     * <p>
     * Base condition is when there is no operator present in the string.
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> operatorIndices = new ArrayList<>(); //store the indices of the operator, can solve the question without it too
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '*' || input.charAt(i) == '+' || input.charAt(i) == '-') {
                operatorIndices.add(i);
            }
        }
        if (operatorIndices.isEmpty()) { //single operand present, return as it is
            return Arrays.asList(Integer.parseInt(input));
        } else {
            List<Integer> res = new ArrayList<>();
            for (int index : operatorIndices) { //recursively consider each of them as operand
                List<Integer> left = diffWaysToCompute(input.substring(0, index));
                List<Integer> right = diffWaysToCompute(input.substring(index + 1));
                //critical part -- merge the results by applying the operator
                for (int leftValue : left) {
                    for (int rightValue : right) {
                        if (input.charAt(index) == '+') {
                            res.add(leftValue + rightValue);
                        } else if (input.charAt(index) == '*') {
                            res.add(leftValue * rightValue);
                        } else if (input.charAt(index) == '-') {
                            res.add(leftValue - rightValue);
                        }
                    }
                }
            }
            return res;
        }
    }
}
