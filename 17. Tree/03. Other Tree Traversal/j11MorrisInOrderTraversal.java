/**
 * LeetCode 94: Binary Tree Inorder Traversal (Morris Traversal)
 * 
 * Problem Statement:
 *     Given the root of a binary tree, return the inorder traversal of its nodes' values
 *     using Morris Traversal (constant space) approach.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - List containing inorder traversal of the tree
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     
 *     Output: [4, 2, 5, 1, 3]
 */

import java.util.ArrayList;
import java.util.List;

public class j11MorrisInOrderTraversal {

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
     * Approach: Morris Traversal (Threaded Binary Tree)
     * 
     * Intuition:
     * - Create temporary links (threads) from predecessor to current node
     * - These threads help in backtracking without recursion/stack
     * - Each node visited twice: first to create thread, second to process
     * 
     * Example visualization:
     * Initial:          After threading:
     *     1                1
     *    /               /
     *   2      -->      2
     *  /               /
     * 4               4
     *                  \
     *                   2 -> 1
     * 
     * Explanation:
     * 1. If node has no left child:
     *    - Process current node
     *    - Move to right child
     * 
     * 2. If node has left child:
     *    a. Find predecessor (rightmost node in left subtree)
     *    b. If predecessor's right is null:
     *       - Create thread (predecessor.right = current)
     *       - Move to left child
     *    c. If predecessor's right points to current:
     *       - Remove thread (predecessor.right = null)
     *       - Process current node
     *       - Move to right child
     * 
     * Time Complexity: O(n) - each edge traversed at most 3 times
     * Space Complexity: O(1) - only uses constant extra space
     * 
     * @param root Root node of binary tree
     * @return List containing inorder traversal
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>(); // Store inorder traversal
        TreeNode curr = root; // Current node being processed

        while (curr != null) {
            if (curr.left == null) { // No left child
                out.add(curr.val); // Process current node
                curr = curr.right; // Move to right child
            } else {
                TreeNode prev = curr.left; // Find predecessor
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == curr) { // Thread exists
                    prev.right = null; // Remove thread
                    out.add(curr.val); // Process current node
                    curr = curr.right; // Move to right child
                } else { // No thread
                    prev.right = curr; // Create thread
                    curr = curr.left; // Move to left child
                }
            }
        }
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
        System.out.println("Expected: [4, 2, 5, 1, 3]");
        System.out.println("Output: " + inorderTraversal(root1));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + inorderTraversal(null));

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 - Single node tree");
        System.out.println("Expected: [1]");
        System.out.println("Output: " + inorderTraversal(root3));
    }
}
