/**
 * Problem Statement:
 *     Ancestors in Binary Tree
 * 
 *     Given a Binary Tree and a target node value, find all ancestors of the target
 *     node in the tree. An ancestor is any node that appears above the target node
 *     on the path from root to target.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Target value to find ancestors for
 *     - Tree can contain any number of nodes
 * 
 * Output:
 *     - ArrayList containing all ancestors in order from leaf to root
 * 
 * Example:
 *     Input: 
 *     Tree:
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Target: 4
 *     Output: [2, 1]
 *     Explanation: Path from root to 4 is 1->2->4, so ancestors are [2, 1]
 */

import java.util.ArrayList;

public class j02AncestorsInBinaryTree {

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

    public static void main(String args[]) {
        // Test Case 1: Basic tree
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        System.out.println("Test Case 1 (Target = 4):");
        System.out.println("Ancestors: " + Ancestors(root1, 4));  // Expected: [2, 1]

        // Test Case 2: Target is root
        System.out.println("\nTest Case 2 (Target = 1):");
        System.out.println("Ancestors: " + Ancestors(root1, 1));  // Expected: []

        // Test Case 3: Target not in tree
        System.out.println("\nTest Case 3 (Target = 6):");
        System.out.println("Ancestors: " + Ancestors(root1, 6));  // Expected: []

        // Test Case 4: Empty tree
        System.out.println("\nTest Case 4 (Empty Tree):");
        System.out.println("Ancestors: " + Ancestors(null, 1));   // Expected: []
    }

    /**
     * Approach: DFS with Path Building
     * 
     * Intuition:
     * - Use DFS to find path to target
     * - Add nodes to result list while backtracking
     * - Only add node if target found in its subtree
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - n is number of nodes in tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - ArrayList stores at most h-1 ancestors
     */
    public static ArrayList<Integer> Ancestors(Node root, int target) {
        ArrayList<Integer> out = new ArrayList<>();
        getAncestors(root, target, out);
        return out;
    }

    /**
     * Helper method to find ancestors using DFS
     * Returns true if target found in current subtree
     */
    public static boolean getAncestors(Node root, int target, ArrayList<Integer> out) {
        if (root == null)
            return false;                           // Empty tree
        if (root.data == target)
            return true;                           // Found target
            
        boolean left = getAncestors(root.left, target, out);   // Check left subtree
        boolean right = getAncestors(root.right, target, out); // Check right subtree
        
        if (left || right) {
            out.add(root.data);                    // Add current node as ancestor
            return true;                           // Propagate found status
        }
        return false;                              // Target not found in subtree
    }
}
