/**
 * LeetCode 145: Binary Tree Postorder Traversal (Morris Traversal)
 * 
 * Problem Statement:
 *     Given the root of a binary tree, return the postorder traversal of its nodes' values
 *     using Morris Traversal (constant space) approach.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - List containing postorder traversal of the tree
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     
 *     Output: [4, 5, 2, 3, 1]
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class j13MorrisPostOrderTraversal {

    /**
     * TreeNode class representing each node in the binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach: Modified Morris Traversal with Reverse
     * 
     * Intuition:
     * - Modify Morris traversal to process right subtree first
     * - Collect nodes in reverse postorder (root->right->left)
     * - Reverse final list to get postorder (left->right->root)
     * 
     * Example visualization:
     * Original tree:     Threading steps:
     *     1                1                1
     *    / \              / \              / \
     *   2   3    -->     2   3    -->     2   3
     *  /                /                 /
     * 4               4                  4
     *                  \                  \
     *                   2                  2 -> 1
     * 
     * Explanation:
     * 1. If node has no right child:
     *    - Add current node to list
     *    - Move to left child
     * 
     * 2. If node has right child:
     *    a. Find predecessor (leftmost node in right subtree)
     *    b. If predecessor's left is null:
     *       - Create thread (predecessor.left = current)
     *       - Add current node to list
     *       - Move to right child
     *    c. If predecessor's left points to current:
     *       - Remove thread (predecessor.left = null)
     *       - Move to left child
     * 
     * 3. Finally, reverse the list to get postorder
     * 
     * Time Complexity: O(n) - each edge traversed at most 3 times
     * Space Complexity: O(1) - only uses constant extra space
     * 
     * @param root Root node of binary tree
     * @return List containing postorder traversal
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>(); // Store traversal result
        TreeNode curr = root; // Current node being processed

        while (curr != null) {
            if (curr.right == null) { // No right child
                out.add(curr.val); // Process current node
                curr = curr.left; // Move to left child
            } else {
                TreeNode prev = curr.right; // Find predecessor
                while (prev.left != null && prev.left != curr) {
                    prev = prev.left;
                }

                if (prev.left == curr) { // Thread exists
                    prev.left = null; // Remove thread
                    curr = curr.left; // Move to left child
                } else { // No thread
                    prev.left = curr; // Create thread
                    out.add(curr.val); // Process current node
                    curr = curr.right; // Move to right child
                }
            }
        }
        Collections.reverse(out); // Reverse to get postorder
        return out;
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        /**
         *           1
         *          / \
         *         2   3
         *        / \
         *       4   5
         */
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);

        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected: [4, 5, 2, 3, 1]");
        System.out.println("Output: " + postorderTraversal(root1));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + postorderTraversal(null));

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 - Single node tree");
        System.out.println("Expected: [1]");
        System.out.println("Output: " + postorderTraversal(root3));
    }
}
