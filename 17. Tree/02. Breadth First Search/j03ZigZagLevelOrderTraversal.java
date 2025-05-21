/**
 * Problem Statement:
 *     LeetCode 103. Binary Tree Zigzag Level Order Traversal
 * 
 *     Given the root of a binary tree, return the zigzag level order traversal of its
 *     nodes' values. (i.e., from left to right, then right to left for the next level
 *     and alternate between).
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - List of lists containing node values in zigzag level order
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         9   20
 *            /  \
 *           15   7
 *     
 *     Output: [[3], [20,9], [15,7]]
 *     Explanation:
 *     Level 0: Left to right  -> [3]
 *     Level 1: Right to left -> [20,9]
 *     Level 2: Left to right  -> [15,7]
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class j03ZigZagLevelOrderTraversal {
    
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    
    public static void main(String[] args) {
        // Test Case 1: Normal tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        
        System.out.println("Test Case 1: " + 
            zigzagLevelOrder(root1));  // Expected: [[3], [20,9], [15,7]]

        // Test Case 2: Single level tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        System.out.println("Test Case 2: " + 
            zigzagLevelOrder(root2));  // Expected: [[1], [3,2]]
    }

    /**
     * BFS with Level Reversal Approach
     * 
     * Intuition:
     * 1. Use standard BFS to traverse level by level
     * 2. Keep track of level number
     * 3. Reverse alternate levels after collection
     * 
     * Algorithm:
     * 1. Use queue for level-wise traversal
     * 2. Process each level normally (left to right)
     * 3. After collecting level, check if it needs reversal
     * 4. Reverse odd-numbered levels for zigzag pattern
     * 
     * Time Complexity: O(n)
     * - BFS traversal: O(n)
     * - Level reversal: O(w) where w is max width
     * 
     * Space Complexity: O(n)
     * - Queue storage: O(w) where w is max width
     * - Result storage: O(n)
     * 
     * @param root Root node of binary tree
     * @return List of lists with values in zigzag order
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) 
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            ArrayList<Integer> levelNodes = new ArrayList<>();
            int levelSize = queue.size();

            // Process current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelNodes.add(node.val);

                // Add children for next level
                if (node.left != null) 
                    queue.add(node.left);
                if (node.right != null) 
                    queue.add(node.right);
            }
            
            // Reverse alternate levels
            if (result.size() % 2 == 1) {
                Collections.reverse(levelNodes);
            }
            result.add(levelNodes);
        }
        return result;
    }
}
