/**
 * GeeksForGeeks: Boundary Traversal of Binary Tree
 * 
 * Problem Statement:
 *     Write a function to print Boundary Traversal of a binary tree. Boundary
 *     Traversal of a binary tree includes:
 *     1. Left boundary nodes (excluding leaves)
 *     2. All leaf nodes (from left to right)
 *     3. Right boundary nodes (excluding leaves) in reverse order
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - ArrayList containing boundary traversal of the tree
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \   \
 *       4   5   6
 *     
 *     Output: [1, 2, 4, 5, 6, 3]
 *     
 *     Explanation:
 *     - Left boundary: [1, 2]
 *     - Leaf nodes: [4, 5, 6]
 *     - Right boundary (reverse): [3]
 */

import java.util.ArrayList;

public class j06BoundryTreeTraversal {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    /**
     * Main method to test the implementation with multiple test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Normal tree
        /**
         *           1
         *          / \
         *         2   3
         *        / \   \
         *       4   5   6
         */
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.right.right = new Node(6);
        System.out.println("Test Case 1 - Normal tree");
        System.out.println("Expected: [1, 2, 4, 5, 6, 3]");
        System.out.println("Output: " + boundaryTraversal(root1));

        // Test Case 2: Complete binary tree
        /**
         *           1
         *          / \
         *         2   3
         *        / \ / \
         *       4  5 6  7
         */
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.left.right = new Node(5);
        root2.right.left = new Node(6);
        root2.right.right = new Node(7);
        System.out.println("\nTest Case 2 - Complete binary tree");
        System.out.println("Expected: [1, 2, 4, 5, 6, 7, 3]");
        System.out.println("Output: " + boundaryTraversal(root2));

        // Test Case 3: Left skewed tree
        /**
         *       1
         *      /
         *     2
         *    /
         *   3
         *  /
         * 4
         */
        Node root3 = new Node(1);
        root3.left = new Node(2);
        root3.left.left = new Node(3);
        root3.left.left.left = new Node(4);
        System.out.println("\nTest Case 3 - Left skewed tree");
        System.out.println("Expected: [1, 2, 3, 4]");
        System.out.println("Output: " + boundaryTraversal(root3));

        // Test Case 4: Complex tree with multiple levels
        /**
         *           1
         *          / \
         *         2   3
         *        /     \
         *       4       5
         *      / \     / \
         *     6   7   8   9
         *    /         \
         *   10         11
         */
        Node root4 = new Node(1);
        root4.left = new Node(2);
        root4.right = new Node(3);
        root4.left.left = new Node(4);
        root4.right.right = new Node(5);
        root4.left.left.left = new Node(6);
        root4.left.left.right = new Node(7);
        root4.right.right.left = new Node(8);
        root4.right.right.right = new Node(9);
        root4.left.left.left.left = new Node(10);
        root4.right.right.left.right = new Node(11);
        System.out.println("\nTest Case 4 - Complex tree");
        System.out.println("Expected: [1, 2, 4, 6, 10, 7, 11, 8, 9, 5, 3]");
        System.out.println("Output: " + boundaryTraversal(root4));
    }

    /**
     * Approach: Three-Step Boundary Traversal
     * 
     * Intuition:
     * - Boundary consists of three parts:
     *   1. Left boundary (top to bottom)
     *   2. Leaf nodes (left to right)
     *   3. Right boundary (bottom to top)
     * 
     * Time Complexity: O(n) - visit each node at most once
     * Space Complexity: O(n) - for storing result and recursion stack
     */
    public static ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> out = new ArrayList<>();
        if (root == null) return out;

        out.add(root.data);
        leftBoundary(root.left, out);
        leafNodes(root, out);
        rightBoundary(root.right, out);
    
        return out;
    }

    /**
     * Helper method to collect left boundary nodes (excluding leaves)
     */
    private static void leftBoundary(Node node, ArrayList<Integer> out) {
        if (node == null || (node.left == null && node.right == null)) return;
        out.add(node.data);
        if (node.left != null) {
            leftBoundary(node.left, out);
        } else {
            leftBoundary(node.right, out);
        }
    }

    /**
     * Helper method to collect right boundary nodes (excluding leaves)
     */
    private static void rightBoundary(Node node, ArrayList<Integer> out) {
        if (node == null || (node.left == null && node.right == null)) return;
        if (node.right != null) {
            rightBoundary(node.right, out);
        } else {
            rightBoundary(node.left, out);
        }
        out.add(node.data);
    }

    /**
     * Helper method to collect leaf nodes (left to right)
     */
    private static void leafNodes(Node node, ArrayList<Integer> out) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            out.add(node.data);
            return;
        }
        leafNodes(node.left, out);
        leafNodes(node.right, out);
    }
}
