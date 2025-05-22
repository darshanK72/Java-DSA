/**
 * LeetCode 1609. Even Odd Tree
 * 
 * Problem Statement:
 *     A binary tree is named Even-Odd if it meets the following conditions:
 *     - The root level is level 0, then level 1, and so on.
 *     - For each even-indexed level, all nodes have odd integer values in strictly
 *       increasing order (from left to right).
 *     - For each odd-indexed level, all nodes have even integer values in strictly
 *       decreasing order (from left to right).
 * 
 * Input:
 *     - Root node of a binary tree where 1 <= Node.val <= 10^6
 * 
 * Output:
 *     - Return true if the binary tree is Even-Odd, otherwise return false.
 * 
 * Example:
 *     Input: root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
 *     Output: true
 *     
 *     Explanation:
 *     The tree has the following levels:
 *     Level 0: [1]              - odd values, increasing from left to right
 *     Level 1: [10,4]          - even values, decreasing from left to right
 *     Level 2: [3,7,9]         - odd values, increasing from left to right
 *     Level 3: [12,8,6,2]      - even values, decreasing from left to right
 */

import java.util.LinkedList;
import java.util.Queue;

public class j10OddEvenTree {

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
     * - We need to check properties at each level, so level order traversal is ideal
     * - For each level, we need to:
     *   1. Track if we're at even or odd level
     *   2. Maintain previous value to check ordering
     *   3. Verify values are odd/even based on level
     *   4. Verify strictly increasing/decreasing order
     * 
     * Explanation:
     * 1. Use queue for level order traversal
     * 2. For each level:
     *    - Track level number (even/odd)
     *    - Initialize prev value based on level
     *    - For each node in level:
     *      * Even level: Check for odd values and increasing order
     *      * Odd level: Check for even values and decreasing order
     *    - Add children to queue for next level
     * 
     * Time Complexity:
     * - O(n) where n is the number of nodes in the tree
     * - We visit each node exactly once
     * 
     * Space Complexity:
     * - O(w) where w is the maximum width of the tree
     * - Queue will store at most width of the tree at any level
     * 
     * @param root The root node of the binary tree
     * @return true if the tree satisfies Even-Odd properties, false otherwise
     */
    public static boolean isEvenOddTree(TreeNode root) {
        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int prev = (level % 2 == 0) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // Check even level conditions: odd values and strictly increasing
                if (level % 2 == 0) {
                    if (node.val % 2 == 0 || node.val <= prev) {
                        return false;
                    }
                }
                // Check odd level conditions: even values and strictly decreasing
                else {
                    if (node.val % 2 == 1 || node.val >= prev) {
                        return false;
                    }
                }

                prev = node.val;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            level++;
        }
        return true;
    }
}
