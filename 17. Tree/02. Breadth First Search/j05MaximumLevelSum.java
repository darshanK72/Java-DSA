/**
 * Problem Statement:
 *     LeetCode 1161. Maximum Level Sum of a Binary Tree
 * 
 *     Given the root of a binary tree, return the level (1-indexed) with maximum sum.
 *     If there are multiple levels with the same maximum sum, return the smallest level.
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - Level number with maximum sum (1-indexed)
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         7   0
 *        /     \
 *       7      -8
 *     
 *     Output: 2
 *     Explanation: 
 *     Level 1: sum = 1
 *     Level 2: sum = 7 + 0 = 7
 *     Level 3: sum = 7 + (-8) = -1
 *     So level 2 has maximum sum
 */

import java.util.LinkedList;
import java.util.Queue;

public class j05MaximumLevelSum {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Normal tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(0);
        root1.left.left = new TreeNode(7);
        root1.right.right = new TreeNode(-8);
        
        System.out.println("Test Case 1: " + 
            maxLevelSum(root1));  // Expected: 2

        // Test Case 2: Single path with negative values
        TreeNode root2 = new TreeNode(-1);
        root2.left = new TreeNode(-2);
        root2.left.left = new TreeNode(-3);
        
        System.out.println("Test Case 2: " + 
            maxLevelSum(root2));  // Expected: 1
    }

    /**
     * BFS with Level Sum Tracking Approach
     * 
     * Intuition:
     * 1. Use BFS to process level by level
     * 2. Calculate sum for each level
     * 3. Track maximum sum and its level
     * 4. Handle negative values with MIN_VALUE initialization
     * 
     * Algorithm:
     * 1. Initialize queue with root
     * 2. For each level:
     *    - Process all nodes in current level
     *    - Calculate level sum
     *    - Update maximum if needed
     * 3. Return level with maximum sum
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(w)
     * - w is maximum width of tree (queue storage)
     * 
     * @param root Root node of binary tree
     * @return Level number with maximum sum
     */
    public static int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int maxSum = Integer.MIN_VALUE;
        int currentLevel = 1;
        int maxSumLevel = 1;
        
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int levelSum = 0;
            int levelSize = queue.size();
            
            // Process current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;

                // Add children for next level
                if (node.left != null) 
                    queue.add(node.left);
                if (node.right != null) 
                    queue.add(node.right);
            }
            
            // Update maximum if current level sum is larger
            if (levelSum > maxSum) {
                maxSum = levelSum;
                maxSumLevel = currentLevel;
            }
            currentLevel++;
        }
        return maxSumLevel;
    }
}
