/**
 * LeetCode 958. Check Completeness of a Binary Tree
 * 
 * Problem Statement:
 *     Given the root of a binary tree, determine if it is a complete binary tree.
 *     In a complete binary tree, every level except possibly the last is completely
 *     filled, and all nodes in the last level are as far left as possible.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - true if the tree is complete, false otherwise
 * 
 * Example:
 *     Input: 
 *           1
 *          /  \
 *         2    3
 *        / \  /
 *       4   5 6
 *     
 *     Output: true
 *     
 *     Explanation:
 *     - Every level except last is filled
 *     - Last level nodes are as far left as possible
 *     - Therefore, it's a complete binary tree
 */

import java.util.LinkedList;
import java.util.Queue;

public class j13CompletenessOfTree {

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
     * Main method to test the implementation with multiple test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Complete binary tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        System.out.println("Test Case 1 - Complete binary tree");
        System.out.println("Expected: true");
        System.out.println("BFS Output: " + isCompleteTreeBFS(root1));
        System.out.println("DFS Output: " + isCompleteTreeDFS(root1));

        // Test Case 2: Incomplete binary tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        System.out.println("\nTest Case 2 - Incomplete binary tree");
        System.out.println("Expected: false");
        System.out.println("BFS Output: " + isCompleteTreeBFS(root2));
        System.out.println("DFS Output: " + isCompleteTreeDFS(root2));

        // Test Case 3: Empty tree
        System.out.println("\nTest Case 3 - Empty tree");
        System.out.println("Expected: true");
        System.out.println("BFS Output: " + isCompleteTreeBFS(null));
        System.out.println("DFS Output: " + isCompleteTreeDFS(null));

        // Test Case 4: Single node tree
        TreeNode root4 = new TreeNode(1);
        System.out.println("\nTest Case 4 - Single node tree");
        System.out.println("Expected: true");
        System.out.println("BFS Output: " + isCompleteTreeBFS(root4));
        System.out.println("DFS Output: " + isCompleteTreeDFS(root4));
    }

    /**
     * Approach 1: BFS (Level Order Traversal)
     * 
     * Intuition:
     * - In a complete binary tree, after encountering a null node,
     *   all subsequent nodes must also be null
     * - Use level order traversal to process nodes level by level
     * - Track if we've seen a null node
     * 
     * Explanation:
     * 1. Use queue for level order traversal
     * 2. Add both left and right children (even if null)
     * 3. Once we find a null node:
     *    - Set flag indicating null found
     *    - Any non-null node after this means tree is incomplete
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(w) - w is maximum width of tree
     * 
     * @param root Root node of binary tree
     * @return true if tree is complete, false otherwise
     */
    public static boolean isCompleteTreeBFS(TreeNode root) {
        if (root == null) // Empty tree is complete
            return true;

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for level order traversal
        queue.add(root); // Add root to start traversal
        boolean foundNullNodeBefore = false; // Flag to track if null node found

        while (!queue.isEmpty()) {
            int s = queue.size(); // Number of nodes at current level

            for (int i = 0; i < s; i++) {
                TreeNode node = queue.poll(); // Get next node from queue

                if (node == null) {
                    foundNullNodeBefore = true; // Mark that we found a null node
                } else {
                    // If we find a non-null node after a null node, tree is incomplete
                    if (foundNullNodeBefore)
                        return false;
                    queue.add(node.left); // Add left child (can be null)
                    queue.add(node.right); // Add right child (can be null)
                }
            }
        }
        return true; // All levels are properly filled
    }

    /**
     * Approach 2: DFS with Node Indexing
     * 
     * Intuition:
     * - In a complete binary tree, node indices follow a pattern
     * - For node at index i:
     *   * Left child index = 2i
     *   * Right child index = 2i + 1
     * - All indices should be consecutive from 1 to total nodes
     * 
     * Explanation:
     * 1. Count total nodes in tree
     * 2. Use DFS with node indexing:
     *    - If index > total nodes, tree is incomplete
     *    - Recursively check left and right subtrees
     * 
     * Time Complexity: O(n) - visit each node twice
     * Space Complexity: O(h) - h is height of tree
     * 
     * @param root Root node of binary tree
     * @return true if tree is complete, false otherwise
     */
    public static boolean isCompleteTreeDFS(TreeNode root) {
        int totalNodes = countNodes(root); // First count total nodes
        return completeTree(root, 1, totalNodes); // Check completeness with indexing
    }

    /**
     * Helper method to count total nodes in the tree
     */
    public static int countNodes(TreeNode root) {
        if (root == null) // Base case: empty subtree
            return 0;
        // Count nodes in left subtree + right subtree + current node
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * Helper method to check if tree is complete using node indices
     */
    public static boolean completeTree(TreeNode root, int index, int totalNodes) {
        if (root == null) // Empty subtree is valid
            return true;
        if (index > totalNodes) // Index exceeds total nodes: incomplete
            return false;

        // Check both subtrees:
        // Left child at index 2*i, right child at index 2*i + 1
        return completeTree(root.left, index * 2, totalNodes) &&
                completeTree(root.right, index * 2 + 1, totalNodes);
    }
}
