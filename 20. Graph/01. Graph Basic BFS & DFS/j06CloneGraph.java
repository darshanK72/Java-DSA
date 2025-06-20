/**
 * LeetCode 133. Clone Graph
 *
 * Problem Statement:
 *     Given a reference of a node in a connected undirected graph, return a deep
 *     copy (clone) of the graph. Each node contains an integer value and a list
 *     of its neighbors.
 *
 * Input:
 *     - node: Node, reference to a node in the graph (1 <= Node.val <= 100)
 *
 * Output:
 *     - Node: Reference to the cloned graph's corresponding node
 *
 * Example:
 *     Input: Node with structure 1 -- 2
 *                              |    |
 *                              4 -- 3
 *     Output: Deep copy of the above graph
 *
 *     Explanation:
 *     Each node and its neighbors are cloned, and the structure is preserved.
 */

import java.util.*;

public class j06CloneGraph {

    /**
     * Node definition for undirected graph
     */
    static class Node {
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
    
    /**
     * Approach: DFS with HashMap (Deep Copy)
     *
     * Intuition:
     * - Use DFS to traverse the graph and clone each node.
     * - Use a HashMap to map original nodes to their clones to avoid cycles and
     *   duplicate nodes.
     *
     * Explanation:
     * - If input node is null, return null.
     * - Create a new node for the input node and map it.
     * - Recursively clone all neighbors, using the map to avoid revisiting.
     * - For each neighbor, if not cloned, create and recurse; else, use the map.
     *
     * Time Complexity: O(N), where N is the number of nodes (each node visited once).
     * Space Complexity: O(N) for the map and recursion stack.
     *
     * @param node   Reference to a node in the graph
     * @return      Reference to the cloned node
     */
    public static Node cloneGraph(Node node) {
        if (node == null)
            return null; // Handle null input
        Node outNode = new Node(node.val); // Clone the root node
        HashMap<Node, Node> map = new HashMap<>(); // Map original to clone
        map.put(node, outNode);
        deepClone(node, outNode, map);
        return outNode;
    }

    /**
     * Helper Method: Deep Clone using DFS
     *
     * Intuition:
     * - Recursively clone all neighbors, using a map to avoid cycles.
     *
     * Explanation:
     * - For each neighbor, if not cloned, create and recurse.
     * - If already cloned, add the mapped node to neighbors.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     *
     * @param node      Original node
     * @param outNode   Cloned node
     * @param map       Map from original to cloned nodes
     */
    private static void deepClone(Node node, Node outNode, HashMap<Node, Node> map) {
        for (Node neb : node.neighbors) {
            if (!map.containsKey(neb)) {
                Node newNode = new Node(neb.val); // Clone neighbor
                map.put(neb, newNode);
                outNode.neighbors.add(newNode); // Add to neighbors
                deepClone(neb, newNode, map); // Recurse
            } else {
                outNode.neighbors.add(map.get(neb)); // Use already cloned node
            }
        }
    }

    public static void main(String[] args) {
        // Basic Test Case: 1 -- 2
        //                  |    |
        //                  4 -- 3
        System.out.println("\nBasic Test Case:");
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors.add(n2); n1.neighbors.add(n4);
        n2.neighbors.add(n1); n2.neighbors.add(n3);
        n3.neighbors.add(n2); n3.neighbors.add(n4);
        n4.neighbors.add(n1); n4.neighbors.add(n3);
        Node clone = cloneGraph(n1);
        System.out.println("Original Node: " + n1.val + ", Cloned Node: " + clone.val);
        System.out.println("Original Neighbors: " + n1.neighbors.size() + ", Cloned Neighbors: " + clone.neighbors.size());

        // Edge Case: null input
        System.out.println("\nEdge Case:");
        System.out.println("Input: null, Expected: null, Output: " + cloneGraph(null));

        // Boundary Case: Single node, no neighbors
        System.out.println("\nBoundary Case:");
        Node single = new Node(10);
        Node singleClone = cloneGraph(single);
        System.out.println("Input: Node(10), Expected: 10, Output: " + (singleClone != null ? singleClone.val : null));

        // Special Case: Node with self-loop
        System.out.println("\nSpecial Case:");
        Node self = new Node(5);
        self.neighbors.add(self);
        Node selfClone = cloneGraph(self);
        System.out.println("Input: Node(5) with self-loop, Output: " + (selfClone != null ? selfClone.val : null));
        System.out.println("Self-loop in clone: " + (selfClone.neighbors.contains(selfClone)));
    }
}