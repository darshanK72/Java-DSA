/**
 * LeetCode 919. Complete Binary Tree Inserter
 * 
 * Problem Statement:
 *     A complete binary tree inserter class CBTInserter maintains a complete binary
 *     tree and supports two operations:
 *     1. Insert a new node with given value at the end of the tree
 *     2. Get the root node of the tree
 * 
 * Input:
 *     - Operations: ["CBTInserter", "insert", "insert", "get_root"]
 *     - Values: [[1], [2], [3], []]
 * 
 * Output:
 *     - Results of each operation: [null, 1, 1, [1,2,3]]
 * 
 * Example:
 *     Input: 
 *           1         insert(2)       1
 *                      ------>       /
 *                                   2
 *     
 *                    insert(3)       1
 *                      ------>      / \
 *                                  2   3
 *     
 *     Output: [null, 1, 1, [1,2,3]]
 *     
 *     Explanation:
 *     - CBTInserter(1): Initialize with root node value 1
 *     - insert(2): Return 1 (parent of 2)
 *     - insert(3): Return 1 (parent of 3)
 *     - get_root(): Return entire tree [1,2,3]
 */

import java.util.LinkedList;
import java.util.Queue;

public class j14CompleteBinaryTreeInsertor {

    /**
     * CBTInserter class to maintain complete binary tree
     */
    static class CBTInserter {

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

        TreeNode root; // Root of the tree
        Queue<TreeNode> queue; // Queue to track potential parent nodes

        /**
         * Initialize CBTInserter with root node
         * Time Complexity: O(n) - may need to traverse all nodes
         * Space Complexity: O(w) - w is maximum width of tree
         */
        public CBTInserter(TreeNode root) {
            this.root = root; // Store root node
            this.queue = new LinkedList<>(); // Initialize queue
            this.queue.add(this.root); // Add root to queue
            lastInsertPos(this.root); // Find last insertion position
        }

        /**
         * Helper method to find the last position where we can insert
         * Maintains queue with nodes that can accept new children
         */
        public void lastInsertPos(TreeNode root) {
            while (!this.queue.isEmpty()) {
                TreeNode node = queue.peek(); // Get potential parent node

                if (node.left != null) { // If left child exists
                    this.queue.add(node.left); // Add it to queue
                }
                if (node.right != null) { // If right child exists
                    this.queue.add(node.right); // Add it to queue
                    this.queue.poll(); // Remove current node as it's full
                }
                // Stop if we find a node with space for children
                if (node.left == null || node.right == null)
                    return;
            }
        }

        /**
         * Insert new node with given value
         * Returns the value of parent node where insertion happened
         * Time Complexity: O(1) - constant time operation
         * Space Complexity: O(1) - no extra space needed
         */
        public int insert(int val) {
            TreeNode newNode = new TreeNode(val); // Create new node
            this.queue.add(newNode); // Add to queue for future insertions
            TreeNode node = queue.peek(); // Get parent node

            if (node.left == null) { // If left is empty
                node.left = newNode; // Insert as left child
            } else {
                node.right = newNode; // Insert as right child
                queue.poll(); // Remove parent as it's now full
            }
            return node.val; // Return parent's value
        }

        /**
         * Get root node of the tree
         * Time Complexity: O(1) - constant time operation
         */
        public TreeNode get_root() {
            return this.root;
        }
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Basic operations
        CBTInserter.TreeNode root = new CBTInserter.TreeNode(1);
        CBTInserter cbt = new CBTInserter(root);

        System.out.println("Test Case 1 - Basic operations");
        System.out.println("Insert 2: " + cbt.insert(2)); // Should print 1
        System.out.println("Insert 3: " + cbt.insert(3)); // Should print 1
        System.out.println("Insert 4: " + cbt.insert(4)); // Should print 2

        // Test Case 2: Empty tree
        CBTInserter.TreeNode emptyRoot = null;
        CBTInserter cbt2 = new CBTInserter(emptyRoot);

        System.out.println("\nTest Case 2 - Empty tree");
        System.out.println("Insert 1: " + cbt2.insert(1));
    }
}
