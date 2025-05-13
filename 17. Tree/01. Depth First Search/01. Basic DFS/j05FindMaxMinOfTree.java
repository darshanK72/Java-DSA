/**
 * Problem Statement:
 *     Find Maximum and Minimum Values in Binary Tree
 * 
 *     Given the root of a binary tree, find the maximum and minimum values present
 *     in the tree. The tree can contain any integer values.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can be empty or have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - Maximum and minimum values present in the tree
 * 
 * Example:
 *     Input: root = [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Output: 
 *     Max: 5
 *     Min: 1
 */

public class j05FindMaxMinOfTree {

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
        System.out.println("Max: " + findMax(root1));  // Expected: 5
        System.out.println("Min: " + findMin(root1));  // Expected: 1

        // Test Case 2: Tree with negative values
        Node root2 = new Node(-1);
        root2.left = new Node(-2);
        root2.right = new Node(-3);
        System.out.println("\nTest Case 2 (Negative Values):");
        System.out.println("Max: " + findMax(root2));  // Expected: -1
        System.out.println("Min: " + findMin(root2));  // Expected: -3

        // Test Case 3: Single node
        Node root3 = new Node(42);
        System.out.println("\nTest Case 3 (Single Node):");
        System.out.println("Max: " + findMax(root3));  // Expected: 42
        System.out.println("Min: " + findMin(root3));  // Expected: 42

        // Test Case 4: Empty tree
        System.out.println("\nTest Case 4 (Empty Tree):");
        System.out.println("Max: " + findMax(null));   // Expected: Integer.MIN_VALUE
        System.out.println("Min: " + findMin(null));   // Expected: Integer.MAX_VALUE
    }

    /**
     * Approach: Recursive Maximum Finding
     * 
     * Intuition:
     * - Maximum value is maximum of:
     *   * Current node's value
     *   * Maximum value in left subtree
     *   * Maximum value in right subtree
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - n is number of nodes in tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static int findMax(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int leftMax = findMax(root.left);        // Find max in left subtree
        int rightMax = findMax(root.right);      // Find max in right subtree
        return Math.max(Math.max(leftMax, rightMax), root.data);  // Return overall max
    }

    /**
     * Approach: Recursive Minimum Finding
     * 
     * Intuition:
     * - Minimum value is minimum of:
     *   * Current node's value
     *   * Minimum value in left subtree
     *   * Minimum value in right subtree
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - n is number of nodes in tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static int findMin(Node root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int leftMin = findMin(root.left);        // Find min in left subtree
        int rightMin = findMin(root.right);      // Find min in right subtree
        return Math.min(Math.min(leftMin, rightMin), root.data);  // Return overall min
    }
}