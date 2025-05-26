/**
 * LeetCode 559. Maximum Depth of N-ary Tree
 * 
 * Problem Statement:
 *     Given a n-ary tree, find its maximum depth. The maximum depth is the number
 *     of nodes along the longest path from the root node down to the farthest leaf
 *     node.
 * 
 * Input:
 *     - Node root (root node of N-ary tree)
 *     - Each node has a value and list of children nodes
 *     - Node values are in range [0, 10^4]
 *     - Number of nodes is in range [0, 10^4]
 * 
 * Output:
 *     - int (maximum depth of the tree)
 * 
 * Example:
 *     Input: root = [1,null,3,2,4,null,5,6]
 *     Output: 3
 * 
 *     Explanation:
 *           1
 *         / | \
 *        3  2  4      depth = 3
 *       / \
 *      5   6
 */

import java.util.ArrayList;
import java.util.List;

public class j04DepthOfNAryTree {
    
    /**
     * N-ary Tree Node Definition
     * Represents a node in an N-ary tree with a value and list of children.
     */
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * Finds maximum depth of N-ary tree
     * 
     * Intuition:
     * - Use DFS to explore all paths
     * - Track depth while traversing
     * - Use array to store maximum depth found
     * 
     * Explanation:
     * - Initialize max depth tracker array
     * - Start DFS with depth 1 at root
     * - Return maximum depth found
     * 
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(h) where h is height of tree (recursion stack)
     * 
     * @param root    Root node of N-ary tree
     * @return       Maximum depth of the tree
     */
    public static int maxDepth(Node root) {
        // Handle null tree case
        if (root == null) 
            return 0;
            
        // Initialize max depth tracker
        int[] max = new int[1];
        
        // Start DFS with depth 1
        depthOfNArrayTree(root, 1, max);
        
        return max[0];
    }

    /**
     * Helper method for finding maximum depth using DFS
     * 
     * Intuition:
     * - Traverse each path maintaining current depth
     * - Update max depth when leaf node is reached
     * - Process all children recursively
     * 
     * Explanation:
     * - Update max depth if current depth is greater
     * - Recursively process all children with increased depth
     * 
     * Time Complexity: O(n) for visiting each node once
     * Space Complexity: O(h) for recursion stack
     * 
     * @param root     Current node being processed
     * @param depth    Current depth in traversal
     * @param max      Array to track maximum depth
     */
    private static void depthOfNArrayTree(Node root, int depth, int[] max) {
        // Base case: null node
        if (root == null)
            return;
            
        // Update maximum depth if current is greater
        max[0] = Math.max(max[0], depth);
        
        // Process all children with incremented depth
        for (Node child : root.children) {
            depthOfNArrayTree(child, depth + 1, max);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic N-ary tree
        System.out.println("\nBasic N-ary Tree Test:");
        Node root1 = new Node(1);
        root1.children.add(new Node(3));
        root1.children.add(new Node(2));
        root1.children.add(new Node(4));
        root1.children.get(0).children.add(new Node(5));
        root1.children.get(0).children.add(new Node(6));
        System.out.println("Maximum Depth: " + maxDepth(root1));  // Expected: 3

        // Test Case 2: Single node
        System.out.println("\nSingle Node Test:");
        Node root2 = new Node(1);
        System.out.println("Maximum Depth: " + maxDepth(root2));  // Expected: 1

        // Test Case 3: Linear tree
        System.out.println("\nLinear Tree Test:");
        Node root3 = new Node(1);
        root3.children.add(new Node(2));
        root3.children.get(0).children.add(new Node(3));
        System.out.println("Maximum Depth: " + maxDepth(root3));  // Expected: 3

        // Test Case 4: Null tree
        System.out.println("\nNull Tree Test:");
        System.out.println("Maximum Depth: " + maxDepth(null));  // Expected: 0
    }
}
