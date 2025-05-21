/**
 * Problem Statement:
 *     LeetCode 637. Average of Levels in Binary Tree
 * 
 *     Given the root of a binary tree, return the average value of the nodes on
 *     each level in the form of an array. Answers within 10^-5 of the actual
 *     answer will be accepted.
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - List of averages for each level
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         9   20
 *            /  \
 *           15   7
 *     
 *     Output: [3.00000, 14.50000, 11.00000]
 *     Explanation:
 *     Level 1: 3 -> average = 3
 *     Level 2: 9, 20 -> average = 14.5
 *     Level 3: 15, 7 -> average = 11
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class j06AverageOfLevels {

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
            averageOfLevels(root1));  // Expected: [3.0, 14.5, 11.0]

        // Test Case 2: Large values
        TreeNode root2 = new TreeNode(2147483647);
        root2.left = new TreeNode(2147483647);
        root2.right = new TreeNode(2147483647);
        
        System.out.println("Test Case 2: " + 
            averageOfLevels(root2));  // Test integer overflow handling
    }

    /**
     * BFS with Level Average Calculation Approach
     * 
     * Intuition:
     * 1. Use BFS to process level by level
     * 2. Calculate sum and count for each level
     * 3. Handle potential integer overflow with long
     * 
     * Key Points:
     * - Use long for levelSum to avoid overflow
     * - Cast to double only during division
     * - Process all nodes at current level before next
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(w)
     * - w is maximum width of tree (queue storage)
     * - O(h) for result list where h is height
     * 
     * @param root Root node of binary tree
     * @return List of level averages
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Double> result = new ArrayList<>();
        
        queue.add(root);
        
        while (!queue.isEmpty()) {
            long levelSum = 0;  // Use long to handle overflow
            int levelSize = queue.size();
            
            // Process current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;

                // Add children for next level
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            
            // Calculate and add level average
            result.add((double)levelSum / levelSize);
        }
        return result;
    }
}
