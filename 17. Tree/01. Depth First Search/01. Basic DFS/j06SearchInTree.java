/**
 * Problem Statement:
 *     Search in Binary Tree
 * 
 *     Given a binary tree and a target value, determine if the target value exists
 *     in the tree. Return true if the value is found, false otherwise.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Target value to search for (integer)
 *     - Tree can be empty or have any number of nodes
 * 
 * Output:
 *     - Boolean indicating if target value exists in tree
 * 
 * Example:
 *     Input: 
 *     Tree:
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Target: 3
 *     Output: true
 */

public class j06SearchInTree {

    /**
     * Node class represents a node in binary tree
     * Each node contains data and references to left and right children
     */
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int x) {
            data = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        System.out.println("Test Case 1 (Basic Tree):");
        System.out.println("Node 3 exists: " + isNodeExists(root1, 3));  // Expected: true
        System.out.println("Node 6 exists: " + isNodeExists(root1, 6));  // Expected: false

        // Test Case 2: Single node
        Node root2 = new Node(42);
        System.out.println("\nTest Case 2 (Single Node):");
        System.out.println("Node 42 exists: " + isNodeExists(root2, 42));  // Expected: true
        System.out.println("Node 100 exists: " + isNodeExists(root2, 100)); // Expected: false

        // Test Case 3: Empty tree
        System.out.println("\nTest Case 3 (Empty Tree):");
        System.out.println("Node 1 exists: " + isNodeExists(null, 1));   // Expected: false
    }

    /**
     * Approach: Recursive Tree Search
     * 
     * Intuition:
     * - Base cases:
     *   * If tree is empty, return false
     *   * If current node has target value, return true
     * - Recursively search in both left and right subtrees
     * - Return true if target found in either subtree
     * 
     * Time Complexity: O(n)
     * - Worst case: need to visit all nodes
     * - n is number of nodes in tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - Worst case O(n) for skewed tree
     * - Best case O(log n) for balanced tree
     */
    public static boolean isNodeExists(Node root, int target) {
        // Base case: if the tree is empty or we find the target
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        }

        // Recursively search in left and right subtrees
        return isNodeExists(root.left, target) || isNodeExists(root.right, target);
    }
}