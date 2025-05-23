/**
 * LeetCode 199: Binary Tree Right Side View
 * 
 * Problem Statement:
 *     Given the root of a binary tree, imagine yourself standing on the right side
 *     of it. Return the values of the nodes you can see ordered from top to bottom.
 * 
 * Input:
 *     - Root node of a binary tree
 * 
 * Output:
 *     - List containing nodes visible from right side
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *          \   \
 *           5   4
 *     
 *     Output: [1, 3, 4]
 *     
 *     Explanation:
 *     Level 0: 1 is visible
 *     Level 1: 3 is visible (blocks 2)
 *     Level 2: 4 is visible (blocks 5)
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class j08RightViewOfBinaryTree {

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
     * Approach: Level Order Traversal (BFS)
     * 
     * Intuition:
     * - Right view contains rightmost node at each level
     * - Use BFS to process tree level by level
     * - Last node at each level is part of right view
     * 
     * Example visualization:
     *           1*         Level 0: 1 (rightmost)
     *          / \
     *         2   3*      Level 1: 3 (rightmost)
     *          \   \
     *           5   4*    Level 2: 4 (rightmost)
     *     
     *     * indicates nodes in right view
     * 
     * Explanation:
     * 1. Use queue for level order traversal
     * 2. At each level:
     *    - Process all nodes at current level
     *    - Add their children for next level
     *    - Last node is part of right view
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(w) - w is maximum width of tree
     * 
     * @param root Root node of binary tree
     * @return List containing right view nodes
     */
    public static List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> out = new ArrayList<>();             // Store right view nodes
        if(root == null) return out;                           // Handle empty tree

        Queue<TreeNode> queue = new LinkedList<>();            // Queue for BFS
        queue.add(root);                                       // Start with root

        while(!queue.isEmpty()) {
            int s = queue.size();                              // Nodes at current level

            for(int i = 0; i < s; i++) {
                TreeNode node = queue.poll();                  // Get next node
                if(i == s - 1) out.add(node.val);             // Add last node of level

                if(node.left != null) {                        // Add left child
                    queue.add(node.left);
                }
                if(node.right != null) {                       // Add right child
                    queue.add(node.right);
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
         *          \   \
         *           5   4
         */
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);
        
        System.out.println("Test Case 1 - Regular binary tree");
        System.out.println("Expected: [1, 3, 4]");
        System.out.println("Output: " + rightSideView(root1));

        // Test Case 2: Left skewed tree
        /**
         *       1
         *      /
         *     2
         *    /
         *   3
         */
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        
        System.out.println("\nTest Case 2 - Left skewed tree");
        System.out.println("Expected: [1, 2, 3]");
        System.out.println("Output: " + rightSideView(root2));

        // Test Case 3: Empty tree
        System.out.println("\nTest Case 3 - Empty tree");
        System.out.println("Expected: []");
        System.out.println("Output: " + rightSideView(null));
    }
}
