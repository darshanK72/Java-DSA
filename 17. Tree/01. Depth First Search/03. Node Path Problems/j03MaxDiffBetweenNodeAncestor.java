/**
 * Problem Statement:
 *     Maximum Difference Between Node and its Ancestor
 *     (GeeksForGeeks)
 * 
 *     Given a Binary Tree, find the maximum difference between any node and its ancestor.
 *     The difference between two nodes is defined as: ancestor_value - node_value.
 *     For each node, we need to find the maximum such difference considering all its ancestors.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be negative
 * 
 * Output:
 *     - Maximum difference between any node and its ancestor
 * 
 * Example 1:
 *     Input: 
 *           5
 *          / \
 *         2   1
 *     Output: 4
 *     Explanation: Maximum difference is between 5 and 1 (5-1 = 4)
 * 
 * Example 2:
 *     Input:
 *           2
 *          / \
 *         7   5
 *            / \
 *           1   3
 *     Output: 6
 *     Explanation: Maximum difference is between 7 and 1 (7-1 = 6)
 */

public class j03MaxDiffBetweenNodeAncestor {

    /**
     * Node class represents a node in binary tree
     * Contains data and references to left and right children
     */
    static class Node {
        int data;
        Node left, right;

        Node(int key) {
            data = key;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: From GeeksForGeeks example
        Node root1 = new Node(2);
        root1.left = new Node(7);
        root1.right = new Node(5);
        root1.right.left = new Node(1);
        root1.right.right = new Node(3);
        System.out.println("Test Case 1: " + maxDiff(root1));  // Expected: 6

        // Test Case 2: Simple tree
        Node root2 = new Node(5);
        root2.left = new Node(2);
        root2.right = new Node(1);
        System.out.println("Test Case 2: " + maxDiff(root2));  // Expected: 4

        // Test Case 3: Single node
        Node root3 = new Node(1);
        System.out.println("Test Case 3: " + maxDiff(root3));  // Expected: 0
    }

    /**
     * Approach: DFS with Minimum Value Tracking
     * 
     * Intuition:
     * - For each subtree, track minimum value in that subtree
     * - At each node, calculate difference with minimum from subtrees
     * - Update global maximum difference
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - n is number of nodes in tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static int maxDiff(Node root) {
        if (root == null)
            return 0;                           // Empty tree
            
        int[] diff = new int[1];               // Store max difference
        diff[0] = Integer.MIN_VALUE;           // Initialize with smallest value
        maxAncestorDiff(root, diff);          // Find max difference
        return diff[0];
    }

    /**
     * Helper method to find maximum difference using DFS
     * Returns minimum value in current subtree
     */
    public static int maxAncestorDiff(Node root, int[] diff) {
        if (root == null)
            return Integer.MAX_VALUE;          // Return max value for null node
            
        // Get minimum values from subtrees
        int leftMin = maxAncestorDiff(root.left, diff);
        int rightMin = maxAncestorDiff(root.right, diff);
        
        // Find minimum value considering both subtrees
        int min = Math.min(leftMin, rightMin);
        
        // Update maximum difference if current difference is larger
        if (min != Integer.MAX_VALUE) {
            diff[0] = Math.max(diff[0], root.data - min);
        }
        
        // Return minimum value including current node
        return Math.min(root.data, min);
    }
}
