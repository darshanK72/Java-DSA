/**
 * Problem Statement:
 *     LeetCode 257. Binary Tree Paths
 * 
 *     Given the root of a binary tree, return all root-to-leaf paths in any order.
 *     A leaf is a node with no children. Each path should be returned as a string
 *     of node values separated by "->".
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - List of strings, each representing a root-to-leaf path
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *          \
 *           5
 *     Output: ["1->2->5", "1->3"]
 *     Explanation: There are two paths:
 *     1. Root -> Left -> Right: 1->2->5
 *     2. Root -> Right: 1->3
 */

import java.util.ArrayList;
import java.util.List;

public class j05BinaryTreePaths {

    /**
     * TreeNode class represents a node in binary tree
     * Contains value and references to left and right children
     */
    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree from example
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        System.out.println("Test Case 1: " + binaryTreePaths(root1));
        // Expected: ["1->2->5", "1->3"]

        // Test Case 2: Single node
        TreeNode root2 = new TreeNode(1);
        System.out.println("Test Case 2: " + binaryTreePaths(root2));
        // Expected: ["1"]

        // Test Case 3: Empty tree
        System.out.println("Test Case 3: " + binaryTreePaths(null));
        // Expected: []
    }

    /**
     * Approach: DFS with Path Building
     * 
     * Intuition:
     * - Use DFS to traverse tree from root to leaves
     * - Build path string while traversing
     * - Add complete path when leaf is reached
     * 
     * Time Complexity: O(n)
     * - Visit each node once
     * - String concatenation at each node
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - Each path string is O(h) length
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> out = new ArrayList<>();          // Store all paths
        if (root == null)
            return out;                                // Handle empty tree
        generateBinaryTreePaths(root, out, "");       // Generate paths
        return out;
    }

    /**
     * Helper method to generate paths using DFS
     * Builds path string while traversing and adds complete paths at leaves
     */
    public static void generateBinaryTreePaths(TreeNode root, List<String> list, String curr) {
        if (root == null)
            return;                                    // Base case
            
        if (root.left == null && root.right == null) {
            list.add(curr + root.val);                // Add path at leaf
            return;
        }
        
        String path = curr + root.val + "->";         // Build current path
        generateBinaryTreePaths(root.left, list, path);  // Process left subtree
        generateBinaryTreePaths(root.right, list, path); // Process right subtree
    }
}
