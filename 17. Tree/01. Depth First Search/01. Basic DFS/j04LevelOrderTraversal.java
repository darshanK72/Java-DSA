/**
 * Problem Statement:
 *     LeetCode 102. Binary Tree Level Order Traversal
 * 
 *     Given the root of a binary tree, return the level order traversal of its nodes'
 *     values (i.e., from left to right, level by level).
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can be empty or have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - List of lists where each inner list contains nodes at same level
 * 
 * Example:
 *     Input: root = [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Output: [[1], [2,3], [4,5]]
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class j04LevelOrderTraversal {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String args[]) {
        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        System.out.println("Test Case 1 (Basic Tree):");
        System.out.println("Level Order Traversal: " + levelOrder(root1)); // Expected: [[1], [2, 3], [4, 5]]

        // Test Case 2: Single node tree
        TreeNode root2 = new TreeNode(1);
        System.out.println("\nTest Case 2 (Single Node):");
        System.out.println("Level Order Traversal: " + levelOrder(root2)); // Expected: [[1]]

        // Test Case 3: Empty tree
        System.out.println("\nTest Case 3 (Empty Tree):");
        System.out.println("Level Order Traversal: " + levelOrder(null)); // Expected: []

        // Test Case 4: Left-skewed tree
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        System.out.println("\nTest Case 4 (Left-skewed Tree):");
        System.out.println("Level Order Traversal: " + levelOrder(root4)); // Expected: [[1], [2], [3]]
    }

    /**
     * Approach: Level Order Traversal using Queue
     * 
     * Intuition:
     * - Use queue to process nodes level by level
     * - For each level:
     *   * Store all nodes at current level
     *   * Process their values
     *   * Add their children for next level
     * 
     * Algorithm:
     * 1. Initialize result list and queue
     * 2. Add root to queue if not null
     * 3. While queue not empty:
     *    - Get all nodes at current level
     *    - Process their values
     *    - Add their children to queue
     *    - Add level values to result
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Queue operations are O(1)
     * 
     * Space Complexity: O(w)
     * - w is maximum width of tree
     * - Queue stores at most w nodes at a time
     * - Best case O(1), worst case O(n/2) for last level
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>(); // Result list
        if (root == null)
            return list; // Handle empty tree

        Queue<TreeNode> queue = new LinkedList<>(); // Process nodes level by level
        queue.add(root); // Start with root

        while (!queue.isEmpty()) {
            ArrayList<TreeNode> levelNodes = new ArrayList<>(); // Store current level nodes

            // Get all nodes at current level
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                levelNodes.add(node);
            }

            ArrayList<Integer> levelInts = new ArrayList<>(); // Store level values

            // Process current level nodes
            for (TreeNode node : levelNodes) {
                if (node.left != null) {
                    queue.add(node.left); // Add left child for next level
                }
                if (node.right != null) {
                    queue.add(node.right); // Add right child for next level
                }
                levelInts.add(node.val); // Store current node's value
            }

            list.add(levelInts); // Add level to result
        }
        return list;
    }
}
