/**
 * Problem Statement:
 *     LeetCode 107. Binary Tree Level Order Traversal II
 * 
 *     Given the root of a binary tree, return the bottom-up level order traversal of 
 *     its nodes' values. (i.e., from left to right, level by level from leaf to root).
 * 
 * Input:
 *     - Root node of binary tree
 * 
 * Output:
 *     - List of lists containing node values in bottom-up level order
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         9   20
 *            /  \
 *           15   7
 *     
 *     Output: [[15,7], [9,20], [3]]
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class j02LevelOrderTraversalReverse {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach 1: ArrayList with Reverse
     * 
     * Intuition:
     * - Use standard BFS with queue
     * - Collect levels in ArrayList
     * - Reverse entire list at end
     * 
     * Time Complexity: O(n)
     * - BFS traversal: O(n)
     * - List reversal: O(h) where h is height
     * 
     * Space Complexity: O(n)
     * - Queue storage: O(w) where w is max width
     * - Result storage: O(n)
     * 
     * @param root Root node of binary tree
     * @return List of lists with values in bottom-up order
     */
    public static List<List<Integer>> levelOrderBottomReverseList(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null)
            return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> levelInts = new ArrayList<>();
            int levelSize = queue.size();

            // Process current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelInts.add(node.val);

                // Add children for next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            list.add(levelInts);
        }

        Collections.reverse(list);  // Reverse to get bottom-up order
        return list;
    }

    /**
     * Approach 2: LinkedList with AddFirst
     * 
     * Intuition:
     * - Use standard BFS with queue
     * - Add each level to front of result list
     * - Natural bottom-up order without explicit reversal
     * 
     * Time Complexity: O(n)
     * - BFS traversal: O(n)
     * - addFirst operation: O(1)
     * 
     * Space Complexity: O(n)
     * - Queue storage: O(w) where w is max width
     * - Result storage: O(n)
     * 
     * @param root Root node of binary tree
     * @return List of lists with values in bottom-up order
     */
    public static List<List<Integer>> levelOrderBottomLinkedList(TreeNode root) {
        LinkedList<List<Integer>> list = new LinkedList<>();
        if (root == null)
            return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            ArrayList<Integer> levelInts = new ArrayList<>();
            int levelSize = queue.size();

            // Process current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelInts.add(node.val);

                // Add children for next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            list.addFirst(levelInts);  // Add at front for bottom-up order
        }
        return list;
    }

    public static void main(String[] args) {
        // Test Case 1: Normal tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        System.out.println("ArrayList Approach: " + 
            levelOrderBottomReverseList(root1));
        System.out.println("LinkedList Approach: " + 
            levelOrderBottomLinkedList(root1));

        // Test Case 2: Single node
        TreeNode root2 = new TreeNode(1);
        System.out.println("\nSingle Node Test:");
        System.out.println("ArrayList Approach: " + 
            levelOrderBottomReverseList(root2));
        System.out.println("LinkedList Approach: " + 
            levelOrderBottomLinkedList(root2));
    }
}
