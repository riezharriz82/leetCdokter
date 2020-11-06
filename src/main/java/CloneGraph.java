import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/clone-graph/
 * <p>
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 */
public class CloneGraph {
    /**
     * Approach: As it's a graph, you will find cycles, to avoid going into infinite loop, store the cloned version of the source node in a map
     * If you see a node that is already present in the map i.e. it's already cloned, this means we have found a cycle
     * and can simply return the cloned node from the map itself
     * <p>
     * Since we store the reference of the cloned node in the map, it works.
     */
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return DFS(node, map);
    }

    private Node DFS(Node actual, Map<Node, Node> map) {
        if (actual == null) {
            return null;
        }
        if (map.containsKey(actual)) { //cycle found, return the reference of the clone previously created
            return map.get(actual);
        }
        Node cloned = new Node(actual.val);
        map.put(actual, cloned);
        ArrayList<Node> clonedNeigbors = new ArrayList<>();
        for (Node neigbor : actual.neighbors) {
            clonedNeigbors.add(DFS(neigbor, map));
        }
        cloned.neighbors = clonedNeigbors;
        return cloned;
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
