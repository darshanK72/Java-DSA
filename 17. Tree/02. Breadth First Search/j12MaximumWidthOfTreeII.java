/**
 * LeetCode 662. Maximum Width of Binary Tree
 * 
 * Problem Statement:
 *     Given the root of a binary tree, return the maximum width of the given tree.
 *     The maximum width of a tree is the maximum width among all levels.
 *     The width of one level is defined as the length between the end-nodes
 *     (the leftmost and rightmost non-null nodes), where null nodes between
 *     the end-nodes that would be present in a complete binary tree extending
 *     down to that level are also counted into the length calculation.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - Maximum width of the binary tree
 * 
 * Example:
 *     Input: 
 *           1
 *          /  \
 *         3    2
 *        /      \
 *       5        9
 *     
 *     Output: 4
 *     
 *     Explanation:
 *     Level 0: Width = 1 (Node 1)
 *     Level 1: Width = 2 (Nodes 3, 2)
 *     Level 2: Width = 4 (Nodes 5, null, null, 9)
 *     Maximum width = 4
 */

import java.util.LinkedList;
import java.util.Queue;

public class j12MaximumWidthOfTreeII {
    
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
     * Pair class to store node and its index for level order traversal
     */
    static class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    /**
     * Main method to test the implementation with multiple test cases
     */
    public static void main(String[] args) {
        // Test Case 1: Example from problem statement
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        root1.right.right = new TreeNode(9);
        System.out.println("Test Case 1 - Example tree");
        System.out.println("Expected: 4, Output: " + widthOfBinaryTree(root1));

        // Test Case 2: Perfect binary tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        System.out.println("\nTest Case 2 - Perfect binary tree");
        System.out.println("Expected: 4, Output: " + widthOfBinaryTree(root2));

        // Test Case 3: Left skewed tree
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(3);
        root3.left.left.left = new TreeNode(4);
        System.out.println("\nTest Case 3 - Left skewed tree");
        System.out.println("Expected: 1, Output: " + widthOfBinaryTree(root3));

        // Test Case 4: Tree with gaps
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);
        root4.left.right = new TreeNode(4);
        root4.right.right = new TreeNode(5);
        System.out.println("\nTest Case 4 - Tree with gaps");
        System.out.println("Expected: 2, Output: " + widthOfBinaryTree(root4));

        // Test Case 5: Empty tree
        System.out.println("\nTest Case 5 - Empty tree");
        System.out.println("Expected: 0, Output: " + widthOfBinaryTree(null));

        // Test Case 6: Single node tree
        TreeNode root6 = new TreeNode(1);
        System.out.println("\nTest Case 6 - Single node tree");
        System.out.println("Expected: 1, Output: " + widthOfBinaryTree(root6));
    }

    /**
     * Approach: Level Order Traversal with Indexing
     * 
     * Intuition:
     * - Use level order traversal with node indexing
     * - For a node at index i:
     *   * Left child index = 2i + 1
     *   * Right child index = 2i + 2
     * - To prevent integer overflow:
     *   * Normalize indices at each level by subtracting minimum index
     * 
     * Explanation:
     * 1. Use queue to store pairs of (node, index)
     * 2. For each level:
     *    - Get minimum index of level (for normalization)
     *    - Track first and last node indices
     *    - Calculate width as (lastIdx - firstIdx + 1)
     *    - Add children with calculated indices
     * 
     * 3. Key points:
     *    - Index normalization prevents overflow
     *    - Width includes null nodes between end nodes
     *    - Complete binary tree indexing property used
     * 
     * Time Complexity:
     * - O(n) where n is the number of nodes in the tree
     * - Visit each node exactly once
     * 
     * Space Complexity:
     * - O(w) where w is the maximum width of the tree
     * - Queue stores at most width of the tree at any level
     * 
     * @param root The root node of the binary tree
     * @return Maximum width of the binary tree
     */
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));    // Add root with index 0
        int maxWidth = 0;                 // Track maximum width seen so far

        while (!queue.isEmpty()) {
            int size = queue.size();      // Number of nodes at current level
            int minIdx = queue.peek().index;  // Leftmost index at current level
            int firstIdx = 0;             // Will store first node's normalized index
            int lastIdx = 0;              // Will store last node's normalized index

            // Process all nodes at current level
            for (int i = 0; i < size; i++) {
                // Normalize current index by subtracting minimum index of level
                int currIdx = queue.peek().index - minIdx;
                TreeNode node = queue.poll().node;

                if (i == 0)               // If first node of level
                    firstIdx = currIdx;
                if (i == size - 1)        // If last node of level
                    lastIdx = currIdx;

                // Add left child with index 2*i + 1
                if (node.left != null) {
                    queue.add(new Pair(node.left, currIdx * 2 + 1));
                }
                // Add right child with index 2*i + 2
                if (node.right != null) {
                    queue.add(new Pair(node.right, currIdx * 2 + 2));
                }
            }
            // Update maxWidth if current level is wider
            maxWidth = Math.max(maxWidth, lastIdx - firstIdx + 1);
        }
        return maxWidth;
    }
}
