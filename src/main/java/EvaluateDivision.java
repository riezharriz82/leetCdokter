import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/evaluate-division/
 * <p>
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number).
 * Given some queries, return the answers. If the answer does not exist, return -1.0.
 * <p>
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 * <p>
 * According to the example above:
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 */
public class EvaluateDivision {
    /**
     * Approach: The trick behind the problem was to model the relationships as graph
     * Initially I thought of it as string processing and then lightening stuck and I thought about graph
     * Create a graph and perform DFS for each query
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair<String, Double>>> map = buildGraph(equations, values);
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            Set<String> visited = new HashSet<>();
            res[i] = DFS(queries.get(i).get(0), queries.get(i).get(1), map, visited);
        }
        return res;
    }

    private double DFS(String source, String target, Map<String, List<Pair<String, Double>>> map, Set<String> visited) {
        if (!map.containsKey(source)) {
            return -1; //this works because their are no negative values
            //if the code needs to be generic, then you can change the return type to be pair<boolean, double>
        } else if (source.equals(target)) {
            return 1;
        } else if (!visited.contains(source)) {
            visited.add(source);
            for (Pair<String, Double> child : map.get(source)) {
                double cost = DFS(child.getKey(), target, map, visited);
                if (cost != -1.0) { //if it's a valid path
                    //Don't multiply the child.getValue()
                    return cost * child.getValue();
                }
            }
        }
        return -1;
    }

    private Map<String, List<Pair<String, Double>>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, List<Pair<String, Double>>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String first = equations.get(i).get(0);
            String second = equations.get(i).get(1);
            map.computeIfAbsent(first, __ -> new ArrayList<>()).add(new Pair<>(second, values[i]));
            map.computeIfAbsent(second, __ -> new ArrayList<>()).add(new Pair<>(first, 1 / values[i])); //inverse the relationship
        }
        map.forEach((key, value) -> value.add(new Pair<>(key, 1.0))); // self relationship
        return map;
    }
}
