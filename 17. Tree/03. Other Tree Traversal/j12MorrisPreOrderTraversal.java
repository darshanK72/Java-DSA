/**
 * LeetCode 144: Binary Tree Preorder Traversal (Morris Traversal)
 * 
 * Problem Statement:
 *     Given the root of a binary tree, return the preorder traversal of its nodes' values
 *     using Morris Traversal (constant space) approach.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - List containing preorder traversal of the tree
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     
 *     Output: [1, 2, 4, 5, 3]
 */

import java.util.ArrayList;
import java.util.List;

public class j12MorrisPreOrderTraversal {

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
     * - Process node before moving left (unlike inorder)
     * - Use threads to return to parent without stack
     * 
     * Example visualization:
     * Step 1:           Step 2:           Step 3:
     *     1                1                1
     *    /               /                /
     *   2      -->      2        -->     2
     *  /               /                /
     * 4               4                4
     *                  \                \
     *                   2                2 -> 1
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
     *       - Process current node
     *       - Move to left child
     *    c. If predecessor's right points to current:
     *       - Remove thread (predecessor.right = null)
     *       - Move to right child
     * 
     * Time Complexity: O(n) - each edge traversed at most 3 times
     * Space Complexity: O(1) - only uses constant extra space
     * 
     * @param root Root node of binary tree
     * @return List containing preorder traversal
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>(); // Store preorder traversal
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
                    curr = curr.right; // Move to right child
                } else { // No thread
                    prev.right = curr; // Create thread
                    out.add(curr.val); // Process current node
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
        System.out.println("Expected: [1, 2, 4, 5, 3]");
        System.out.println("Output: " + preorderTraversal(root1));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + preorderTraversal(null));

        // Test Case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 - Single node tree");
        System.out.println("Expected: [1]");
        System.out.println("Output: " + preorderTraversal(root3));
    }
}
